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
    private String id;
    private String title;
    private String type;
    private Spring description;
    private String address;
    private String event_image;
    private int available_tickets;
    private int available_seats;
    private DecimalFormat ticket_price;
    private Date start_date;
    private Date end_date;
    private Date update_at;
    private Date created_at;
}
