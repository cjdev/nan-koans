package com.cj.nan.koans.java.exceptions;

import com.cj.nan.koans.impl.ExceptionThrower;
import org.junit.Assert;
import org.junit.Test;
import static com.cj.nan.koans.impl.TheBlank._____;

public class ExceptionsTest {

    @Test
    public void testException() {
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        exceptionThrower.throwException();

        //fill in the blank
        Assert.assertTrue(_____.equals(exceptionThrower.someNumber));
    }

    @Test
    public void testAnotherException() {
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        exceptionThrower.throwAnotherException();

        //fill in the blank
        Assert.assertTrue(_____.equals(exceptionThrower.someNumber));

    }
}
