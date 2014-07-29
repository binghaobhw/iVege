package wbh.wilfred.ivege.data;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wbh.wilfred.ivege.data.mybatis.mapper.GiftMapper;
import wbh.wilfred.ivege.model.Gift;
import wbh.wilfred.ivege.model.selector.GiftSelector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GiftAccessorMyBatis implements GiftAccessor {
    @Autowired
    private GiftMapper giftMapper;

    @Override
    public Gift getGiftById(long id) {
        return giftMapper.getGiftById(id);
    }

    @Override
    public long addGift(Gift gift) {
        gift.setCreateTime(DateTime.now());
        giftMapper.addGift(gift);
        long giftId = gift.getId();
        if (!gift.isForAll()) {
            if (CollectionUtils.isNotEmpty(gift.getCategories())) {
                Map<String, Object> dc = new HashMap<String, Object>();
                dc.put("giftId", giftId);
                dc.put("categories", gift.getCategories());
                giftMapper.addGiftCategory(dc);
            }
            if (CollectionUtils.isNotEmpty(gift.getProducts())) {
                Map<String, Object> dp = new HashMap<String, Object>();
                dp.put("giftId", giftId);
                dp.put("products", gift.getProducts());
                giftMapper.addGiftProduct(dp);
            }
        }
        return giftId;
    }

    @Override
    public List<Gift> getGifts(GiftSelector selector) {
        return giftMapper.getGifts(selector);
    }

    @Override
    public void updateGift(Gift gift) {
        giftMapper.updateGift(gift);
    }
}
