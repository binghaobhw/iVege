package wbh.wilfred.ivege.data;

import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wbh.wilfred.ivege.model.Category;

import java.util.List;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CategoryAccessorMyBatisTest extends AccessorTest {
    @Autowired
    private CategoryAccessor categoryAccessor;

    private static final String TABLE = "category";

    @Before
    public void setUp() throws Exception {
        Operation operation = sequenceOf(deleteAllFrom(TABLE),
                insertInto(TABLE)
                        .columns("id", "name")
                        .values(1L, "有机蔬菜")
                        .values(2L, "面点")
                        .build());
        prepare(operation);
    }

    @Test
    public void testGetCategoryById() throws Exception {
        Category a = categoryAccessor.getCategoryById(1L);
        Category b = new Category();
        b.setId(1L);
        b.setName("有机蔬菜");
        assertEquals(b, a);
        skipNextDbSetup();
    }

    @Test
    public void testGetCategories() throws Exception {
        List<Category> a = categoryAccessor.getCategories(new Category());
        assertTrue(a.size() == 2);
        skipNextDbSetup();
    }

    @Test
    public void testAddCategory() throws Exception {
        Category a = new Category();
        a.setName("太阳眼镜");
        assertEquals(a.getId(), 0L);
        categoryAccessor.addCategory(a);
        assertNotEquals(a.getId(), 0L);
    }
}