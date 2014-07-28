package wbh.wilfred.ivege.service;

import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.FlatDiscount;
import wbh.wilfred.ivege.model.PriceDiscount;
import wbh.wilfred.ivege.model.RateDiscount;
import wbh.wilfred.ivege.model.selector.DiscountSelector;

import java.util.List;


public interface DiscountService {
    public long addDiscount(Discount discount);
    public List<Discount> getDiscounts(DiscountSelector selector);
    public List<FlatDiscount> getFlatDiscounts(DiscountSelector selector);
    public List<PriceDiscount> getPriceDiscounts(DiscountSelector selector);
    public List<RateDiscount> getRateDiscounts(DiscountSelector selector);
    public void changeStatus(DiscountSelector selector);
}
