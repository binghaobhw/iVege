package wbh.wilfred.ivege.data.mybatis.typehandler;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import wbh.wilfred.ivege.model.Product;

public class ProductStatusTypeHandler extends EnumOrdinalTypeHandler<Product.Status> {

    public ProductStatusTypeHandler() {
        super(Product.Status.class);
    }
}
