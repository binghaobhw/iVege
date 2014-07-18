package wbh.wilfred.ivege.model;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;

import java.util.List;

public abstract class Promotion {
    private String id;
    private String name;
    private boolean forAll;
    private List<String> categoryIds;
    private List<String> productIds;
    private Rmb minTotal;
    private DateTime start;
    private DateTime end;
    private Status status;
    private DateTime creationTime;

    public enum Status {
        ACTIVE, INACTIVE, DELETED
    }

    public boolean isForCategory(String categoryId) {
        return forAll || (CollectionUtils.isNotEmpty(categoryIds) &&
                categoryIds.contains(categoryId));
    }

    public boolean isForProduct(String productId) {
        return forAll || (CollectionUtils.isNotEmpty(productIds) &&
                productIds.contains(productId));
    }

    public boolean isForAll() {
        return forAll;
    }

    public void setForAll(boolean forAll) {
        this.forAll = forAll;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Rmb getMinTotal() {
        return minTotal;
    }

    public void setMinTotal(Rmb minTotal) {
        this.minTotal = minTotal;
    }

    public DateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(DateTime creationTime) {
        this.creationTime = creationTime;
    }
}
