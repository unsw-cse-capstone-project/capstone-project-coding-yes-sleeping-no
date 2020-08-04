package com.edwin.controller;

import com.edwin.entity.Order;
import com.edwin.entity.User;
import com.edwin.service.OrderService;
import com.edwin.utlis.Consts;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Order status
 * 0 => unpaid
 * 1 => paid
 * 2 => refund
 * Description: order controller has all routers of order related
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
@Slf4j
public class OderController {

    @Autowired
    private OrderService orderService;

    /**
     * Create one order by customer (book tickets)
     *
     * @param request
     * @param session
     * @return
     */
    @ApiOperation("Create one order router in order controller")
    @PostMapping("/create")
    public Map<String, Object> create(@RequestBody Map<String, Object> request, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        try {
            log.info("map11111 status ==============:[{}]", request.toString());
            Order currentOrder = orderService.create(request, currentUser);
            session.setAttribute(Consts.CURRENT_ORDER, currentOrder);
            response.put("state", true);
            response.put("msg", "create order success");
            response.put("order", currentOrder);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("state", false);
            response.put("msg", e.getMessage());
            return response;
        }
    }

    /**
     * Customer confirms to pay order
     *
     * @param orderId
     * @param session
     * @return
     */
    @ApiOperation("Confirm the payment for one order router in order controller")
    @GetMapping("/confirm/{id}")
    public Map<String, Object> update(@PathVariable(value = "id") Integer orderId, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        try {
            Order updateOrder = orderService.update(orderId, currentUser);
            session.setAttribute(Consts.CURRENT_ORDER, updateOrder);
            response.put("state", true);
            response.put("msg", "confirm order payment success");
            response.put("order", updateOrder);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("state", false);
            response.put("msg", e.getMessage());
        }
        return response;
    }

    /**
     * Get all orders of one customer
     *
     * @param session
     * @return
     */
    @ApiOperation("Get all orders for customer router in order controller")
    @GetMapping("/get/customer")
    public Map<String, Object> get(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        try {
            List<Order> allOrders = orderService.getAll(currentUser.getId());
            response.put("state", true);
            response.put("msg", "confirm order payment success");
            response.put("allOrders", allOrders);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("state", false);
            response.put("msg", e.getMessage());
        }
        return response;
    }

    /**
     * Customer cancels one order
     *
     * @param orderId
     * @param session
     * @return
     */
    @ApiOperation("Cancel the order by customer router in order controller")
    @GetMapping("/cancel/{id}")
    public Map<String, Object> cancel(@PathVariable(value = "id") Integer orderId, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        try {
            log.info("aaaa: [{}]", orderId);
            Order cancelOrder = orderService.cancel(orderId, currentUser);
            response.put("state", true);
            response.put("msg", "cancel order success");
            response.put("cancelOrder", cancelOrder);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("state", false);
            response.put("msg", e.getMessage());
        }
        return response;
    }

    /**
     * Host gets all orders booked by customers for events
     *
     * @param session
     * @return
     */
    @ApiOperation("Find all customer orders router in order controller")
    @GetMapping("/get/host")
    @ResponseBody
    public Map<String, Object> getHost(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        try {
            List<Order> ordersByHost = orderService.findHost(currentUser);
            if (ordersByHost.size() == 0) {
                response.put("state", false);
                response.put("msg", "no order found by host");
            } else {
                response.put("state", true);
                response.put("msg", "get order details by host");
                response.put("orders", ordersByHost);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("state", false);
            response.put("msg", e.getMessage());
        }
        return response;
    }
}
