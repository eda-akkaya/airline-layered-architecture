package com.example.airline.domain.valueObjects;

public class Airport {
    //Airport:
    //No identity
    //No standalone lifecycle
    //Meaningless without a "flight"
    //Conveys meaning, not behavior

    private String code;
    private String city;

    protected Airport(){}

    public Airport(String code, String city) {
        this.code = code;
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

}
