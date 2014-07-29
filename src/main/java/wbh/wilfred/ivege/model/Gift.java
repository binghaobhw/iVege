package wbh.wilfred.ivege.model;

import java.math.BigDecimal;

public class Gift extends Promotion implements Comparable<Gift> {
    private Product product;
    private BigDecimal quantity;
    private Unit unit;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(Gift o) {
        return this.getCreateTime().compareTo(o.getCreateTime());
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
