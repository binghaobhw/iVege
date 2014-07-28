package wbh.wilfred.ivege.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wbh.wilfred.ivege.data.mybatis.mapper.CategoryMapper;
import wbh.wilfred.ivege.model.Category;

import java.util.List;

@Repository
public class CategoryAccessorMyBatis implements CategoryAccessor {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category getCategoryById(long id) {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public List<Category> getCategories(Category selector) {
        return categoryMapper.getCategories(selector);
    }

    @Override
    public long addCategory(Category category) {
        categoryMapper.addCategory(category);
        return category.getId();
    }
}
