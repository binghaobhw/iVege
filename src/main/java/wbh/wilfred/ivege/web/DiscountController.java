package wbh.wilfred.ivege.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.FlatDiscount;
import wbh.wilfred.ivege.model.PriceDiscount;
import wbh.wilfred.ivege.model.RateDiscount;
import wbh.wilfred.ivege.model.selector.DiscountSelector;
import wbh.wilfred.ivege.service.DiscountService;

import java.util.List;

@RestController
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @RequestMapping(value = "/flat-discounts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long addFlatDiscount(@RequestBody FlatDiscount flatDiscount) {
        return discountService.addDiscount(flatDiscount);
    }

    @RequestMapping(value = "/price-discounts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long addPriceDiscount(@RequestBody PriceDiscount priceDiscount) {
        return discountService.addDiscount(priceDiscount);
    }

    @RequestMapping(value = "/rate-discounts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long addRateDiscount(@RequestBody RateDiscount rateDiscount) {
        return discountService.addDiscount(rateDiscount);
    }

    @RequestMapping(value = "/discounts", method = RequestMethod.GET)
    public List<Discount> getDiscounts(DiscountSelector discountSelector) {
        return discountService.getDiscounts(discountSelector);
    }

}
