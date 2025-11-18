package com.elliot.spring.demo.drools.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDiscount {
    private Double originalPrice;
    private Double discountPrice;
}
