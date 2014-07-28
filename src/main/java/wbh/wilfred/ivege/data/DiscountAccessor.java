package wbh.wilfred.ivege.data;

import org.joda.time.DateTime;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.selector.DiscountSelector;

import java.util.List;

public interface DiscountAccessor {
    public <T> T getDiscountById(long id);
    public long addDiscount(Discount discount);
    public <T> List<T> getDiscounts(DiscountSelector selector);
    public void updateDiscount(Discount discount);
    public List<Discount> getInTimeOrderDiscounts(DateTime dateTime);
    public List<Discount> getInTimeProductDiscounts(DateTime dateTime);
}
