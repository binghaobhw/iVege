package wbh.wilfred.ivege.service;

import org.springframework.beans.factory.annotation.Autowired;
import wbh.wilfred.ivege.data.DiscountAccessor;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.FlatDiscount;
import wbh.wilfred.ivege.model.PriceDiscount;
import wbh.wilfred.ivege.model.RateDiscount;
import wbh.wilfred.ivege.model.selector.DiscountSelector;

import java.util.List;

public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountAccessor discountAccessor;

    @Override
    public long addDiscount(Discount discount) {
        return discountAccessor.addDiscount(discount);
    }

    @Override
    public List<Discount> getDiscounts(DiscountSelector selector) {
        return discountAccessor.getDiscounts(selector);
    }

    @Override
    public List<FlatDiscount> getFlatDiscounts(DiscountSelector selector) {
        return discountAccessor.getDiscounts(selector);
    }

    @Override
    public List<PriceDiscount> getPriceDiscounts(DiscountSelector selector) {
        return discountAccessor.getDiscounts(selector);
    }

    @Override
    public List<RateDiscount> getRateDiscounts(DiscountSelector selector) {
        return discountAccessor.getDiscounts(selector);
    }

    public <T> List<T> getDiscounts(DiscountSelector selector, Class<T> entityClass) {
        return discountAccessor.getDiscounts(selector);
    }

    @Override
    public void changeStatus(DiscountSelector selector) {

    }
}
