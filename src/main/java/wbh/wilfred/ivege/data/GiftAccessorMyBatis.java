package wbh.wilfred.ivege.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wbh.wilfred.ivege.data.mybatis.mapper.GiftMapper;
import wbh.wilfred.ivege.model.Gift;
import wbh.wilfred.ivege.model.selector.GiftSelector;

import java.util.List;

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
        giftMapper.addGift(gift);
        return gift.getId();
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
