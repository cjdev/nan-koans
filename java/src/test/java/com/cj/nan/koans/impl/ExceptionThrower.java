package com.cj.nan.koans.impl;

public class ExceptionThrower {
    public int someNumber;
    
    public void throwException() {
        try {
            someNumber = 12;
            throw new Exception();
            
        } catch(Exception e) {
            someNumber = 25;
            
        } finally {
            someNumber = 34;
        }
    }

    public void throwAnotherException() {
            try {
                someNumber = 12;
                return;
            } catch(Exception e) {
                someNumber = 25;
            }finally {
                someNumber = 34;
            }
        }
}
