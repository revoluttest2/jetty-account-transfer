package com.revolut.servlet.transfer;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferJson {

    private Long accountIdFrom;
    private Long accountIdTo;
    private BigDecimal amount;
}
