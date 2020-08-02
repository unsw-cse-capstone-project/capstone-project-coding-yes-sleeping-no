package com.edwin.controller;

import com.edwin.dao.UserDao;
import com.edwin.entity.User;
import com.edwin.service.EventReviewService;
import com.edwin.utlis.Consts;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: event review controller has all routers of event review related
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/eventReview")
@Slf4j
public class EventReviewController {

    @Autowired
    private EventReviewService eventReviewService;

    @Autowired
    private UserDao userDao;

    /**
     * Customer sends event review to past event
     *
     * @param request
     * @param session
     * @return
     */
    @ApiOperation("Send event review by current user router in event review controller")
    @ResponseBody
    @PostMapping("/send")
    public Map<String, Object> sendReview(@RequestBody Map<String, Object> request, HttpSession session) {
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        Map<String, Object> response = new HashMap<>();
        try {
            eventReviewService.sendReview(request, currentUser);
            response.put("state", true);
            response.put("msg", "send event review success");
        } catch (Exception e) {
            e.printStackTrace();
            response.put("state", true);
            response.put("msg", e.getMessage());
        }
        return response;
    }

    /**
     * Host replies to customer
     *
     * @param request
     * @param session
     * @return
     */
    @ApiOperation("Host replies to customer's event review router in event review controller")
    @ResponseBody
    @PostMapping("/reply")
    public Map<String, Object> reply(@RequestBody Map<String, Object> request, HttpSession session) {
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        Map<String, Object> response = new HashMap<>();
        try {
            eventReviewService.reply(request, currentUser);
            response.put("state", true);
            response.put("msg", "reply to customer's event review success");
        } catch (Exception e) {
            e.printStackTrace();
            response.put("state", true);
            response.put("msg", e.getMessage());
        }
        return response;
    }
}
