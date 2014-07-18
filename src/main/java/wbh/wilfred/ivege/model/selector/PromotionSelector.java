package wbh.wilfred.ivege.model.selector;

import org.joda.time.DateTime;
import wbh.wilfred.ivege.model.Promotion;
import wbh.wilfred.ivege.model.Rmb;

public class PromotionSelector {
    private Rmb total;
    private DateTime dateTime;
    private Promotion.Status status;

    public Rmb getTotal() {
        return total;
    }

    public void setTotal(Rmb total) {
        this.total = total;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Promotion.Status getStatus() {
        return status;
    }

    public void setStatus(Promotion.Status status) {
        this.status = status;
    }
}
