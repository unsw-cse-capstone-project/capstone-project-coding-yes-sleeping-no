package com.edwin.controller;

import com.edwin.dao.UserDao;
import com.edwin.entity.User;
import com.edwin.service.UserService;
import com.edwin.utlis.Consts;
import com.edwin.utlis.StringUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * user status:
 * 0 => customer
 * 1 => host
 * 2 => back end staff
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Value("${photo.dir}")
    private String realPath;

    @Value(value = "${image.path:./img}")
    private String imagePath;

    @Value(value = "${image.host:http://localhost}")
    private String imageHost;

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    //TBD
    @Resource
    private JavaMailSender mailSender;

    @RequestMapping(value = {"/","/index.html", "/index",})
    public String index(){
        return "index";
    }

    @ApiOperation("login router in user controller")
    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody User user, HttpSession session) {
        log.info("login user information: [{}]", user.toString());
        Map<String, Object> map = new HashMap<>();
        try {
            User userByName = userService.login(user);
            session.setAttribute(Consts.CURRENT_USER, userByName);
            // TBD: test
            log.info("current user:[{}]", session.getAttribute(Consts.CURRENT_USER).toString());
            map.put("state", true);
            map.put("msg", "login success");
            map.put("user", userByName);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @ApiOperation("register router in user controller")
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user, HttpServletRequest request, HttpSession session) {
        log.info("register user information:[{}]", user.toString());
        Map<String, Object> map = new HashMap<>();
        try {
            userService.register(user);
            map.put("state", true);
            map.put("msg", "registration success");
            User userByEmail = userDao.findByUserEmail(user.getEmail());
            map.put("user", userByEmail);
            session.setAttribute(Consts.CURRENT_USER, userByEmail);
            log.info("current user:[{}]", session.getAttribute(Consts.CURRENT_USER).toString());
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Send password reset email with verification code
     */
    @ApiOperation("forget password send email with verification code to user router in user controller")
    @ResponseBody
    @PostMapping("/sendEmail")
    public Map<String, Object> sendEmail(String email, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(email)) {
            map.put("state", false);
            map.put("msg", "mail is empty");
            return map;
        }
        User userEmail = userDao.findByUserEmail(email);
        if (userEmail == null) {
            map.put("state", false);
            map.put("msg", "email dose not exist");
            return map;
        }

        String verificationCode = StringUtil.giveSixRandom();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("719204145@qq.com");
        message.setTo(email);
        message.setSubject("CYSN - password reset");
        message.setText("Your verification code:" + verificationCode);
        mailSender.send(message);
        // HttpServletRequest request
        // request.getServletContext().setAttribute("mailCode",mailCode);
        session.setAttribute(Consts.EMAIL_VERIFICATION_CODE_NAME, verificationCode);
        session.setAttribute(Consts.USER_ID_NAME, userEmail.getId());
        map.put("state", true);
        map.put("msg", "password reset verification email has been sent");
        return map;
    }

    /**
     * Check email verification code for password reset
     */
    @ApiOperation("forget password check user's verification code router in user controller")
    @PostMapping("/checkVerificationCode")
    public Map<String, Object> checkVerificationCode(String verificationCode, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(verificationCode)) {
            map.put("state", false);
            map.put("msg", "verification code can not be empty");
        }
        if (StringUtils.isEmpty(password)) {
            map.put("state", false);
            map.put("msg", "password can not be empty");
        }
        String mailCode = (String) session.getAttribute(Consts.EMAIL_VERIFICATION_CODE_NAME);
        Integer userId = (Integer) session.getAttribute(Consts.USER_ID_NAME);
        if (!verificationCode.equals(mailCode)) {
            map.put("state", false);
            map.put("msg", "verification code is not correct");
            return map;
        }

        // if correct: reset user password
        User user = userDao.findByUserId(userId);
        user.setId(user.getId());
        user.setUpdated_at(new Date());
        user.setPassword(password);
        userDao.update(user);
        map.put("state", true);
        map.put("msg", "password reset success");
        return map;
    }

    @ApiOperation("get user account information router in user controller")
    @GetMapping("/getUserInfo")
    @ResponseBody
    public Map<String, Object> getUserInfo(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) session.getAttribute(Consts.CURRENT_USER);
        if (user == null) {
            map.put("state", false);
            map.put("msg", "user dose not login, cannot get user information");
            return map;
        }
        map.put("state", true);
        map.put("msg", "get user information success");
        map.put("user", user);
        return map;
    }

    @ApiOperation("update user account information with avatar in account center router in user controller")
    @PostMapping("/updateUserInfo")
    @ResponseBody
    public Map<String, Object> updateUserInfo(HttpSession session, User user, MultipartFile avatar) {
        Map<String, Object> map = new HashMap<>();
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        if (currentUser == null) {
            map.put("state", false);
            map.put("msg", "user dose not login");
            return map;
        }
        user.setId(currentUser.getId());
        user.setUpdated_at(new Date());
        try {
            String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(avatar.getOriginalFilename());
            avatar.transferTo(new File(realPath, fileName));
            user.setAvatar(fileName);
            userService.update(user);
            session.setAttribute(Consts.CURRENT_USER, user);
            map.put("state", true);
            map.put("user", user);
            map.put("msg", "Update user information success");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "Fail to update user");
            return map;
        }
    }
    @ApiOperation("logout router in user controller")
    @GetMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout(HttpSession session){
        Map<String, Object> map = new HashMap<>();
        session.removeAttribute(Consts.CURRENT_USER);
        map.put("state", true);
        map.put("msg", "Logout success");
        return map;
    }

    @ApiOperation("user changes original password to new password")
    @PostMapping("/resetPassword")
    public Map<String,Object> resetPassword(String oldPassword, String newPassword, HttpSession session){
        Map<String, Object> map = new HashMap<>();
        User currentUser = (User)session.getAttribute(Consts.CURRENT_USER);
        try {
            userService.resetPassword(oldPassword, newPassword, currentUser);
            map.put("state", true);
            map.put("msg", "Password reset");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "Fail to change password");
        }
        return map;
    }
}
