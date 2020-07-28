package com.edwin.controller;

import com.edwin.entity.User;
import com.edwin.service.EventReviewService;
import com.edwin.utlis.Consts;
import com.edwin.utlis.StringUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.util.StringUtils;
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

    @ApiOperation("Send event review by current user router in event review controller")
    @ResponseBody
    @PostMapping("/send")
    public Map<String, Object> sendReview(@RequestBody Map<String, Object> request, HttpSession session) {
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        Map<String, Object> response = new HashMap<>();
        eventReviewService.sendReview(request, currentUser);
        response.put("state", true);
        response.put("msg", "send event review success");
        return response;
    }
}
