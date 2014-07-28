package wbh.wilfred.ivege.service;

import org.joda.time.DateTime;
import org.junit.Test;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.PriceDiscount;
import wbh.wilfred.ivege.model.RateDiscount;
import wbh.wilfred.ivege.model.Rmb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DiscountChooserTest {

    @Test
    public void testBestDiscount() throws Exception {
        List<Discount> discounts = new ArrayList<Discount>();
        PriceDiscount a = new PriceDiscount();
        a.setPrice(new Rmb("40"));
        a.setCreateTime(new DateTime(2014, 7, 18, 1, 0));
        discounts.add(a);
        RateDiscount b = new RateDiscount();
        b.setRate(new BigDecimal("0.8"));
        b.setCreateTime(new DateTime(2014, 7, 18, 1, 0));
        discounts.add(b);
        PriceDiscount c = new PriceDiscount();
        c.setPrice(new Rmb("35"));
        c.setCreateTime(new DateTime(2014, 7, 18, 1, 0));
        discounts.add(c);
        RateDiscount d = new RateDiscount();
        d.setRate(new BigDecimal("0.70"));
        d.setCreateTime(new DateTime(2014, 7, 18, 0, 0));
        discounts.add(d);
        Discount e = DiscountChooser.bestDiscount(new Rmb("50"), discounts);
        assertEquals(c, e);
    }
}