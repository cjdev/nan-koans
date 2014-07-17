package com.kata;

public final class PrivateValue {
    private int privateInt = 3;
    private String somethingImportant;
    private Boolean lacucaracha;

    public PrivateValue(int val) {
        privateInt = val;
    }

    public int getPrivateInt() {
        return privateInt;
    }
    
    private String getTopSecretMessage() {
        return "The rabbit ate all the carrots";
    }
}
