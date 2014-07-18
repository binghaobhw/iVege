package wbh.wilfred.ivege.service;

import wbh.wilfred.ivege.model.Order;
import wbh.wilfred.ivege.model.selector.OrderSelector;

import java.util.List;


public interface OrderService {
    public Order getOrderById(String id);
    public Order addOrder(Order order);
    public List<Order> getOrders(OrderSelector orderSelector);
    public Order calculateTotal(Order order);
    public Order calculateDiscountedTotal(Order order);
    public Order calculateBonus(Order order);
    public Order completeOrder(Order order);
}
