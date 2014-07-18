package wbh.wilfred.ivege.data;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.query.Criteria;
import wbh.wilfred.ivege.model.Gift;
import wbh.wilfred.ivege.model.Promotion;
import wbh.wilfred.ivege.model.Rmb;
import wbh.wilfred.ivege.model.selector.PromotionSelector;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class GiftAccessorMongo extends AccessorMongo implements GiftAccessor {
    @Override
    public String addGift(Gift gift) {
        insert(gift);
        return gift.getId();
    }

    @Override
    public List<Gift> getGifts(PromotionSelector selector) {
        Rmb total = selector.getTotal();
        DateTime dateTime = selector.getDateTime();
        Promotion.Status status = selector.getStatus();
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
        if (criteria != null) {
            return find(query(criteria), Gift.class);
        } else {
            return findAll(Gift.class);
        }
    }

    @Override
    public String collectionName() {
        return "gift";
    }
}
