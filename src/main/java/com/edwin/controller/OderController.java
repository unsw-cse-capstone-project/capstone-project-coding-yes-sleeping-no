package com.edwin.controller;

import com.edwin.entity.Order;
import com.edwin.entity.User;
import com.edwin.service.OrderService;
import com.edwin.utlis.Consts;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/order")
/**
 * order status: 0 not pay
 * order status: 1 have paid
 */
public class OderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("create one order router in order controller")
    @PostMapping("/create")
    public Map<String, Object> create(Integer eventId, Integer ticketAmount, HttpSession session){
        Map<String, Object> map = new HashMap<>();
        User currentUser = (User)session.getAttribute(Consts.CURRENT_USER);
        try {
            Order currentOrder = orderService.create(eventId, ticketAmount, currentUser);
            session.setAttribute(Consts.CURRENT_ORDER, currentOrder);
            map.put("state", true);
            map.put("msg", "create order success");
            map.put("order", currentOrder);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", true);
            map.put("msg", e.getMessage());
            return map;
        }
    }

    @ApiOperation("confirm the payment for one order router in order controller")
    @PostMapping("/confirm")
    public Map<String, Object> update(Integer orderId, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        User currentUser = (User)session.getAttribute(Consts.CURRENT_USER);
        try {
            Order updateOrder = orderService.update(orderId, currentUser);
            session.setAttribute(Consts.CURRENT_ORDER, updateOrder);
            map.put("state", true);
            map.put("msg", "confirm order payment success");
            map.put("order", updateOrder);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", true);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @ApiOperation("get all orders for one user router in order controller")
    @GetMapping("/get")
    public Map<String, Object> get(HttpSession session){
        Map<String, Object> map = new HashMap<>();
        User currentUser = (User)session.getAttribute(Consts.CURRENT_USER);
        try {
            List<Order> allOrders = orderService.getAll(currentUser.getId());
            map.put("state", true);
            map.put("msg", "confirm order payment success");
            map.put("allOrders", allOrders);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", true);
            map.put("msg", e.getMessage());
        }
        return map;
    }


    @ApiOperation("cancel the order for one order router in order controller")
//    @PostMapping("/cancel")
    @PostMapping("/cancel/{id}")
    public Map<String, Object> cancel(@PathVariable(value = "id") Integer orderId, HttpSession session){
        Map<String, Object> map = new HashMap<>();
        User currentUser = (User)session.getAttribute(Consts.CURRENT_USER);
        try {
            Order cancelOrder = orderService.cancel(orderId, currentUser);
            map.put("state", true);
            map.put("msg", "confirm order payment success");
            map.put("cancelOrder", cancelOrder);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", true);
            map.put("msg", e.getMessage());
        }
        return map;
    }
}
