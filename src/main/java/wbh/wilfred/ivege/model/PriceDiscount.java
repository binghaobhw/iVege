package wbh.wilfred.ivege.model;

public class PriceDiscount extends ProductDiscount {
    private Rmb price;

    @Override
    public Rmb calculate(Rmb original) {
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
