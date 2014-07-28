package wbh.wilfred.ivege.model;

public class PriceDiscount extends Discount {
    private Rmb price;

    @Override
    public Rmb apply(Rmb original) {
        return price;
    }

    @Override
    public Comparable value() {
        return price;
    }

    public Rmb getPrice() {
        return price;
    }

    public void setPrice(Rmb price) {
        this.price = price;
    }

}
