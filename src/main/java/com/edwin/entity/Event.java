package com.edwin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.w3c.dom.Text;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Event {
    private Integer id;
    private String title;
    private String type;
    private Integer rate;
    private Spring description;
    private String address;
    private String event_image;
    private Integer available_tickets;
    private Integer available_seats;
    private double ticket_price;
    private Date start_date;
    private Date end_date;
    private Date start_time;
    private Date update_at;
    private Date created_at;
}
