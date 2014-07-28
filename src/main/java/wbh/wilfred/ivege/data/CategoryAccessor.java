package wbh.wilfred.ivege.data;

import wbh.wilfred.ivege.model.Category;

import java.util.List;

public interface CategoryAccessor {
    public Category getCategoryById(long id);
    public List<Category> getCategories(Category selector);
    public long addCategory(Category category);
}
