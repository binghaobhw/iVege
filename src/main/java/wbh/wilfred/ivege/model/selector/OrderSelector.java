package wbh.wilfred.ivege.model.selector;

import org.joda.time.DateTime;
import wbh.wilfred.ivege.model.Order;

import java.math.BigDecimal;

public class OrderSelector {
    private String name;
    private String address;
    private String phone;
    private BigDecimal minTotal;
    private BigDecimal maxTotal;
    private DateTime startCreateTime;
    private DateTime endCreateTime;
    private DateTime startDeliverTime;
    private DateTime endDeliverTime;
    private DateTime startCompleteTime;
    private DateTime endCompleteTime;
    private Order.Status status;
    private Order.Source source;
    private int offset;
    private int rows;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getMinTotal() {
        return minTotal;
    }

    public void setMinTotal(BigDecimal minTotal) {
        this.minTotal = minTotal;
    }

    public BigDecimal getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(BigDecimal maxTotal) {
        this.maxTotal = maxTotal;
    }

    public DateTime getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(DateTime startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public DateTime getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(DateTime endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public DateTime getStartDeliverTime() {
        return startDeliverTime;
    }

    public void setStartDeliverTime(DateTime startDeliverTime) {
        this.startDeliverTime = startDeliverTime;
    }

    public DateTime getEndDeliverTime() {
        return endDeliverTime;
    }

    public void setEndDeliverTime(DateTime endDeliverTime) {
        this.endDeliverTime = endDeliverTime;
    }

    public DateTime getStartCompleteTime() {
        return startCompleteTime;
    }

    public void setStartCompleteTime(DateTime startCompleteTime) {
        this.startCompleteTime = startCompleteTime;
    }

    public DateTime getEndCompleteTime() {
        return endCompleteTime;
    }

    public void setEndCompleteTime(DateTime endCompleteTime) {
        this.endCompleteTime = endCompleteTime;
    }

    public Order.Status getStatus() {
        return status;
    }

    public void setStatus(Order.Status status) {
        this.status = status;
    }

    public Order.Source getSource() {
        return source;
    }

    public void setSource(Order.Source source) {
        this.source = source;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
