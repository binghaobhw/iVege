package wbh.wilfred.ivege.service;

import wbh.wilfred.ivege.model.Product;
import wbh.wilfred.ivege.model.selector.ProductSelector;

import java.util.List;


public interface ProductService {
    public Product getProductById(long id);
    public List<Product> getProducts(ProductSelector selector);
    public long addProduct(Product product);
    public void updateProduct(Product product);
}
