package wbh.wilfred.ivege.service;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import wbh.wilfred.ivege.config.BusinessConfig;
import wbh.wilfred.ivege.model.Order;
import wbh.wilfred.ivege.model.OrderItem;
import wbh.wilfred.ivege.model.selector.OrderSelector;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = {BusinessConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testGetOrderById() throws Exception {
        Order order = orderService.getOrderById(1L);
        assertEquals("13613617229", order.getAddress());
    }

    @Test
    @Transactional
    public void testAddOrder() throws Exception {
        OrderItem orderItem1 = new OrderItem();
        OrderItem orderItem2 = new OrderItem();
        List<OrderItem> items = new ArrayList<OrderItem>();
        items.add(orderItem1);
        items.add(orderItem2);
        Order order = new Order();
        order.setAddress("南头湾30弄9号");
        order.setName("王炳浩");
        order.setPhone("13675869600");
        order.setDeliverTime(new DateTime().plusHours(5));
        order.setItems(items);
        order.confirmed();
        long orderId = order.getId();
        Order result = orderService.getOrderById(orderId);
        assertEquals(2, result.getItems().size());
    }

    @Test
    public void testGetOrders() throws Exception {
        OrderSelector orderSelector = new OrderSelector();
        orderSelector.setPhone("13613617229");
        orderSelector.setStartCreateTime(new DateTime("2014-07-03"));
        List<Order> orders = orderService.getOrders(orderSelector);
        assertEquals(1, orders.size());
        orderSelector.setEndDeliverTime(new DateTime("2014-07-05"));
        orderSelector.setAddress("lands");
        orderSelector.setEndCompleteTime(new DateTime("2014-07-06"));
        orderSelector.setMinAmount(new BigDecimal("11.5"));
        orderSelector.setMaxAmount(new BigDecimal("15"));
        orderSelector.setName("yeah");
        orderSelector.setStartDeliverTime(new DateTime("2014-07-07"));
        orderSelector.setStartCompleteTime(new DateTime("2014-06-04"));
        orderSelector.setStatus(Order.Status.CANCELED);
        orderSelector.setEndCreateTime(new DateTime("2016-07-04"));
        orders = orderService.getOrders(orderSelector);
        assertEquals(0, orders.size());
    }

    @Test
    public void testCalculateTotal() throws Exception {
        OrderItem orderItem1 = new OrderItem();
        OrderItem orderItem2 = new OrderItem();
        List<OrderItem> items = new ArrayList<OrderItem>();
        items.add(orderItem1);
        items.add(orderItem2);
        Order order = new Order();
        order.setAddress("南头湾30弄9号");
        order.setName("王炳浩");
        order.setPhone("13675869600");
        order.setDeliverTime(new DateTime().plusHours(5));
        order.setItems(items);
        order.confirmed();

    }

    @Test
    public void testCalculateFinalTotal() throws Exception {

    }

    @Test
    public void testCalculateBonus() throws Exception {

    }
}