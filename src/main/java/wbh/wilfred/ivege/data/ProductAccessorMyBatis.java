package wbh.wilfred.ivege.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wbh.wilfred.ivege.data.mybatis.mapper.ProductMapper;
import wbh.wilfred.ivege.model.Product;
import wbh.wilfred.ivege.model.selector.ProductSelector;

import java.util.List;

@Repository
public class ProductAccessorMyBatis implements ProductAccessor {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getProductById(long id) {
        return productMapper.getProductById(id);
    }

    @Override
    public List<Product> getProductsByIds(List<Long> ids) {
        return productMapper.getProductsByIds(ids);
    }

    @Override
    public long addProduct(Product product) {
        productMapper.addProduct(product);
        return product.getId();
    }

    @Override
    public List<Product> getProducts(ProductSelector selector) {
        return productMapper.getProducts(selector);
    }
}
