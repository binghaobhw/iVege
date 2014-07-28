package wbh.wilfred.ivege.model;

public class Category {
    private long id;
    private String name;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Category) {
            Category x = (Category) obj;
            if (id == x.getId() && name.equals(x.getName())) {
                return true;
            }
        }
        return false;
    }
}
