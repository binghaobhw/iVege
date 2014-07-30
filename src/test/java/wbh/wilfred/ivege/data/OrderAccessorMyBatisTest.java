package wbh.wilfred.ivege.data;

import com.ninja_squad.dbsetup.operation.Operation;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wbh.wilfred.ivege.model.Discount;
import wbh.wilfred.ivege.model.FlatDiscount;
import wbh.wilfred.ivege.model.Gift;
import wbh.wilfred.ivege.model.Order;
import wbh.wilfred.ivege.model.OrderItem;
import wbh.wilfred.ivege.model.PriceDiscount;
import wbh.wilfred.ivege.model.Product;
import wbh.wilfred.ivege.model.Rmb;
import wbh.wilfred.ivege.model.Unit;
import wbh.wilfred.ivege.model.selector.OrderSelector;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assert.assertEquals;

public class OrderAccessorMyBatisTest extends AccessorTest {
    @Autowired
    private OrderAccessor orderAccessor;

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
                        "product_id").values(2L, 3L).build(),
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
                        .values(2L, 1, "黄金甲限时特价6元", false, 0,
                                new DateTime("2014-07-28").toDate(),
                                new DateTime("2014-07-28T23:59:59").toDate()
                                , 0, new DateTime("2014-07-26").toDate(), 0, null,
                                60, null)
                        .build(),
                insertInto(DISCOUNT_CATEGORY).columns("discount_id",
                        "category_id").values(1L, 1L).build(),
                insertInto(DISCOUNT_PRODUCT).columns("discount_id",
                        "product_id").values(2L, 3L).build(),
                insertInto(ORDER).columns("id", "name", "phone", "address",
                        "original_total", "total", "create_time",
                        "deliver_time", "status",
                        "discount_id", "gift_id", "source").values(1L,
                        "陈欢乐",
                        "13675869600", "顺发巷11号", 600L, 500L,
                        new DateTime("2014-07-29T11:30").toDate(),
                        new DateTime("2014-07-29T11:40").toDate(), 1, 1L,
                        1L, 0).build(),
                insertInto(ORDER_ITEM).columns("id", "order_id", "product_id",
                        "discount_id", "user_quantity", "user_unit", "quantity",
                        "unit", "price", "subtotal").values(1L, 1L, 3L, 2L,
                        40, 1, 10, 0, 60, 600).build());
        prepare(operation);
    }

    @Test
    public void testGetOrderById() throws Exception {
        Order o = orderAccessor.getOrderById(1L);
        assertEquals(1L, o.getDiscount().getId());
        assertEquals(2L, o.getGift().getProduct().getId());
        skipNextDbSetup();
    }

    @Test
    public void testAddOrder() throws Exception {
        Discount d = new FlatDiscount();
        d.setId(1L);
        Gift g = new Gift();
        g.setId(1L);
        List<OrderItem> i = new ArrayList<OrderItem>();
        OrderItem oi = new OrderItem();
        Product p = new Product();
        p.setId(1L);
        oi.setProduct(p);
        Discount d2 = new PriceDiscount();
        d2.setId(2L);
        oi.setDiscount(d2);
        oi.setUserQuantity(BigDecimal.TEN);
        oi.setUserUnit(Unit.JIN);
        oi.setQuantity(BigDecimal.TEN);
        oi.setUnit(Unit.JIN);
        oi.setPrice(new Rmb("3"));
        oi.setSubtotal(new Rmb("30"));
        i.add(oi);
        Order o = new Order();
        o.setName("陈欢乐");
        o.setPhone("13675869600");
        o.setAddress("顺发巷11号");
        o.setDeliverTime(new DateTime("2014-08-02T10:00"));
        o.setDiscount(d);
        o.setGift(g);
        o.setItems(i);
        o.setOriginalTotal(new Rmb("37"));
        o.setTotal(new Rmb("35"));
        o.setStatus(Order.Status.DELIVERING);
        o.setSource(Order.Source.PHONE);
        long id = orderAccessor.addOrder(o);
        Order o2 = orderAccessor.getOrderById(id);
        assertEquals(o.getDiscount().getId(), o2.getDiscount().getId());
        assertEquals(o.getGift().getId(), o2.getGift().getId());
        assertEquals(o.getSource(), o2.getSource());
    }

    @Test
    public void testGetOrders() throws Exception {
        OrderSelector selector = new OrderSelector();
        selector.setName("陈欢乐");
        List<Order> orders = orderAccessor.getOrders(selector);
        assertEquals(1, orders.size());
        skipNextDbSetup();
    }

    @Test
    public void testUpdateOrder() throws Exception {
        Order o = new Order();
        o.setId(1L);
        o.setStatus(Order.Status.CANCELED);
        orderAccessor.updateOrder(o);
        Order o2 = orderAccessor.getOrderById(1L);
        assertEquals(o.getStatus(), o2.getStatus());
    }
}