//package com.edwin.controller;
//
//import com.edwin.entity.User;
//import com.edwin.service.EventReviewService;
//import com.edwin.utlis.Consts;
//import com.edwin.utlis.StringUtil;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:8080")
//@RequestMapping("/eventReview")
//@Slf4j
//public class EventReviewController {
//
//
//    @Autowired
//    private EventReviewService eventReviewService;
//
//    @ApiOperation("send event review by current user router in event review controller")
//    @ResponseBody
//    @PostMapping("/sendReview")
//    public Map<String, Object> sendReview(@RequestBody Map<String,Object> map1, HttpSession session) {
//        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
//        Map<String, Object> map = new HashMap<>();
//        eventReviewService.sendReview(map1,currentUser);
//        if (StringUtils.isEmpty(email)) {
//            map.put("state", false);
//            map.put("msg", "mail is empty");
//            return map;
//        }
//        User userEmail = userDao.findByUserEmail(email);
//        if (userEmail == null) {
//            map.put("state", false);
//            map.put("msg", "email dose not exist");
//            return map;
//        }
//
//        String verificationCode = StringUtil.giveSixRandom();
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(Consts.SENDER_EMAIL);
//        message.setTo(email);
//        message.setSubject("CYSN - password reset");
//        message.setText("Your verification code:" + verificationCode);
//        mailSender.send(message);
//        // HttpServletRequest request
//        // request.getServletContext().setAttribute("mailCode",mailCode);
//        session.setAttribute(Consts.EMAIL_VERIFICATION_CODE_NAME, verificationCode);
//        session.setAttribute(Consts.USER_ID_NAME, userEmail.getId());
//        log.info("verification code session ============:[{}]", (String) session.getAttribute(Consts.EMAIL_VERIFICATION_CODE_NAME));
//        map.put("state", true);
//        map.put("msg", "password reset verification email has been sent");
//        return map;
//    }
//
//
//}
