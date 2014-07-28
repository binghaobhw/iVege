package wbh.wilfred.ivege.data;

import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wbh.wilfred.ivege.model.Category;
import wbh.wilfred.ivege.model.Product;
import wbh.wilfred.ivege.model.Rmb;
import wbh.wilfred.ivege.model.Unit;
import wbh.wilfred.ivege.model.selector.ProductSelector;

import java.util.ArrayList;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ProductAccessorMyBatisTest extends AccessorTest {
    @Autowired
    private ProductAccessor productAccessor;
    private static final String TABLE = "product";
    private static final String REFERENCED_TABLE = "category";
    private List<Product> expected;

    @Before
    public void setUp() throws Exception {
        Operation operation = sequenceOf(deleteAllFrom(TABLE, REFERENCED_TABLE),
                insertInto(REFERENCED_TABLE)
                        .columns("id", "name")
                        .values(1L, "有机蔬菜")
                        .values(2L, "面点")
                        .build(),
                insertInto(TABLE)
                        .columns("id", "category_id", "name", "info",
                                "thumbnail", "price", "unit", "status", "images")
                        .values(1L, 1L, "豆芽", "营养丰富", "123/1.jpg", "25", 0,
                                0, "[\"123/2.jpg\",\"123/3.jpg\"]\\java.util.ArrayList")
                        .values(2L, 1L, "球菜", "东北叫大头菜", "234/1.jpg", "50", 0,
                                1, "[\"234/2.jpg\",\"234/3.jpg\"]\\java.util" +
                                        ".ArrayList")
                        .build());
        prepare(operation);
        expected = new ArrayList<Product>();
        Product a = new Product();
        a.setId(1L);
        a.setName("豆芽");
        Category c = new Category();
        c.setId(1L);
        c.setName("有机蔬菜");
        a.setCategory(c);
        a.setInfo("营养丰富");
        a.setThumbnail("123/1.jpg");
        a.setPrice(new Rmb("2.5"));
        a.setUnit(Unit.JIN);
        a.setStatus(Product.Status.AVAILABLE);
        List<String> l = new ArrayList<String>();
        l.add("123/2.jpg");
        l.add("123/3.jpg");
        a.setImages(l);
        Product b = new Product();
        b.setId(2L);
        b.setName("球菜");
        b.setCategory(c);
        b.setInfo("东北叫大头菜");
        b.setThumbnail("234/1.jpg");
        b.setPrice(new Rmb("5.0"));
        b.setUnit(Unit.JIN);
        b.setStatus(Product.Status.SOLD_OUT);
        List<String> l1 = new ArrayList<String>();
        l1.add("234/2.jpg");
        l1.add("234/3.jpg");
        b.setImages(l1);
        expected.add(a);
        expected.add(b);
    }
    @Test
    public void testGetProductById() throws Exception {
        Product p = productAccessor.getProductById(1L);
        assertEquals(expected.get(0), p);
        skipNextDbSetup();
    }

    @Test
    public void testAddProduct() throws Exception {
        Product p = new Product();
        p.setName("豆芽");
        Category c = new Category();
        c.setId(1L);
        c.setName("蔬菜");
        p.setCategory(c);
        p.setStatus(Product.Status.AVAILABLE);
        p.setInfo("营养丰富");
        p.setThumbnail("temp/1.jpg");
        List<String> images = new ArrayList<String>();
        images.add("temp/2.jpg");
        images.add("temp/3.jpg");
        p.setImages(images);
        p.setPrice(new Rmb("2.0"));
        p.setUnit(Unit.JIN);
        long id = productAccessor.addProduct(p);
        assertNotEquals(id, 0L);
    }

    @Test
    public void testGetProducts() throws Exception {
        ProductSelector selector = new ProductSelector();
        List<Product> products = productAccessor.getProducts(selector);
        assertEquals(expected, products);
        skipNextDbSetup();
    }
}