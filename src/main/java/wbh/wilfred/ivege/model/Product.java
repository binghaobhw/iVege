package wbh.wilfred.ivege.model;

import java.util.List;

public class Product {
    private long id;
    private Category category;
    private String name;
    private String info;
    private String thumbnail;
    private Rmb price;
    private Unit unit;
    private Status status;
    private List<String> images;

    public enum Status {
        AVAILABLE, SOLD_OUT, NOT_AVAILABLE, DELETED
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Rmb getPrice() {
        return price;
    }

    public void setPrice(Rmb price) {
        this.price = price;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status Status) {
        this.status = Status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Product) {
            Product x = (Product) obj;
            if (id == x.getId() &&
                    category.equals(x.getCategory()) &&
                    name.equals(x.getName()) &&
                    info.equals(x.getInfo()) &&
                    price.equals(x.getPrice()) &&
                    unit.equals(x.getUnit()) &&
                    images.equals(x.getImages()) &&
                    status.equals(x.getStatus()) &&
                    thumbnail.equals(x.getThumbnail())) {
                return true;
            }
        }
        return false;
    }
}
