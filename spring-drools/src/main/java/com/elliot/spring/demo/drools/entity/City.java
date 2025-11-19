package com.elliot.spring.demo.drools.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class City {
    private String countryName;
    private List<String> cityNames = new ArrayList<>();
}
