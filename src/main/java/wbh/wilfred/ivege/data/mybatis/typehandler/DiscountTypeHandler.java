package wbh.wilfred.ivege.data.mybatis.typehandler;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import wbh.wilfred.ivege.model.Discount;

public class DiscountTypeHandler extends EnumOrdinalTypeHandler<Discount.Type> {

    public DiscountTypeHandler() {
        super(Discount.Type.class);
    }
}
