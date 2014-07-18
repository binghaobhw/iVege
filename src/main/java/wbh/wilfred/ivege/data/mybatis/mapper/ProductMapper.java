package wbh.wilfred.ivege.data.mybatis.mapper;

import wbh.wilfred.ivege.model.Product;

public interface ProductMapper {
    public Product getProductById(long id);
    public long addProduct(Product product);
    public void updateProduct(Product product);
}
