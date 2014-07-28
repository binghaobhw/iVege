package wbh.wilfred.ivege.data.mybatis.mapper;

import wbh.wilfred.ivege.model.Gift;
import wbh.wilfred.ivege.model.selector.GiftSelector;

import java.util.List;
import java.util.Map;

public interface GiftMapper {
    public Gift getGiftById(long id);
    public long addGift(Gift gift);
    public long addGiftCategory(Map<String, Object> p);
    public long addGiftProduct(Map<String, Object> p);
    public List<Gift> getGifts(GiftSelector selector);
    public void updateGift(Gift gift);
}
