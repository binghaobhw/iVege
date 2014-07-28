package wbh.wilfred.ivege.model;

public class Gift extends Promotion implements Comparable<Gift> {
    private Product product;
    private Quantity quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(Gift o) {
        return this.getCreateTime().compareTo(o.getCreateTime());
    }
}
