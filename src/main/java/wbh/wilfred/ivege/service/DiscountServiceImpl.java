package wbh.wilfred.ivege.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wbh.wilfred.ivege.data.DiscountAccessor;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.selector.DiscountSelector;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountAccessor discountAccessor;

    @Override
    public Discount getDiscountById(long id) {
        return discountAccessor.getDiscountById(id);
    }

    @Override
    public long addDiscount(Discount discount) {
        return discountAccessor.addDiscount(discount);
    }

    @Override
    public List<Discount> getDiscounts(DiscountSelector selector) {
        return discountAccessor.getDiscounts(selector);
    }

    @Override
    public void updateDiscount(Discount discount) {
        discountAccessor.updateDiscount(discount);
    }
}
