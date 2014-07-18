package wbh.wilfred.ivege.model;

import java.math.BigDecimal;

public class Gift extends Promotion {
    private String productId;
    private BigDecimal amount;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
