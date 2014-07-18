package wbh.wilfred.ivege.service;

import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.Order;
import wbh.wilfred.ivege.model.OrderItem;
import wbh.wilfred.ivege.model.Rmb;

import java.util.List;

public class OrderUtils {

    private OrderUtils() {}

    public static Rmb calculateTotal(Order order) {
        List<OrderItem> orderItems= order.getItems();
        Rmb total = Rmb.ZERO;
        for (OrderItem item: orderItems) {
            Rmb subtotal = item.getSubtotal();
            if (subtotal == null) {
                subtotal = item.getOriginalPrice().times(item.getAmount());
            }
            total = total.plus(subtotal);
        }
        return total;
    }

    public static Rmb calculateFinalTotal(Order order, Discount discount) {
        Rmb totalLimit = discount.getMinTotal();
        Rmb orderTotal = order.getOriginalTotal();
        if (discount.isForAll() && orderTotal.greaterOrEqual
                (totalLimit)) {
            return discount.calculate(orderTotal);
        }
        for (OrderItem orderItem : order.getItems()) {
            Rmb subtotal = orderItem.getSubtotal();
            if ((discount.isForProduct(orderItem.getProductId()) ||
                    discount.isForCategory(orderItem.getCategoryId()
                    )) && orderItem.getSubtotal().greaterOrEqual(totalLimit)) {
                return orderTotal.minus(subtotal).plus(discount.calculate
                        (subtotal));
            }
        }
        return orderTotal;
    }
}
