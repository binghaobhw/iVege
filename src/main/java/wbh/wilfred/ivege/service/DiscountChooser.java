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

    public static Discount bestDiscount(Rmb original, List<Discount> discounts) {
        if (CollectionUtils.isEmpty(discounts)) {
            throw new IllegalArgumentException();
        }
        Collections.sort(discounts);
        List<Discount> differentTypeBests = new ArrayList<Discount>();
        ListIterator<Discount> iterator = discounts.listIterator();
        Discount next = iterator.next();
        while (iterator.hasNext()) {
            Discount current = next;
            next = iterator.next();
            if (current.getClass() != next.getClass()) {
                differentTypeBests.add(current);
            }
        }
        differentTypeBests.add(next);
        Discount best = null;
        Rmb min = null;
        for (Discount discount: differentTypeBests) {
            Rmb current = discount.calculate(original);
            if (min == null) {
                best = discount;
                min = current;
            } else {
                int d = current.compareTo(min);
                if (d < 0 || (d == 0 && discount.getCreationTime().isAfter(best
                        .getCreationTime()))) {
                    best = discount;
                    min = current;
                }
            }
        }
        return best;
    }
}
