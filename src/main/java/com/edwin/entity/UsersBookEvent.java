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
    private Integer id;
    private Integer user_id;
    private Integer event_id;
    private Integer ticket_amount;
    private Date update_at;
    private Date created_at;
}

