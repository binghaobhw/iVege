package wbh.wilfred.ivege.model.selector;

import wbh.wilfred.ivege.model.Discount;

public class DiscountSelector extends PromotionSelector {
    private Discount.Type type;

    public Discount.Type getType() {
        return type;
    }

    public void setType(Discount.Type type) {
        this.type = type;
    }
}
