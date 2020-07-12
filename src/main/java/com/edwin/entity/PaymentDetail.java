package com.edwin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.text.DecimalFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PaymentDetail {
    private String id;
    private String category;
    private String card_number;
    private Date expiry_date;
    private int cvv;
    private DecimalFormat account_balance;
    private Date update_at;
    private Date created_at;
}
