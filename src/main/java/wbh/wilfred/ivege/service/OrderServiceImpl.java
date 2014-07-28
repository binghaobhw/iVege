package wbh.wilfred.ivege.service;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wbh.wilfred.ivege.data.DiscountAccessor;
import wbh.wilfred.ivege.data.GiftAccessor;
import wbh.wilfred.ivege.data.OrderAccessor;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.Gift;
import wbh.wilfred.ivege.model.Order;
import wbh.wilfred.ivege.model.OrderItem;
import wbh.wilfred.ivege.model.Product;
import wbh.wilfred.ivege.model.Promotion;
import wbh.wilfred.ivege.model.Quantity;
import wbh.wilfred.ivege.model.Rmb;
import wbh.wilfred.ivege.model.selector.GiftSelector;
import wbh.wilfred.ivege.model.selector.OrderSelector;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderAccessor orderAccessor;
    @Autowired
    private DiscountAccessor discountAccessor;
    @Autowired
    private GiftAccessor giftAccessor;

    @Override
    public Order getOrderById(long id) {
        return orderAccessor.getOrderById(id);
    }

    @Override
    public Order addConfirmedOrder(Order order) {
        processConfirmedOrder(order);
        orderAccessor.addOrder(order);
        return order;
    }

    @Override
    public Order addUnconfirmedOrder(Order order) {
        processUnconfirmedOrder(order);
        orderAccessor.addOrder(order);
        return order;
    }

    @Override
    public Order calculateOrder(Order order) {
        processConfirmedOrder(order);
        orderAccessor.updateOrder(order);
        return order;
    }

    @Override
    public List<Order> getOrders(OrderSelector orderSelector) {
        return orderAccessor.getOrders(orderSelector);
    }

    @Override
    public void updateOrder(Order order) {
        orderAccessor.updateOrder(order);
    }

    public Order calculateTotal(Order order) {
        List<OrderItem> orderItems= order.getItems();
        Rmb total = Rmb.ZERO;
        List<Discount> inTimeProductDiscounts = discountAccessor
                .getInTimeProductDiscounts(order.getCreateTime());
        for (OrderItem item: orderItems) {
            Quantity quantity = item.getActualQuantity();
            if (quantity == null) {
                throw new IllegalArgumentException();
            }
            List<Discount> availableDiscounts = new ArrayList<Discount>();
            Product product = item.getProduct();
            for (Discount discount: inTimeProductDiscounts) {
                if (discount.isForProduct(product)) {
                    availableDiscounts.add(discount);
                }
            }
            Rmb price;
            Discount bestDiscount;
            if (availableDiscounts.isEmpty()) {
                bestDiscount = null;
                price = product.getPrice();
            } else {
                bestDiscount = DiscountChooser.bestDiscount(product
                        .getPrice(), availableDiscounts);
                price = bestDiscount.apply(product.getPrice());
            }
            item.setDiscount(bestDiscount);
            item.setPrice(price);
            Rmb subtotal = price.times(quantity);
            item.setSubtotal(subtotal);
            total = total.plus(subtotal);
        }
        order.setOriginalTotal(total);
        return order;
    }

    public Order calculateOrderDiscount(Order order) {
        if (order.getOriginalTotal() == null) {
            calculateTotal(order);
        }
        List<Discount> inTimeOrderDiscounts = discountAccessor
                .getInTimeOrderDiscounts(order.getCreateTime());
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
            order.setDiscount(bestDiscount);
            discountedTotal = bestDiscount.apply(orderTotal);
        }
        order.setTotal(discountedTotal);
        return order;
    }

    public Order calculateGift(Order order) {
        GiftSelector selector = new GiftSelector();
        selector.setDateTime(order.getCreateTime());
        selector.setStatus(Promotion.Status.ACTIVE);
        List<Gift> inTimeGifts = giftAccessor.getGifts(selector);
        Gift bestGift = (CollectionUtils.isNotEmpty(inTimeGifts))? null:
                GiftChooser.bestGift(inTimeGifts);
        order.setGift(bestGift);
        return order;
    }

    public Order processConfirmedOrder(Order order) {
        if (order.getCreateTime() == null) {
            order.setCreateTime(DateTime.now());
        }
        calculateTotal(order);
        calculateOrderDiscount(order);
        calculateGift(order);
        order.confirmed();
        return order;
    }

    public Order processUnconfirmedOrder(Order order) {
        if (order.getCreateTime() == null) {
            order.setCreateTime(DateTime.now());
        }
        order.unconfirmed();
        return order;
    }
}
