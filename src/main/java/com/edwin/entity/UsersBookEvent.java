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
public class UsersBookEvent {
    private String id;
    private int user_id;
    private int event_id;
    private int ticket_amount;
    private Date update_at;
    private Date created_at;
}

