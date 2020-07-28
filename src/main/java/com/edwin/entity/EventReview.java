package com.edwin.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * event_review table attributes
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("event_review database table")
public class EventReview {
    private Integer id;
    private Integer sender_id;
    private Integer receiver_id;
    private String content;
    private Date updated_at;
    private Date created_at;
    private Integer event_id;
}
