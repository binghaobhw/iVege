package wbh.wilfred.ivege.data;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wbh.wilfred.ivege.data.mybatis.mapper.DiscountMapper;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.selector.DiscountSelector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DiscountAccessorMyBatis implements DiscountAccessor {
    @Autowired
    private DiscountMapper discountMapper;

    @Override
    public <T> T getDiscountById(long id) {
        return discountMapper.getDiscountById(id);
    }

    @Override
    public long addDiscount(Discount discount) {
        discount.setCreateTime(DateTime.now());
        discountMapper.addDiscount(discount);
        long discountId = discount.getId();
        if (!discount.isForAll()) {
            if (CollectionUtils.isNotEmpty(discount.getCategories())) {
                Map<String, Object> dc = new HashMap<String, Object>();
                dc.put("discountId", discountId);
                dc.put("categories", discount.getCategories());
                discountMapper.addDiscountCategory(dc);
            }
            if (CollectionUtils.isNotEmpty(discount.getProducts())) {
                Map<String, Object> dp = new HashMap<String, Object>();
                dp.put("discountId", discountId);
                dp.put("products", discount.getProducts());
                discountMapper.addDiscountProduct(dp);
            }
        }
        return discountId;
    }

    @Override
    public <T> List<T> getDiscounts(DiscountSelector selector) {
        return discountMapper.getDiscounts(selector);
    }

    @Override
    public void updateDiscount(Discount discount) {
        discountMapper.updateDiscount(discount);
    }

    @Override
    public List<Discount> getInTimeOrderDiscounts(DateTime dateTime) {
        return null;
    }

    @Override
    public List<Discount> getInTimeProductDiscounts(DateTime dateTime) {
        return null;
    }
}
