package wbh.wilfred.ivege.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import wbh.wilfred.ivege.data.DiscountAccessor;
import wbh.wilfred.ivege.data.OrderAccessor;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.Order;
import wbh.wilfred.ivege.model.OrderItem;
import wbh.wilfred.ivege.model.Rmb;
import wbh.wilfred.ivege.model.selector.OrderSelector;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderAccessor orderAccessor;
    @Autowired
    private DiscountAccessor discountAccessor;

    @Override
    public Order getOrderById(String id) {
        return orderAccessor.getOrderById(id);
    }

    @Override
    @Transactional
    public Order addOrder(@Validated Order order) {
        completeOrder(order);
        orderAccessor.addOrder(order);
        return order;
    }

    @Override
    public List<Order> getOrders(OrderSelector orderSelector) {
        return orderAccessor.getOrders(orderSelector);
    }

    @Override
    public Order calculateTotal(Order order) {
        List<OrderItem> orderItems= order.getItems();
        Rmb total = Rmb.ZERO;
        List<Discount> inTimeProductDiscounts = discountAccessor
                .getInTimeDiscounts(order.getCreationTime(), Discount.Type.PRODUCT);
        for (OrderItem item: orderItems) {
            List<Discount> availableDiscounts = new ArrayList<Discount>();
            for (Discount discount: inTimeProductDiscounts) {
                if (discount.isForCategory(item.getCategoryId()) || discount
                        .isForProduct(item.getProductId())) {
                    availableDiscounts.add(discount);
                }
            }
            Rmb price;
            if (availableDiscounts.isEmpty()) {
                price = item.getOriginalPrice();
            } else {
                Discount bestDiscount = DiscountChooser.bestDiscount(item.getOriginalPrice(),
                        availableDiscounts);
                price = bestDiscount.calculate(item.getOriginalPrice());
                item.setDiscountId(bestDiscount.getId());
                item.setDiscountName(bestDiscount.getName());
                item.setPrice(price);
            }
            BigDecimal amount = item.getAmount();
            Rmb subtotal = (amount.compareTo(BigDecimal.ONE) == 0) ?
                    price : price.times(item.getAmount());
            item.setSubtotal(subtotal);
            total = total.plus(subtotal);
        }
        order.setOriginalTotal(total);
        return order;
    }

    @Override
    public Order calculateDiscountedTotal(Order order) {
        if (order.getOriginalTotal() == null) {
            calculateTotal(order);
        }
        List<Discount> inTimeOrderDiscounts = discountAccessor
                .getInTimeDiscounts(order.getCreationTime(), Discount.Type.ORDER);
        Rmb orderTotal = order.getOriginalTotal();
        List<Discount> availableDiscounts = new ArrayList<Discount>();
        for (Discount discount: inTimeOrderDiscounts) {
            if (discount.isForAll() && orderTotal.greaterOrEqual(discount
                    .getMinTotal())) {
                availableDiscounts.add(discount);
            }
        }
        Rmb discountedTotal;
        if (availableDiscounts.isEmpty()) {
            discountedTotal = orderTotal;
        } else {
            Discount bestDiscount = DiscountChooser.bestDiscount(orderTotal,
                    availableDiscounts);
            order.setDiscountId(bestDiscount.getId());
            order.setDiscountName(bestDiscount.getName());
            discountedTotal = bestDiscount.calculate(orderTotal);
        }
        order.setTotal(discountedTotal);
        return order;
    }

    @Override
    public Order calculateBonus(Order order) {
        return null;
    }

    @Override
    public Order completeOrder(Order order) {
        order.setCreationTime(DateTime.now());
        calculateTotal(order);
        calculateDiscountedTotal(order);
        calculateBonus(order);
        order.valid();
        return order;
    }
}
