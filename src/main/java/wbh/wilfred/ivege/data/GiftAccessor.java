package wbh.wilfred.ivege.data;

import wbh.wilfred.ivege.model.Gift;
import wbh.wilfred.ivege.model.selector.GiftSelector;

import java.util.List;

public interface GiftAccessor {
    public Gift getGiftById(long id);
    public long addGift(Gift gift);
    public List<Gift> getGifts(GiftSelector selector);
    public void updateGift(Gift gift);
}
