package wbh.wilfred.ivege.persistence.typehandler;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import wbh.wilfred.ivege.model.Order;

public class OrderStatusTypeHandler extends EnumOrdinalTypeHandler<Order.Status> {

    public OrderStatusTypeHandler() {
        super(Order.Status.class);
    }
}
