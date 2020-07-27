package com.edwin.controller;

import com.edwin.entity.Order;
import com.edwin.entity.User;
import com.edwin.service.OrderService;
import com.edwin.utlis.Consts;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/order")
@Slf4j

/**
 * order status: 0 not pay
 * order status: 1 have paid
 * order status: 2 refund
 */
public class OderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("create one order router in order controller")
    @PostMapping("/create")
    public Map<String, Object> create(@RequestBody Map<String,Object> map1, HttpSession session){
        Map<String, Object> map = new HashMap<>();
        User currentUser = (User)session.getAttribute(Consts.CURRENT_USER);
        try {
            log.info("map11111 status ==============:[{}]", map1.toString());
            Order currentOrder = orderService.create(map1, currentUser);
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

//    @ApiOperation("confirm the payment for one order router in order controller")
//    @GetMapping("/confirm/{id}")
//    public Map<String, Object> update(@PathVariable(value = "id") Integer orderId, HttpSession session) {
//        Map<String, Object> map = new HashMap<>();
//        User currentUser = (User)session.getAttribute(Consts.CURRENT_USER);
//        try {
//            Order updateOrder = orderService.update(orderId, currentUser);
//            session.setAttribute(Consts.CURRENT_ORDER, updateOrder);
//            map.put("state", true);
//            map.put("msg", "confirm order payment success");
//            map.put("order", updateOrder);
//        } catch (Exception e) {
//            e.printStackTrace();
//            map.put("state", true);
//            map.put("msg", e.getMessage());
//        }
//        return map;
//    }

    @ApiOperation("get all orders for one user router in order controller")
    @GetMapping("/get/customer")
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
    @GetMapping("/cancel/{id}")
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

    @ApiOperation("find all customer orders through event created by host")
    @GetMapping("/get/host")
    @ResponseBody
    public Map<String,Object> getHost(HttpSession session){
        Map<String, Object> map = new HashMap<>();
        User currentUser = (User)session.getAttribute(Consts.CURRENT_USER);
        try {
            List<Order> ordersByHost = orderService.findHost(currentUser);
            if (ordersByHost.size() == 0){
                map.put("state", false);
                map.put("msg", "no order found by host");
            }else {
                map.put("state", true);
                map.put("msg", "get order details by host");
                map.put("orders", ordersByHost);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", true);
            map.put("msg", e.getMessage());
        }
        return map;
    }
}
