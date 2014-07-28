package wbh.wilfred.ivege.data.mybatis.typehandler;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import wbh.wilfred.ivege.model.Unit;

public class UnitTypeHandler extends EnumOrdinalTypeHandler<Unit> {

    public UnitTypeHandler() {
        super(Unit.class);
    }
}
