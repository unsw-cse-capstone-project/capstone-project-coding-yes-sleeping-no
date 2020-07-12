package com.edwin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.w3c.dom.Text;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EventReview {
    private String id;
    private int messagable_id;
    private int to_user_id;
    private Text content;
    private Date updated_at;
    private Date created_at;
}
