package wbh.wilfred.ivege.data.mybatis.mapper;

import wbh.wilfred.ivege.model.OrderItem;

import java.util.List;

public interface OrderItemMapper {
    public OrderItem getOrderItemById(long id);
    public long addOrderItems(List<OrderItem> orderItems);
}
