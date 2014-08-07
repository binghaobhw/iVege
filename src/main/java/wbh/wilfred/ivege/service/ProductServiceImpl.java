package wbh.wilfred.ivege.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wbh.wilfred.ivege.data.ProductAccessor;
import wbh.wilfred.ivege.model.Product;
import wbh.wilfred.ivege.model.selector.ProductSelector;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductAccessor productAccessor;
    @Override
    public Product getProductById(long id) {
        return productAccessor.getProductById(id);
    }

    @Override
    public List<Product> getProducts(ProductSelector selector) {
        return productAccessor.getProducts(selector);
    }

    @Override
    @Transactional
    public long addProduct(Product product) {
        return productAccessor.addProduct(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        productAccessor.updateProduct(product);
    }
}
