package wbh.wilfred.ivege.data;

import com.ninja_squad.dbsetup.operation.Operation;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wbh.wilfred.ivege.model.Category;
import wbh.wilfred.ivege.model.Gift;
import wbh.wilfred.ivege.model.Product;
import wbh.wilfred.ivege.model.Promotion;
import wbh.wilfred.ivege.model.Rmb;
import wbh.wilfred.ivege.model.Unit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assert.assertEquals;

public class GiftAccessorMyBatisTest extends AccessorTest {
    @Autowired
    private GiftAccessor giftAccessor;

    @Before
    public void setUp() throws Exception {
        Operation operation = sequenceOf(
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
                insertInto(GIFT)
                        .columns("id", "name", "for_all",
                                "min_total",
                                "start", "end", "status", "create_time",
                                "product_id", "quantity", "unit")
                        .values(1L, "订单满50元送球菜1个", true, 500,
                                new DateTime("2014-07-27").toDate(),
                                new DateTime("2014-08-02").toDate(), 0,
                                new DateTime("2014-07-26").toDate(), 2L, 1, 1)
                        .values(2L, "黄金甲买3斤送1斤", false, 240,
                                new DateTime("2014-07-28").toDate(),
                                new DateTime("2014-07-28T23:59:59").toDate()
                                , 0, new DateTime("2014-07-26").toDate(), 3L,
                                1, 0)
                        .build(),
                insertInto(GIFT_CATEGORY).columns("gift_id",
                        "category_id").values(1L, 1L).build(),
                insertInto(GIFT_PRODUCT).columns("gift_id",
                        "product_id").values(2L, 3L).build());
        prepare(operation);
    }

    @Test
    public void testGetGiftById() throws Exception {
        Gift g = giftAccessor.getGiftById(2L);
        List<String> l = new ArrayList<String>();
        l.add("345/2.jpg");
        l.add("345/3.jpg");
        Category c = new Category();
        c.setId(1L);
        c.setName("有机蔬菜");
        Product p = new Product();
        p.setId(3L);
        p.setName("黄金甲");
        p.setInfo("秋葵");
        p.setPrice(new Rmb("8"));
        p.setImages(l);
        p.setThumbnail("345/1.jpg");
        p.setUnit(Unit.JIN);
        p.setCategory(c);
        p.setStatus(Product.Status.SOLD_OUT);
        assertEquals(p, g.getProducts().get(0));
    }

    @Test
    public void testAddGift() throws Exception {
        Product p = new Product();
        p.setId(1L);
        Gift g = new Gift();
        g.setCategories(null);
        g.setProducts(null);
        g.setProduct(p);
        g.setStatus(Promotion.Status.ACTIVE);
        g.setMinTotal(Rmb.ZERO);
        g.setName("订单满10元送豆芽1斤");
        g.setQuantity(new BigDecimal("1"));
        g.setUnit(Unit.JIN);
        g.setStart(new DateTime("2014-07-28"));
        g.setEnd(new DateTime("2014-07-30"));
        g.setForAll(true);
        giftAccessor.addGift(g);
    }

    @Test
    public void testGetGifts() throws Exception {

    }

    @Test
    public void testUpdateGift() throws Exception {

    }
}