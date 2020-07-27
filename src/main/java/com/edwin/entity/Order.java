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
@ApiModel("order database table")
public class Order {
    private Integer id;
    private Integer order_number;
    private Integer ticket_amount;
    private BigDecimal total_price;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String address_1;
    private String address_2;
    private String city;
    private String state;
    private String postcode;
    private String donate;
    private Integer status;
    private Date updated_at;
    private Date created_at;
    private Integer user_id;
    private Integer event_id;
}
