package com.edwin.service;

import com.edwin.dao.UserDao;
import com.edwin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.Base64.Decoder;

/**
 * Description: user service implementation
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static String UPLOAD_PATH = "File/image/upload";

    @Autowired
    private UserDao userDao;

    /**
     * User register
     *
     * @param user
     */
    @Override
    public void register(User user) {
        User userByEmail = userDao.findByUserEmail(user.getEmail());
        if (userByEmail == null) {
            if (StringUtils.isEmpty(user.getUser_name())) {
                throw new RuntimeException("Username is empty");
            }
            if (StringUtils.isEmpty(user.getEmail())) {
                throw new RuntimeException("Email is empty");
            }
            if (StringUtils.isEmpty(user.getPassword())) {
                throw new RuntimeException("Password is empty");
            }
            if (user.getStatus() == null) {
                throw new RuntimeException("User status is empty");
            }
            Integer status = Integer.valueOf(user.getStatus());
            user.setUser_balance(new BigDecimal(Double.toString(0.00)));
            user.setStatus(status);
            user.setUpdated_at(new Date());
            user.setCreated_at(new Date());
            userDao.save(user);
        } else {
            throw new RuntimeException("user exists");
        }
    }

    /**
     * User login
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        User userByEmail = userDao.findByUserEmail(user.getEmail());
        if (!ObjectUtils.isEmpty(userByEmail)) {
            if (userByEmail.getPassword().equals(user.getPassword())) {
                return userByEmail;
            } else {
                throw new RuntimeException("Password is not correct");
            }
        } else {
            throw new RuntimeException("User does not exist");
        }
    }

    /**
     * User updates user profile
     *
     * @param user
     */
    @Override
    public void update(User user) {
        User userByUserId = userDao.findByUserId(user.getId());
        if (userByUserId == null) {
            throw new RuntimeException("User does not exist");
        } else {
            String base64Data = user.getAvatar();
            String dataPrix = "";
            String data = "";
            if (base64Data == null || "".equals(base64Data)) {
                throw new RuntimeException("image is empty");
            } else {
                String[] d = base64Data.split("base64,");
                if (d != null && d.length == 2) {
                    dataPrix = d[0];
                    data = d[1];
                } else {
                    throw new RuntimeException("image format is invalid");
                }
            }
            String suffix = "";
            if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {
                suffix = ".jpg";
            } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {
                suffix = ".ico";
            } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {
                suffix = ".gif";
            } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {
                suffix = ".png";
            } else {
                throw new RuntimeException("image extension is invalid");
            }
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String tempFileName = uuid + suffix;
            Decoder decoder = Base64.getDecoder();
            try {
                byte[] b = decoder.decode(data);
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {
                        b[i] += 256;
                    }
                }
                File pathRoot = new File(ResourceUtils.getURL("classpath:").getPath());
                System.out.println("pathRoot："+pathRoot.getAbsolutePath());
                if(!pathRoot.exists()) {
                    pathRoot = new File("");
                }
                String saveFile = pathRoot.getAbsolutePath() +"/static/";
                FileOutputStream writer = new FileOutputStream(new File(saveFile , tempFileName));
                writer.write(b);
                writer.flush();
                writer.close();
                user.setAvatar(saveFile + tempFileName);
                userDao.update(user);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * User reset password
     *
     * @param newPassword
     * @param currentUser
     */
    @Override
    public void resetPassword(String newPassword, User currentUser) {

        if (StringUtils.isEmpty(newPassword)) {
            throw new RuntimeException("new password is empty");
        }
        if (ObjectUtils.isEmpty(currentUser)) {
            throw new RuntimeException("current user is empty");
        }
        currentUser.setUpdated_at(new Date());
        currentUser.setPassword(newPassword);
        userDao.update(currentUser);
    }
}
