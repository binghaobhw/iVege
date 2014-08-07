package wbh.wilfred.ivege.data;

import wbh.wilfred.ivege.model.Product;
import wbh.wilfred.ivege.model.selector.ProductSelector;

import java.util.List;

public interface ProductAccessor {
    public Product getProductById(long id);
    public List<Product> getProductsByIds(List<Long> ids);
    public long addProduct(Product product);
    public List<Product> getProducts(ProductSelector selector);
    public void updateProduct(Product product);
}
