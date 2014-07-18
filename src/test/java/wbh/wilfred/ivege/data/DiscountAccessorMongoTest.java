package wbh.wilfred.ivege.data;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wbh.wilfred.ivege.config.DataConfig;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.PriceDiscount;
import wbh.wilfred.ivege.model.Promotion;
import wbh.wilfred.ivege.model.RateDiscount;
import wbh.wilfred.ivege.model.Rmb;
import wbh.wilfred.ivege.model.selector.DiscountSelector;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = {DataConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class DiscountAccessorMongoTest {
    @Autowired
    private DiscountAccessor discountAccessor;

    @Test
    public void testAddDiscount() throws Exception {
        PriceDiscount priceDiscount = new PriceDiscount();
        priceDiscount.setName("满10元减2元");
        priceDiscount.setForAll(true);
        priceDiscount.setMinTotal(new Rmb("10"));
        priceDiscount.setStart(new DateTime("2055-12-01T00:00"));
        priceDiscount.setEnd(new DateTime("2055-12-31T23:59:59"));
        priceDiscount.setStatus(Promotion.Status.ACTIVE);
        priceDiscount.setType(Discount.Type.ORDER);
        priceDiscount.setPrice(new Rmb("8"));
        String id = discountAccessor.addDiscount(priceDiscount);
        RateDiscount rateDiscount = new RateDiscount();
        rateDiscount.setStatus(Promotion.Status.ACTIVE);
        rateDiscount.setStart(new DateTime("2055-12-15T00:00"));
        rateDiscount.setEnd(new DateTime("2055-12-15T23:59:59"));
        rateDiscount.setForAll(false);
        List<String> ids = new ArrayList<String>();
        ids.add(id);
        rateDiscount.setProductIds(ids);
        rateDiscount.setMinTotal(new Rmb("10"));
        rateDiscount.setName("8折");
        rateDiscount.setRate(new BigDecimal("0.80"));
        discountAccessor.addDiscount(rateDiscount);
    }

    @Test
    public void testGetDiscounts() throws Exception {
        DiscountSelector selector = new DiscountSelector();
        selector.setDateTime(new DateTime("2055-12-02T13:55"));
        selector.setStatus(Promotion.Status.ACTIVE);
        selector.setTotal(new Rmb("15"));
        selector.setType(Discount.Type.ORDER);
        List<Discount> discounts = discountAccessor.getDiscounts(selector);
        assertTrue(discounts.size() == 1);
        assertTrue(discounts.get(0).getEnd().equals(new DateTime
                ("2055-12-31T23:59:59")));

    }
}