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
    DateTime startCreationTime;
    DateTime endCreationTime;
    DateTime startDeliveryTime;
    DateTime endDeliveryTime;
    DateTime startCompletionTime;
    DateTime endCompletionTime;
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

    public DateTime getStartCreationTime() {
        return startCreationTime;
    }

    public void setStartCreationTime(DateTime startCreationTime) {
        this.startCreationTime = startCreationTime;
    }

    public DateTime getEndCreationTime() {
        return endCreationTime;
    }

    public void setEndCreationTime(DateTime endCreationTime) {
        this.endCreationTime = endCreationTime;
    }

    public DateTime getStartDeliveryTime() {
        return startDeliveryTime;
    }

    public void setStartDeliveryTime(DateTime startDeliveryTime) {
        this.startDeliveryTime = startDeliveryTime;
    }

    public DateTime getEndDeliveryTime() {
        return endDeliveryTime;
    }

    public void setEndDeliveryTime(DateTime endDeliveryTime) {
        this.endDeliveryTime = endDeliveryTime;
    }

    public DateTime getStartCompletionTime() {
        return startCompletionTime;
    }

    public void setStartCompletionTime(DateTime startCompletionTime) {
        this.startCompletionTime = startCompletionTime;
    }

    public DateTime getEndCompletionTime() {
        return endCompletionTime;
    }

    public void setEndCompletionTime(DateTime endCompletionTime) {
        this.endCompletionTime = endCompletionTime;
    }

    public Order.Status getStatus() {
        return status;
    }

    public void setStatus(Order.Status status) {
        this.status = status;
    }
}
