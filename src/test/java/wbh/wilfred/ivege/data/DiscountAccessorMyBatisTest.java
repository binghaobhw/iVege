package wbh.wilfred.ivege.data;

import com.ninja_squad.dbsetup.operation.Operation;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wbh.wilfred.ivege.model.Category;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.PriceDiscount;
import wbh.wilfred.ivege.model.Product;
import wbh.wilfred.ivege.model.Promotion;
import wbh.wilfred.ivege.model.RateDiscount;
import wbh.wilfred.ivege.model.Rmb;
import wbh.wilfred.ivege.model.selector.DiscountSelector;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class DiscountAccessorMyBatisTest extends AccessorTest {
    @Autowired
    private DiscountAccessor discountAccessor;
    private static final String DISCOUNT = "discount";
    private static final String PRODUCT = "product";
    private static final String CATEGORY = "category";
    private static final String DISCOUNT_CATEGORY = "discount_category";
    private static final String DISCOUNT_PRODUCT = "discount_product";
    @Before
    public void setUp() throws Exception {
        Operation operation = sequenceOf(deleteAllFrom(DISCOUNT_CATEGORY,
                        DISCOUNT_PRODUCT,
                        DISCOUNT,
                        PRODUCT, CATEGORY),
                insertInto(CATEGORY)
                        .columns("id", "name")
                        .values(1L, "有机蔬菜")
                        .values(2L, "面点")
                        .build(),
                insertInto(PRODUCT)
                        .columns("id", "category_id", "name", "info",
                                "thumbnail", "price", "unit", "status", "images")
                        .values(1L, 1L, "豆芽", "营养丰富", "123/1.jpg", 25, 0,
                                0, "[\"123/2.jpg\",\"123/3.jpg\"]\\java.util.ArrayList")
                        .values(2L, 1L, "球菜", "东北叫大头菜", "234/1.jpg", 50, 0,
                                1, "[\"234/2.jpg\",\"234/3.jpg\"]\\java.util.ArrayList")
                        .values(3L, 1L, "黄金甲", "秋葵", "345/1.jpg", 80, 0,
                                1, "[\"345/2.jpg\",\"345/3.jpg\"]\\java.util.ArrayList")
                        .build(),
                insertInto(DISCOUNT)
                        .columns("id", "_class", "name", "for_all",
                                "min_total",
                                "start", "end", "status", "create_time",
                                "type", "value", "price", "rate")
                        .values(1L, 0, "全场满50减10", true, 500,
                                new DateTime("2014-07-27").toDate(),
                                new DateTime("2014-08-02").toDate(), 0,
                                new DateTime("2014-07-26").toDate(), 1, 100, null,
                                null)
                        .values(2L, 1, "黄金甲限时特价", false, 0,
                                new DateTime("2014-07-28").toDate(),
                                new DateTime("2014-07-28T23:59:59").toDate()
                                , 0, new DateTime("2014-07-26").toDate(), 0, null,
                                60, null)
                        .build(),
                insertInto(DISCOUNT_CATEGORY).columns("discount_id",
                        "category_id").values(1L, 1L).build(),
                insertInto(DISCOUNT_PRODUCT).columns("discount_id",
                        "product_id").values(2L, 3L).build());
        prepare(operation);
    }

    @Test
    public void testAddDiscount() throws Exception {
        Category c = new Category();
        c.setId(2L);
        List<Category> categories = new ArrayList<Category>();
        categories.add(c);
        Product p = new Product();
        p.setId(1L);
        Product p2 = new Product();
        p2.setId(2L);
        List<Product> products = new ArrayList<Product>();
        products.add(p);
        products.add(p2);
        RateDiscount a = new RateDiscount();
        a.setName("全部商品8折");
        a.setStatus(Promotion.Status.INACTIVE);
        a.setType(Discount.Type.PRODUCT);
        a.setStart(new DateTime("2014-08-01"));
        a.setEnd(new DateTime("2014-08-02T23:59:59"));
        a.setRate(new BigDecimal("0.80"));
        a.setForAll(false);
        a.setMinTotal(new Rmb("0"));
        a.setCategories(categories);
        a.setProducts(products);
        long id = discountAccessor.addDiscount(a);
        Discount d = discountAccessor.getDiscountById(id);
        assertNotNull(d.getCategories());
        assertNotNull(d.getProducts());
        assertEquals(a.getCategories().get(0).getId(), d.getCategories().get(0)
                .getId());
        assertEquals(a.getProducts().get(0).getId(), d.getProducts().get(0).getId());
        assertEquals(a.getProducts().get(1).getId(), d.getProducts().get(1)
                .getId());
        assertNotEquals(0L, id);
    }

    @Test
    public void testGetDiscounts() throws Exception {
        DiscountSelector selector = new DiscountSelector();
        selector.setDateTime(new DateTime("2014-07-28T12:00"));
        selector.setStatus(Promotion.Status.ACTIVE);
        List<Discount> discounts = discountAccessor.getDiscounts(selector);
        assertEquals(2, discounts.size());
        skipNextDbSetup();
    }

    @Test
    public void testUpdateDiscount() throws Exception {
        PriceDiscount discount = new PriceDiscount();
        discount.setId(2L);
        discount.setEnd(new DateTime("2014-08-03T12:00"));
        discount.setStatus(Promotion.Status.DELETED);
        discountAccessor.updateDiscount(discount);
        Discount d = discountAccessor.getDiscountById(2L);
        assertEquals(discount.getClass(), d.getClass());
        assertEquals(discount.getEnd(), d.getEnd());
        assertEquals(discount.getStatus(), d.getStatus());
    }
}