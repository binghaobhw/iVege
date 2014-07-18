package wbh.wilfred.ivege.data;

import org.joda.time.DateTime;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.selector.DiscountSelector;

import java.util.List;

public interface DiscountAccessor {
    public String addDiscount(Discount discount);
    public List<Discount> getDiscounts(DiscountSelector selector);
    public List<Discount> getInTimeDiscounts(DateTime dateTime);
    public List<Discount> getInTimeDiscounts(DateTime dateTime,
                                             Discount.Type type);
}
