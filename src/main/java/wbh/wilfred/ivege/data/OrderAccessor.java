package wbh.wilfred.ivege.data;

import wbh.wilfred.ivege.model.Order;
import wbh.wilfred.ivege.model.selector.OrderSelector;

import java.util.List;

public interface OrderAccessor {
    public Order getOrderById(String id);
    public String addOrder(Order order);
    public List<Order> getOrders(OrderSelector orderSelector);
}
