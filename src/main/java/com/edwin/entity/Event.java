package com.edwin.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * event table attributes
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("event database table")
public class Event {
    private Integer id;
    private String title;
    private String type;
    private String description;
    private String address;
    private BigDecimal ticket_price;
    private Integer available_tickets;
    private Integer status;
    private String cover_image;
    private Date start_date;
    private Date start_time;
    private Date end_time;
    private Date updated_at;
    private Date created_at;
    private Integer user_id;
}
