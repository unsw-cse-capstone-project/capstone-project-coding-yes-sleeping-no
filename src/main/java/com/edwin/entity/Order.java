package com.edwin.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("order database table")
public class Order {
    private Integer id;
    private Integer order_number;
    private Integer user_id;
    private Integer event_id;
    private Integer ticket_amount;
    private BigDecimal total_price;
    private Integer status;
    private Date created_at;
    private Date updated_at;
}
