package wbh.wilfred.ivege.model;

import java.math.BigDecimal;

public class RateDiscount extends ProductDiscount {
    private BigDecimal rate;
    @Override
    public Rmb calculate(Rmb original) {
        return original.times(rate);
    }

    @Override
    public Comparable value() {
        return rate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

}
