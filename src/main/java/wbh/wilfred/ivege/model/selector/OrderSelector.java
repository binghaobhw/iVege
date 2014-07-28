package wbh.wilfred.ivege.model.selector;

import org.joda.time.DateTime;
import wbh.wilfred.ivege.model.Order;

import java.math.BigDecimal;

public class OrderSelector {
    String address;
    String name;
    String phone;
    BigDecimal minAmount;
    BigDecimal maxAmount;
    DateTime startCreateTime;
    DateTime endCreateTime;
    DateTime startDeliverTime;
    DateTime endDeliverTime;
    DateTime startCompleteTime;
    DateTime endCompleteTime;
    Order.Status status;

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

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
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
}
