package com.kata;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Mutator {

    public void modifyInt(Object object, String variable, int toValue){ }

    public Object accessMethod(Object privateValue, String getTopSecretMessageMethodName) {
        return null;
    }

    public String[] fieldsOf(Object privateValue) {
        return null;
    }
}
