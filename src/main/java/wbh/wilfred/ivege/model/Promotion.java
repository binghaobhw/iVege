package wbh.wilfred.ivege.model;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;

import java.util.List;

public abstract class Promotion {
    private long id;
    private String name;
    private boolean forAll;
    private List<Category> categories;
    private List<Product> products;
    private Rmb minTotal;
    private DateTime start;
    private DateTime end;
    private Status status;
    private DateTime createTime;

    public enum Status {
        ACTIVE, INACTIVE, DELETED
    }

    public boolean isForCategory(Category category) {
        return forAll || (CollectionUtils.isNotEmpty(categories) &&
                categories.contains(category));
    }

    public boolean isForProduct(Product product) {
        return forAll || isForCategory(product.getCategory()) ||
                (CollectionUtils.isNotEmpty(products) && products.contains
                (product));
    }

    public boolean isForAll() {
        return forAll;
    }

    public void setForAll(boolean forAll) {
        this.forAll = forAll;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }
}
