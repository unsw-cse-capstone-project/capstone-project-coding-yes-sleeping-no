package com.edwin.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("event review database table")
public class EventReview {
    private Integer id;
    private Integer messagable_id;
    private Integer to_user_id;
    private Integer event_id;
    private String rate;
    private String content;
    private Date updated_at;
    private Date created_at;
}
