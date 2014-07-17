package com.kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MutatorTest {
    
    @Test
    public void testWhatAreMyFields() {
        PrivateValue privateValue = new PrivateValue(1);
        Mutator mutator = new Mutator();
        String[] fieldNames = mutator.fieldsOf(privateValue);

        assertEquals(3, fieldNames.length);
        assertEquals("privateInt", fieldNames[0]);
        assertEquals("somethingImportant", fieldNames[1]);
        assertEquals("lacucaracha", fieldNames[2]);
    }
    
    @Test
    public void testModification() {
        PrivateValue privateValue = new PrivateValue(1);
        Mutator mutator = new Mutator();
        mutator.modifyInt(privateValue, "privateInt", 3);

        assertEquals(3, privateValue.getPrivateInt());
    }

    @Test
    public void testAccessPrivateMethods() {
        PrivateValue privateValue = new PrivateValue(1);
        Mutator mutator = new Mutator();
        Object aSecretMessage = mutator.accessMethod(privateValue, "getTopSecretMessage");

        assertEquals("The rabbit ate all the carrots", (String)aSecretMessage);
    }
}
