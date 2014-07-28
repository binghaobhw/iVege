package wbh.wilfred.ivege.service;

import org.apache.commons.collections4.CollectionUtils;
import wbh.wilfred.ivege.model.Gift;

import java.util.Collections;
import java.util.List;

public class GiftChooser {
    private GiftChooser() {
    }

    public static Gift bestGift(List<Gift> gifts) {
        if (CollectionUtils.isEmpty(gifts)) {
            throw new IllegalArgumentException();
        }
        Collections.sort(gifts);
        return gifts.get(gifts.size() - 1);
    }
}
