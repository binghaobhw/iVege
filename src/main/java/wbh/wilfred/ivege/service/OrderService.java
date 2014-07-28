package wbh.wilfred.ivege.service;

import wbh.wilfred.ivege.model.Order;
import wbh.wilfred.ivege.model.selector.OrderSelector;

import java.util.List;


public interface OrderService {
    public Order getOrderById(long id);
    public Order addConfirmedOrder(Order order);
    public Order addUnconfirmedOrder(Order order);
    public Order calculateOrder(Order order);
    public List<Order> getOrders(OrderSelector orderSelector);
    public void updateOrder(Order order);
}
