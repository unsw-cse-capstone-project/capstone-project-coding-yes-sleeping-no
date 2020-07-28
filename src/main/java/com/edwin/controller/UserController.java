package com.edwin.controller;

import com.edwin.dao.UserDao;
import com.edwin.entity.User;
import com.edwin.service.UserService;
import com.edwin.utlis.Consts;
import com.edwin.utlis.StringUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * user status:
 * 0 => admin
 * 1 => customer
 * 2 => host
 * Description: user controller has all routers of user related
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Value("${photo.dir}")
    private String realPath;

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Resource
    private JavaMailSender mailSender;

    /**
     * Index page router
     *
     * @return
     */
    @RequestMapping(value = {"/", "/index.html", "/index",})
    public String index() {
        return "index";
    }

    /**
     * User login router
     *
     * @param user
     * @param session
     * @return
     */
    @ApiOperation("User login router in user controller")
    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody User user, HttpSession session) {
        log.info("login user information: [{}]", user.toString());
        Map<String, Object> response = new HashMap<>();
        try {
            User userByName = userService.login(user);
            session.setAttribute(Consts.CURRENT_USER, userByName);
            log.info("current user:[{}]", session.getAttribute(Consts.CURRENT_USER).toString());
            response.put("state", true);
            response.put("msg", "login success");
            response.put("user", userByName);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("state", false);
            response.put("msg", e.getMessage());
        }
        return response;
    }

    /**
     * User register router
     *
     * @param user
     * @param request
     * @param session
     * @return
     */
    @ApiOperation("User register router in user controller")
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user, HttpServletRequest request, HttpSession session) {
        log.info("register user information:[{}]", user.toString());
        log.info("user status ==============:[{}]", user.getStatus());
        Map<String, Object> response = new HashMap<>();
        try {
            userService.register(user);
            response.put("state", true);
            response.put("msg", "registration success");
            User userByEmail = userDao.findByUserEmail(user.getEmail());
            response.put("user", userByEmail);
            session.setAttribute(Consts.CURRENT_USER, userByEmail);
            log.info("current user:[{}]", session.getAttribute(Consts.CURRENT_USER).toString());
        } catch (Exception e) {
            response.put("state", false);
            response.put("msg", e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * User forget password and 6-digit send verification code to user's email
     *
     * @param request
     * @param session
     * @return
     */
    @ApiOperation("User forget password and back-end send email with 6-digit verification code to user's email router in user controller")
    @ResponseBody
    @PostMapping("/sendEmail")
    public Map<String, Object> sendEmail(@RequestBody Map<String, Object> request, HttpSession session) {
        String email = (String) request.get("email");
        log.info("send email:[{}]", email);
        Map<String, Object> response = new HashMap<>();
        if (StringUtils.isEmpty(email)) {
            response.put("state", false);
            response.put("msg", "mail is empty");
            return response;
        }
        User userEmail = userDao.findByUserEmail(email);
        if (userEmail == null) {
            response.put("state", false);
            response.put("msg", "email dose not exist");
            return response;
        }
        String verificationCode = StringUtil.giveSixRandom();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(Consts.SENDER_EMAIL);
        message.setTo(email);
        message.setSubject("CYSN - password reset");
        message.setText("Your verification code:" + verificationCode);
        mailSender.send(message);
        session.setAttribute(Consts.EMAIL_VERIFICATION_CODE_NAME, verificationCode);
        session.setAttribute(Consts.USER_ID_NAME, userEmail.getId());
        log.info("verification code session ============:[{}]", (String) session.getAttribute(Consts.EMAIL_VERIFICATION_CODE_NAME));
        response.put("state", true);
        response.put("msg", "password reset verification email has been sent");
        return response;
    }

    /**
     * Back end checks user's 6-digit verification code for password reset (forget password)
     *
     * @param request
     * @param session
     * @return
     * @throws JSONException
     */
    @ApiOperation("Forget password check user's verification code router in user controller")
    @RequestMapping(value = "/checkVerificationCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> checkVerificationCode(@RequestBody Map<String, Object> request, HttpSession session) throws JSONException {
        String password = (String) request.get("password");
        String verificationCode = (String) request.get("verificationCode");
        log.info("password code 111111111========= :[{}]", password);
        log.info("verification code 11111111111========= :[{}]", verificationCode);
        log.info("verification code session 111111111111111============:[{}]", (String) session.getAttribute(Consts.EMAIL_VERIFICATION_CODE_NAME));
        Map<String, Object> response = new HashMap<>();
        if (ObjectUtils.isEmpty(verificationCode)) {
            response.put("state", false);
            response.put("msg", "verification code can not be empty");
        }
        if (StringUtils.isEmpty(password)) {
            response.put("state", false);
            response.put("msg", "password can not be empty");
        }
        String mailCode = (String) session.getAttribute(Consts.EMAIL_VERIFICATION_CODE_NAME);
        Integer userId = (Integer) session.getAttribute(Consts.USER_ID_NAME);
        if (!verificationCode.equals(mailCode)) {
            response.put("state", false);
            response.put("msg", "verification code is not correct");
            return response;
        }
        /**
         * If correct: reset user password
         */
        User user = userDao.findByUserId(userId);
        user.setUpdated_at(new Date());
        user.setPassword(password);
        userDao.update(user);
        response.put("state", true);
        response.put("msg", "password reset success");
        return response;
    }

    /**
     * Get current user account information from session router in user controller
     *
     * @param session
     * @return
     */
    @ApiOperation("Get current user account information router in user controller")
    @GetMapping("/getCurrent")
    @ResponseBody
    public Map<String, Object> getUserInfo(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        User user = (User) session.getAttribute(Consts.CURRENT_USER);
        if (user == null) {
            response.put("state", false);
            response.put("msg", "user dose not login, cannot get user information");
            return response;
        }
        response.put("state", true);
        response.put("msg", "get user information success");
        response.put("user", user);
        return response;
    }

    /**
     * Get user account information by user id router in user controller
     *
     * @param id
     * @return
     */
    @ApiOperation("Get user account information by user id router in user controller")
    @RequestMapping("/get/{id}")
    @ResponseBody
    public Map<String, Object> getUserById(@PathVariable(value = "id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        User user = userDao.findByUserId(id);
        if (user == null) {
            response.put("state", false);
            response.put("msg", "user id dose not exist");
            return response;
        }
        response.put("state", true);
        response.put("msg", "get user information by id success");
        response.put("user", user);
        return response;
    }

    /**
     * Update user account information with avatar (user profile) in account center router in user controller
     *
     * @param session
     * @param user
     * @return
     */
    @ApiOperation("Update user account information with avatar in account center router in user controller")
    @PostMapping("/updateUserInfo")
    @ResponseBody
    public Map<String, Object> updateUserInfo(HttpSession session, @RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        log.info("user data :[{}]", (User) session.getAttribute(Consts.CURRENT_USER));
        log.info("user data aaaaaaaaaaaaaa:[{}]", user.toString());
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        if (currentUser == null) {
            response.put("state", false);
            response.put("msg", "user dose not login");
            return response;
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
            userService.update(user);
            session.setAttribute(Consts.CURRENT_USER, user);
            response.put("state", true);
            response.put("user", user);
            response.put("msg", "update user information success");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("state", false);
            response.put("msg", "fail to update user");
            return response;
        }
    }

    /**
     * User logout
     *
     * @param session
     * @return
     */
    @ApiOperation("Logout router in user controller")
    @GetMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        log.info("send logout:[{}]", session.getAttribute(Consts.CURRENT_USER));
        session.removeAttribute(Consts.CURRENT_USER);
        response.put("state", true);
        response.put("msg", "logout success");
        log.info("send logout:[{}]", session.getAttribute(Consts.CURRENT_USER));
        return response;
    }

    /**
     * User changes old password to new password
     *
     * @param request
     * @param session
     * @return
     */
    @ApiOperation("User changes old password to new password router in user controller")
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> resetPassword(@RequestBody Map<String, Object> request, HttpSession session) {
        String newPassword = (String) request.get("newPassword");
        log.info("new password:[{}]", newPassword);
        Map<String, Object> response = new HashMap<>();
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        try {
            userService.resetPassword(newPassword, currentUser);
            response.put("state", true);
            response.put("msg", "password reset");
        } catch (Exception e) {
            e.printStackTrace();
            response.put("state", false);
            response.put("msg", "fail to change password");
        }
        return response;
    }
}
