package wbh.wilfred.ivege.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import wbh.wilfred.ivege.model.Order;
import wbh.wilfred.ivege.model.selector.OrderSelector;
import wbh.wilfred.ivege.service.OrderService;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public Order getOrderById(@PathVariable long id) {
        return orderService.getOrderById(id);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getOrders(OrderSelector orderSelector) {
        return orderService.getOrders(orderSelector);
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PUT)
    public Order updateOrder(@PathVariable String id, @RequestBody Order order) {
        if (order.getStatus() == Order.Status.CONFIRMED) {
        }
        orderService.updateOrder(order);
        return order;
    }
}
