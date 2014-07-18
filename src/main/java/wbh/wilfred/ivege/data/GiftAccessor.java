package wbh.wilfred.ivege.data;

import wbh.wilfred.ivege.model.Gift;
import wbh.wilfred.ivege.model.selector.PromotionSelector;

import java.util.List;

public interface GiftAccessor {
    public String addGift(Gift gift);
    public List<Gift> getGifts(PromotionSelector selector);
}
