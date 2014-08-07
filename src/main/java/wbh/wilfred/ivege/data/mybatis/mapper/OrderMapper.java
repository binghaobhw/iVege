package wbh.wilfred.ivege.data.mybatis.mapper;

import wbh.wilfred.ivege.model.Order;
import wbh.wilfred.ivege.model.OrderItem;
import wbh.wilfred.ivege.model.selector.OrderSelector;

import java.util.List;
import java.util.Map;

public interface  OrderMapper {
    public Order getOrderById(long id);
    public List<Order> getOrders(OrderSelector orderSelector);
    public void addOrder(Order order);
    public void addOrderItems(Map<String, Object> param);
    public void updateOrder(Order order);
    public void updateOrderItems(List<OrderItem> orderItems);
}
