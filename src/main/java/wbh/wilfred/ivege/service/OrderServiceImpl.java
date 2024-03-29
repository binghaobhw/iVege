package wbh.wilfred.ivege.service;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wbh.wilfred.ivege.data.DiscountAccessor;
import wbh.wilfred.ivege.data.GiftAccessor;
import wbh.wilfred.ivege.data.OrderAccessor;
import wbh.wilfred.ivege.data.ProductAccessor;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.Gift;
import wbh.wilfred.ivege.model.Order;
import wbh.wilfred.ivege.model.OrderItem;
import wbh.wilfred.ivege.model.Product;
import wbh.wilfred.ivege.model.Promotion;
import wbh.wilfred.ivege.model.Rmb;
import wbh.wilfred.ivege.model.selector.GiftSelector;
import wbh.wilfred.ivege.model.selector.OrderSelector;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderAccessor orderAccessor;
    @Autowired
    private ProductAccessor productAccessor;
    @Autowired
    private DiscountAccessor discountAccessor;
    @Autowired
    private GiftAccessor giftAccessor;

    @Override
    public Order getOrderById(long id) {
        return orderAccessor.getOrderById(id);
    }

    @Override
    public List<Order> getOrders(OrderSelector orderSelector) {
        return orderAccessor.getOrders(orderSelector);
    }

    // Fill in the essential fields of saved order, then update
    @Override
    @Transactional
    public void confirmOrder(Order order) {
        Order savedOrder = orderAccessor.getOrderById(order.getId());
        for (OrderItem savedItem: savedOrder.getItems()) {
            for (OrderItem item: order.getItems()) {
                if (savedItem.getId() == item.getId()) {
                    savedItem.setQuantity(item.getQuantity());
                    savedItem.setUnit(item.getUnit());
                }
            }
        }
        processConfirmedOrder(savedOrder);
        orderAccessor.updateOrder(savedOrder);
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        orderAccessor.updateOrder(order);
    }

    @Override
    public void completeOrder(Order order) {
        Order o = new Order();
        o.setId(order.getId());
        o.setCompleteTime(DateTime.now());
        orderAccessor.updateOrder(o);
    }

    private Order calculateTotal(Order order) {
        List<OrderItem> orderItems= order.getItems();
        Rmb total = Rmb.ZERO;
        for (OrderItem item: orderItems) {
            total = total.plus(item.getSubtotal());
        }
        order.setOriginalTotal(total);
        return order;
    }

    private Order calculateOrderDiscount(Order order) {
        List<Discount> inTimeOrderDiscounts = discountAccessor
                .getInTimeOrderDiscounts(order.getCreateTime());
        Rmb orderTotal = order.getOriginalTotal();
        List<Discount> availableDiscounts = new ArrayList<Discount>();
        for (Discount discount: inTimeOrderDiscounts) {
            if (discount.isForAll() &&
                    orderTotal.greaterOrEqual(discount.getMinTotal())) {
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

    private Order calculateGift(Order order) {
        GiftSelector selector = new GiftSelector();
        selector.setDateTime(order.getCreateTime());
        selector.setStatus(Promotion.Status.ACTIVE);
        selector.setTotal(order.getOriginalTotal());
        List<Gift> availableGifts = giftAccessor.getGifts(selector);
        Gift bestGift = (CollectionUtils.isNotEmpty(availableGifts))? null:
                GiftChooser.bestGift(availableGifts);
        order.setGift(bestGift);
        return order;
    }

    private Order processConfirmedOrder(Order order) {
        if (order.getCreateTime() == null) {
            order.setCreateTime(DateTime.now());
        }
        calculateProductDiscount(order);
        calculateTotal(order);
        calculateOrderDiscount(order);
        calculateGift(order);
        order.confirmed();
        return order;
    }

    private Order calculateProductDiscount(Order order) {
        List<Discount> inTimeProductDiscounts = discountAccessor
                .getInTimeProductDiscounts(order.getCreateTime());
        for (OrderItem item: order.getItems()) {
            BigDecimal quantity = item.getQuantity();
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
        }
        return order;
    }

    @Override
    @Transactional
    public Order addOrder(Order order) {
        boolean confirmed = true;
        // Check if userUnit == product.unit
        List<OrderItem> orderItems = order.getItems();
        List<Long> productIds = new ArrayList<Long>();
        for (OrderItem item: orderItems) {
            productIds.add(item.getProduct().getId());
        }
        List<Product> products = productAccessor.getProductsByIds(productIds);
        for (int i = 0; i < orderItems.size(); ++i) {
            Product product = products.get(i);
            OrderItem orderItem = orderItems.get(i);
            if (orderItem.getUserUnit() != product.getUnit()) {
                confirmed = false;
            } else {
                orderItem.setQuantity(orderItem.getUserQuantity());
                orderItem.setUnit(orderItem.getUserUnit());
            }
        }
        if (confirmed) {
            processConfirmedOrder(order);
        } else {
            processUnconfirmedOrder(order);
        }
        orderAccessor.addOrder(order);
        return order;
    }

    private Order processUnconfirmedOrder(Order order) {
        order.setCreateTime(DateTime.now());
        order.unconfirmed();
        return order;
    }
}
