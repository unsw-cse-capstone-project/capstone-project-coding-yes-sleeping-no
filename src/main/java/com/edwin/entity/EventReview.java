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
public class EventReview {
    private Integer id;
    private Integer messagable_id;
    private Integer to_user_id;
    private Integer event_id;
    private Double rate;
    private String content;
    private Date updated_at;
    private Date created_at;
}
