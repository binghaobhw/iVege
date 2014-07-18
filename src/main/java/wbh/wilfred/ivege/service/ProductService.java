package wbh.wilfred.ivege.service;

import wbh.wilfred.ivege.model.Product;


public interface ProductService {
    public Product getProductById(long id);
    public long addProduct(Product product);
    public void updateProduct(Product product);
}
