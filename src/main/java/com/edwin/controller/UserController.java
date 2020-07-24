package com.edwin.controller;

import com.edwin.dao.UserDao;
import com.edwin.entity.User;
import com.edwin.service.UserService;
import com.edwin.utlis.Consts;
import com.edwin.utlis.StringUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.json.JSONObject;

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
 * 0 => admin
 * 1 => customer
 * 2 => host
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
        log.info("user status ==============:[{}]", user.getStatus());
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
//    @RequestParam(required = false, value = "email") String email
    public Map<String, Object> sendEmail(@RequestBody Map<String,Object> map1, HttpSession session) {
        String email =(String) map1.get("email");
        log.info("send email:[{}]", email);
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
        message.setFrom(Consts.SENDER_EMAIL);
        message.setTo(email);
        message.setSubject("CYSN - password reset");
        message.setText("Your verification code:" + verificationCode);
        mailSender.send(message);
        // HttpServletRequest request
        // request.getServletContext().setAttribute("mailCode",mailCode);
        session.setAttribute(Consts.EMAIL_VERIFICATION_CODE_NAME, verificationCode);
        session.setAttribute(Consts.USER_ID_NAME, userEmail.getId());
        log.info("verification code session ============:[{}]", (String) session.getAttribute(Consts.EMAIL_VERIFICATION_CODE_NAME));
        map.put("state", true);
        map.put("msg", "password reset verification email has been sent");
        return map;
    }

    /**
     * Check email verification code for password reset
     */
    @ApiOperation("forget password check user's verification code router in user controller")
    @RequestMapping(value = "/checkVerificationCode", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
//    @ResponseBody
//    @RequestParam(required = false, value = "verificationCode") String verificationCode, @RequestParam(value = "password") String password
//    public Map<String, Object> checkVerificationCode(@RequestBody JSONObject jsonObject, HttpSession session) throws JSONException {
//    public Map<String, Object> checkVerificationCode(@RequestParam (required=false,value="password") String password,@RequestParam (required=false,value="verificationCode") String verificationCode, HttpSession session) throws JSONException {
    public Map<String, Object> checkVerificationCode(@RequestBody Map<String,Object> map1, HttpSession session) throws JSONException {
//        String password = params.get("password").toString();

//        String password = jsonObject.getString("password");
        String password = (String) map1.get("password");
        String verificationCode = (String) map1.get("verificationCode");

        log.info("password code 111111111========= :[{}]", password);
//        String verificationCode = jsonObject.getString("verificationCode");
//        String verificationCode = map1.get("verificationCode").toString();
        log.info("verification code 11111111111========= :[{}]", verificationCode);
        log.info("verification code session 111111111111111============:[{}]", (String) session.getAttribute(Consts.EMAIL_VERIFICATION_CODE_NAME));
        Map<String, Object> map = new HashMap<>();
        if (ObjectUtils.isEmpty(verificationCode)) {
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
        user.setUpdated_at(new Date());
        user.setPassword(password);
        userDao.update(user);
        map.put("state", true);
        map.put("msg", "password reset success");
        return map;
    }

    @ApiOperation("get current user account information router in user controller")
    @GetMapping("/getCurrent")
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

    @ApiOperation("get user account information by user id router in user controller")
    @RequestMapping("/get/{id}")
    @ResponseBody
    public Map<String, Object> getUserById(@PathVariable(value = "id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        User user = userDao.findByUserId(id);
        if (user == null) {
            map.put("state", false);
            map.put("msg", "user id dose not exist");
            return map;
        }
        map.put("state", true);
        map.put("msg", "get user information by id success");
        map.put("user", user);
        return map;
    }

    @ApiOperation("update user account information with avatar in account center router in user controller")
    @PostMapping("/updateUserInfo")
    @ResponseBody
    public Map<String, Object> updateUserInfo(HttpSession session, @RequestBody User user) {
        Map<String, Object> map = new HashMap<>();

//        log.info("image base 64 dat:[{}]", user.getAvatar());
//        log.info("user data :[{}]", user.toString());
        log.info("user data :[{}]", (User) session.getAttribute(Consts.CURRENT_USER));
//        String first_name = (String) map1.get("first_name");
        log.info("user data :[{}]", user.getFirst_name());

//        String first_name = (String) map1.get("first_name");
//        String first_name = (String) map1.get("first_name");
//        String first_name = (String) map1.get("first_name");


        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        if (currentUser == null) {
            map.put("state", false);
            map.put("msg", "user dose not login");
            return map;
        }
        user.setId(currentUser.getId());
        user.setUser_name(currentUser.getUser_name());
        user.setEmail(currentUser.getEmail());
        user.setPassword(currentUser.getPassword());
        user.setStatus(currentUser.getStatus());
        user.setUser_balance(currentUser.getUser_balance());
        user.setCreated_at(currentUser.getCreated_at());
        log.info("user data 111111111:[{}]", user);
        user.setUpdated_at(new Date());
        try {
//            String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(avatar.getOriginalFilename());
//            avatar.transferTo(new File(realPath, fileName));
//            user.setAvatar(fileName);
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
        log.info("send logout:[{}]",  session.getAttribute(Consts.CURRENT_USER));
        session.removeAttribute(Consts.CURRENT_USER);
        map.put("state", true);
        map.put("msg", "Logout success");
        log.info("send logout:[{}]",  session.getAttribute(Consts.CURRENT_USER));
        return map;
    }

    @ApiOperation("user changes original password to new password")
//    @PostMapping("/resetPassword")
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
//    @RequestParam(value = "oldPassword") String oldPassword
    public Map<String,Object> resetPassword(@RequestBody Map<String,Object> map1, HttpSession session){
//    public Map<String,Object> resetPassword(@RequestBody Map<String,Object> map1, HttpSession session){
//        String oldPassword = (String)map1.get("oldPassword");
        String newPassword = (String)map1.get("newPassword");
//        String oldPassword = user.getPassword();
//        log.info("old password:[{}]", oldPassword);
        log.info("new password:[{}]", newPassword);
        Map<String, Object> map = new HashMap<>();

        User currentUser = (User)session.getAttribute(Consts.CURRENT_USER);
//        User currentUser = userDao.findByUserId(7);
        try {
            userService.resetPassword(newPassword, currentUser);
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
