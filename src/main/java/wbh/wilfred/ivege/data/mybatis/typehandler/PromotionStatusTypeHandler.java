package wbh.wilfred.ivege.data.mybatis.typehandler;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import wbh.wilfred.ivege.model.Promotion;

public class PromotionStatusTypeHandler extends EnumOrdinalTypeHandler<Promotion.Status> {

    public PromotionStatusTypeHandler() {
        super(Promotion.Status.class);
    }
}
