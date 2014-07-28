package wbh.wilfred.ivege.model;

import java.math.BigDecimal;

public class Quantity {
    private BigDecimal value;
    private Unit unit;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
