package wbh.wilfred.ivege.model;


import org.joda.time.DateTime;

import java.util.List;

public class Order {
    private String id;
    private String address;
    private String name;
    private String phone;
    private Rmb originalTotal;
    private Rmb total;
    private DateTime creationTime;
    private DateTime deliveryTime;
    private DateTime completionTime;
    private Status status;
    private List<OrderItem> items;
    private String discountId;
    private String discountName;
    private String bonusId;
    private String bonusName;

    public enum Status {
        VALID, DELIVERING, COMPLETE, CANCELED
    }

    public Rmb getTotal() {
        return total;
    }

    public void setTotal(Rmb total) {
        this.total = total;
    }

    public void valid() {
        status = Status.VALID;
    }

    public void canceled() {
        status = Status.CANCELED;
    }

    public void complete() {
        status = Status.COMPLETE;
    }

    public void delivering() {
        status = Status.DELIVERING;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rmb getOriginalTotal() {
        return originalTotal;
    }

    public void setOriginalTotal(Rmb originalTotal) {
        this.originalTotal = originalTotal;
    }

    public DateTime getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(DateTime completionTime) {
        this.completionTime = completionTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(DateTime creationTime) {
        this.creationTime = creationTime;
    }

    public DateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(DateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getBonusId() {
        return bonusId;
    }

    public void setBonusId(String bonusId) {
        this.bonusId = bonusId;
    }

    public String getBonusName() {
        return bonusName;
    }

    public void setBonusName(String bonusName) {
        this.bonusName = bonusName;
    }
}
