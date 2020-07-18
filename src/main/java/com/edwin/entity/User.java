package com.edwin.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("user database table")
public class User {
    private Integer id;
    private String user_name;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private String address_1;
    private String address_2;
    private String city;
    private String state;
    private String postcode;
    private String password;
    private BigDecimal user_balance;
    private Integer status;
    private String avatar;
    private Date updated_at;
    private Date created_at;
}

