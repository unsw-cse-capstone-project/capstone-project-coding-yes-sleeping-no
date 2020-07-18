package com.edwin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Event {
    private Integer id;
    private String title;
    private String type;
    private String description;
    private Double rate;
    private String cover_image;
    private String address;
    private Integer available_tickets;
    private double ticket_price;
    private Date start_date;
    private Date end_date;
    private Date start_time;
    private Date update_at;
    private Date created_at;
    private Integer user_id;
}
