package wbh.wilfred.ivege.model;

import java.math.BigDecimal;

public class OrderItem {
    private long id;
    private Product product;
    private Discount discount;
    private BigDecimal userQuantity;
    private Unit userUnit;
    private BigDecimal quantity;
    private Unit unit;
    private Rmb price;
    private Rmb subtotal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Rmb getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Rmb subtotal) {
        this.subtotal = subtotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Rmb getPrice() {
        return price;
    }

    public void setPrice(Rmb price) {
        this.price = price;
    }

    public void setUserQuantity(BigDecimal userQuantity) {
        this.userQuantity = userQuantity;
    }

    public Unit getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(Unit userUnit) {
        this.userUnit = userUnit;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public BigDecimal getUserQuantity() {
        return userQuantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }
}
