package com.example.duckexchange;

import java.util.Date;

public class BoughtCurrency {

    private String code;
    private String name;
    private String data;
    private float bought;
    public BoughtCurrency(String code, String name, String data, float bought)
    {this.code=code;
        this.name=name;
        this.data=data;
        this.bought=bought;
    }
}
