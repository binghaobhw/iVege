package wbh.wilfred.ivege.model;

public class OrderItem {
    private long id;
    private Product product;
    private Discount discount;
    private Quantity userQuantity;
    private Quantity actualQuantity;
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

    public Quantity getUserQuantity() {
        return userQuantity;
    }

    public void setUserQuantity(Quantity userQuantity) {
        this.userQuantity = userQuantity;
    }

    public Quantity getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(Quantity actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public Rmb getPrice() {
        return price;
    }

    public void setPrice(Rmb price) {
        this.price = price;
    }
}
