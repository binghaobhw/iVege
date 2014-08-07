package wbh.wilfred.ivege.service;

import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.selector.DiscountSelector;

import java.util.List;


public interface DiscountService {
    public Discount getDiscountById(long id);
    public long addDiscount(Discount discount);
    public List<Discount> getDiscounts(DiscountSelector selector);
    public void updateDiscount(Discount discount);
}
