package wbh.wilfred.ivege.data;

import wbh.wilfred.ivege.model.Product;

import java.util.List;

public interface ProductAccessor {
    public String addProduct(Product product);
    public List<Product> getProducts(Product selector);
}
