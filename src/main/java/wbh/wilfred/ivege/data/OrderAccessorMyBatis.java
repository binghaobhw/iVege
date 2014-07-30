package wbh.wilfred.ivege.data;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wbh.wilfred.ivege.data.mybatis.mapper.OrderMapper;
import wbh.wilfred.ivege.model.Order;
import wbh.wilfred.ivege.model.selector.OrderSelector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderAccessorMyBatis implements OrderAccessor {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order getOrderById(long id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public long addOrder(Order order) {
        if (order.getCreateTime() == null) {
            order.setCreateTime(DateTime.now());
        }
        orderMapper.addOrder(order);
        long orderId = order.getId();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("orderId", orderId);
        param.put("orderItems", order.getItems());
        orderMapper.addOrderItems(param);
        return orderId;
    }

    @Override
    public List<Order> getOrders(OrderSelector orderSelector) {
        return orderMapper.getOrders(orderSelector);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateOrder(order);
    }
}
