package wbh.wilfred.ivege.model.selector;

import wbh.wilfred.ivege.model.Product;

public class ProductSelector {
    private Long categoryId;
    private Product.Status status;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Product.Status getStatus() {
        return status;
    }

    public void setStatus(Product.Status status) {
        this.status = status;
    }
}
