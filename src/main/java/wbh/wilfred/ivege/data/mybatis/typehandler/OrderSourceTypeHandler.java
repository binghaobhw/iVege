package wbh.wilfred.ivege.data.mybatis.typehandler;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import wbh.wilfred.ivege.model.Order;

public class OrderSourceTypeHandler extends EnumOrdinalTypeHandler<Order.Source> {

    public OrderSourceTypeHandler() {
        super(Order.Source.class);
    }
}
