package wbh.wilfred.ivege.model;


import org.joda.time.DateTime;

import java.util.List;

public class Order {
    private long id;
    private String name;
    private String phone;
    private String address;
    private List<OrderItem> items;
    private Rmb originalTotal;
    private Rmb total;
    private DateTime createTime;
    private DateTime deliverTime;
    private DateTime completeTime;
    private Status status;
    private Discount discount;
    private Gift gift;
    private Source source;
    private String ext;

    public enum Status {
        UNCONFIRMED, CONFIRMED, DELIVERING, COMPLETED, CANCELED
    }

    public enum Source {
        PHONE, WECHET, SHOP
    }

    public Rmb getTotal() {
        return total;
    }

    public void setTotal(Rmb total) {
        this.total = total;
    }

    public void confirmed() {
        status = Status.CONFIRMED;
    }

    public void unconfirmed() {
        status = Status.UNCONFIRMED;
    }

    public void canceled() {
        status = Status.CANCELED;
    }

    public void completed() {
        status = Status.COMPLETED;
    }

    public void delivering() {
        status = Status.DELIVERING;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public DateTime getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(DateTime completeTime) {
        this.completeTime = completeTime;
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

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }

    public DateTime getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(DateTime deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
