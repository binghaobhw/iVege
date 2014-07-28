package wbh.wilfred.ivege.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wbh.wilfred.ivege.data.mybatis.mapper.ProductMapper;
import wbh.wilfred.ivege.model.Product;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public Product getProductById(long id) {
        return productMapper.getProductById(id);
    }

    @Override
    @Transactional
    public long addProduct(Product product) {
        return productMapper.addProduct(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
//        productMapper.updateProduct(product);
    }
}
