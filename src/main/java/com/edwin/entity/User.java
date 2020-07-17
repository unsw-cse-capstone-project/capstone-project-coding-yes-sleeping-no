package com.edwin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    private Integer id;
    private String user_name;
    private String last_name;
    private String first_name;
    private String street;
    private String region;
    private String postcode;
    private String state;
    private String email;
    private String password;
    private double user_balance;
    private Integer status;
    private Date updated_at;
    private Date created_at;
}

