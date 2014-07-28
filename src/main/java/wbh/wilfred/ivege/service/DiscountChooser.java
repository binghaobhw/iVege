package wbh.wilfred.ivege.service;

import org.apache.commons.collections4.CollectionUtils;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.Rmb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class DiscountChooser {
    private DiscountChooser() {}

    public static <T extends Discount> T bestDiscount(Rmb original,
                                         List<T> discounts) {
        if (CollectionUtils.isEmpty(discounts)) {
            throw new IllegalArgumentException();
        }
        Collections.sort(discounts);
        List<T> differentTypeBests = new ArrayList<T>();
        ListIterator<T> iterator = discounts.listIterator();
        T next = iterator.next();
        while (iterator.hasNext()) {
            T current = next;
            next = iterator.next();
            if (current.getClass() != next.getClass()) {
                differentTypeBests.add(current);
            }
        }
        differentTypeBests.add(next);
        T best = null;
        Rmb min = null;
        for (T discount: differentTypeBests) {
            Rmb current = discount.apply(original);
            if (min == null) {
                best = discount;
                min = current;
            } else {
                int d = current.compareTo(min);
                if (d < 0 || (d == 0 && discount.getCreateTime().isAfter(best
                        .getCreateTime()))) {
                    best = discount;
                    min = current;
                }
            }
        }
        return best;
    }
}
