package com.edwin.controller;

import com.edwin.dao.UserDao;
import com.edwin.entity.User;
import com.edwin.service.UserService;
import com.edwin.utlis.Consts;
import com.edwin.utlis.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    //TBD
    @Resource
    private JavaMailSender mailSender;


    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        log.info("login user information: [{}]", user.toString());
        Map<String, Object> map = new HashMap<>();
        try {
            User userByName = userService.login(user);
            map.put("state", true);
            map.put("msg", "Hint : login success");
            map.put("user", userByName);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user, HttpServletRequest request) {
        log.info("register user information:[{}]", user.toString());
        Map<String, Object> map = new HashMap<>();
        try {
            userService.register(user);
            map.put("state", true);
            map.put("msg", "Hint : registration success");

        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", "Hint" + e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Send password reset email with verification code
     */
    @ResponseBody
    @PostMapping("/sendEmail")
    public Map<String, Object> sendEmail(String email, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(email)) {
            map.put("state", false);
            map.put("msg", "Mail is empty !");
            return map;
        }
        User userEmail = userDao.findByUserEmail(email);
        if (userEmail == null) {
            map.put("state", false);
            map.put("msg", "Email dose not exist");
            return map;
        }

        String verificationCode = StringUtil.giveSixRandom();
        System.out.println(verificationCode);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("719204145@qq.com");
        message.setTo(email);
        message.setSubject("Event management - password reset");
        message.setText("Your verification code:" + verificationCode);
        mailSender.send(message);
//        mail verification code saves in session
//        HttpServletRequest request
//        request.getServletContext().setAttribute("mailCode",mailCode);
        session.setAttribute(Consts.EMAIL_VERIFICATION_CODE_NAME, verificationCode);
        session.setAttribute(Consts.USER_ID_NAME, userEmail.getId());
        map.put("state", true);
        map.put("msg", "Password reset verification email has been sent");
        return map;
    }

    /**
     * Check email verification code for password reset
     */
    @PostMapping("/checkVerificationCode")
    public Map<String, Object> checkVerificationCode(String verificationCode, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(verificationCode)) {
            map.put("state", false);
            map.put("msg", "Verification code can not be empty !");
        }
        String mailCode = (String) session.getAttribute(Consts.EMAIL_VERIFICATION_CODE_NAME);
        // TBD maybe Integer type
        String userId = (String) session.getAttribute(Consts.USER_ID_NAME);
        if (!verificationCode.equals(mailCode)) {
            map.put("state", false);
            map.put("msg", "Verification code is not correct !");
            return map;
        }
        // if correct: reset user password
        User user = userDao.findByUserId(userId);
        user.setPassword(Consts.USER_DEFAULT_PASSWORD);
        userDao.save(user);
        map.put("state", true);
        map.put("msg", "Password reset success !");
        return map;
    }
}
