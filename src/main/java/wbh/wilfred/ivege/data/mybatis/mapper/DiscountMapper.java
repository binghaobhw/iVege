package wbh.wilfred.ivege.data.mybatis.mapper;

import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.selector.DiscountSelector;

import java.util.List;
import java.util.Map;

public interface DiscountMapper {
    public <T> T getDiscountById(long id);
    public long addDiscount(Discount discount);
    public long addDiscountCategory(Map<String, Object> p);
    public long addDiscountProduct(Map<String, Object> p);
    public <T> List<T> getDiscounts(DiscountSelector selector);
    public void updateDiscount(Discount discount);
}
