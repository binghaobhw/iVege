package wbh.wilfred.ivege.model;

public abstract class Discount extends Promotion implements Comparable<Discount> {
    private Type type;

    public enum Type {
        PRODUCT, ORDER
    }

    @Override
    public int compareTo(Discount o) {
        int d = this.getClass().getSimpleName().compareTo(o.getClass()
                .getSimpleName());
        if (d != 0) {
            return d;
        }
        // The less the value is, the greater the discount is
        d = o.value().compareTo(this.value());
        if (d != 0) {
            return d;
        }
        // Being created the later, the greater the discount is
        return this.getCreateTime().compareTo(o.getCreateTime());
    }

    public abstract Rmb apply(Rmb original);

    public abstract Comparable value();

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
