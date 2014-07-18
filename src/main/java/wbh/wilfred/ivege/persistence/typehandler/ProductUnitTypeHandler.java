package wbh.wilfred.ivege.persistence.typehandler;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import wbh.wilfred.ivege.model.Product;

public class ProductUnitTypeHandler extends EnumOrdinalTypeHandler<Product.Unit> {

    public ProductUnitTypeHandler() {
        super(Product.Unit.class);
    }
}
