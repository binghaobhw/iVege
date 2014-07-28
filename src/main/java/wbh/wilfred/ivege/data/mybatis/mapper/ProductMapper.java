package wbh.wilfred.ivege.data.mybatis.mapper;

import wbh.wilfred.ivege.model.Product;
import wbh.wilfred.ivege.model.selector.ProductSelector;

import java.util.List;

public interface ProductMapper {
    public Product getProductById(long id);
    public long addProduct(Product product);
//    public void updateProduct(Product product);
    public List<Product> getProducts(ProductSelector selector);
}
