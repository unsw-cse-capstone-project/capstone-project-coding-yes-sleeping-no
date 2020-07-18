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
public class Payment {
    private Integer id;
    private Integer order_id;
    private String card_number;
    private Date expiry_date;
    private Integer cvv;
    private double account_balance;
    private Date update_at;
    private Date created_at;
}
