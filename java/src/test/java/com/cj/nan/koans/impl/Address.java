package com.cj.nan.koans.impl;

import java.io.Serializable;

public class Address implements Serializable {
    public String address;
    public String city;
    public String state;
    public int zip;

    public Address(String s, String city, String state, int zip) {
        this.address = s;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}
