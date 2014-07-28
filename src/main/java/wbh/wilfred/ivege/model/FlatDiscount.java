package wbh.wilfred.ivege.model;

public class FlatDiscount extends Discount {
    private Rmb value;
    @Override
    public Rmb apply(Rmb original) {
        return original.minus(value);
    }

    @Override
    public Comparable value() {
        return value;
    }

    public Rmb getValue() {
        return value;
    }

    public void setValue(Rmb value) {
        this.value = value;
    }
}
