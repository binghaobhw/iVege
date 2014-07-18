package wbh.wilfred.ivege.data;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.Promotion;
import wbh.wilfred.ivege.model.Rmb;
import wbh.wilfred.ivege.model.selector.DiscountSelector;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class DiscountAccessorMongo extends AccessorMongo implements
        DiscountAccessor {

    @Override
    public String addDiscount(Discount discount) {
        insert(discount);
        return discount.getId();
    }

    @Override
    public List<Discount> getDiscounts(DiscountSelector selector) {
        Rmb total = selector.getTotal();
        DateTime dateTime = selector.getDateTime();
        Promotion.Status status = selector.getStatus();
        Discount.Type type = selector.getType();
        Criteria criteria = null;
        if (total != null) {
            criteria = where("minTotal").lte(selector.getTotal());
        }
        if (dateTime != null) {
            criteria = (criteria != null)? criteria.and("start"): where
                    ("start");
            criteria = criteria.lte(dateTime).and("end").gte(dateTime);
        }
        if (status != null) {
            criteria = (criteria != null)? criteria.and("status"): where
                    ("status");
            criteria = criteria.is(status);
        }
        if (type != null) {
            criteria = (criteria != null)? criteria.and("type"): where
                    ("type");
            criteria = criteria.is(type);
        }
        if (criteria != null) {
            return find(query(criteria), Discount.class);
        } else {
            return findAll(Discount.class);
        }
    }

    @Override
    public List<Discount> getInTimeDiscounts(DateTime dateTime) {
        return getInTimeDiscounts(dateTime, null);
    }

    @Override
    public List<Discount> getInTimeDiscounts(DateTime dateTime, Discount.Type type) {
        DiscountSelector selector = new DiscountSelector();
        selector.setDateTime(dateTime);
        selector.setStatus(Promotion.Status.ACTIVE);
        selector.setType(type);
        return getDiscounts(selector);
    }

    @Override
    public String collectionName() {
        return "discount";
    }
}
