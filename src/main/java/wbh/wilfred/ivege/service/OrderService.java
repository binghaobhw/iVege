package wbh.wilfred.ivege.service;

import wbh.wilfred.ivege.model.Order;
import wbh.wilfred.ivege.model.selector.OrderSelector;

import java.util.List;


public interface OrderService {
    public Order getOrderById(long id);
    public Order addOrder(Order order);
    public List<Order> getOrders(OrderSelector orderSelector);
    // Fill in the essential fields of saved order, then update
    public void confirmOrder(Order order);
    public void updateOrder(Order order);
    public void completeOrder(Order order);


}
