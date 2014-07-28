package wbh.wilfred.ivege.data.mybatis.mapper;

import wbh.wilfred.ivege.model.Category;

import java.util.List;

public interface CategoryMapper {
    public Category getCategoryById(long id);
    public List<Category> getCategories(Category selector);
    public long addCategory(Category category);
}
