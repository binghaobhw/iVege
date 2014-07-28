package wbh.wilfred.ivege.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wbh.wilfred.ivege.data.mybatis.mapper.OrderItemMapper;
import wbh.wilfred.ivege.data.mybatis.mapper.OrderMapper;
import wbh.wilfred.ivege.model.Order;
import wbh.wilfred.ivege.model.OrderItem;
import wbh.wilfred.ivege.model.selector.OrderSelector;

import java.util.List;

@Repository
public class OrderAccessorMyBatis implements OrderAccessor {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public Order getOrderById(long id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public long addOrder(Order order) {
        orderMapper.addOrder(order);
        long orderId = order.getId();
        List<OrderItem> orderItems = order.getItems();
        orderItemMapper.addOrderItems(orderItems);
        return orderId;
    }

    @Override
    public List<Order> getOrders(OrderSelector orderSelector) {
        return null;
    }

    @Override
    public void updateOrder(Order order) {

    }
}
