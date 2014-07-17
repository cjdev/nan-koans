package com.cj.nan.koans.java.math;

import static com.cj.nan.koans.impl.TheBlank._____;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class NumbersTest {
	
	
	@Test
	public void equallyConfusing(){
		// given
		BigDecimal a = new BigDecimal("1.0");
		BigDecimal b = new BigDecimal("1.00");
		
		// when
		Boolean theyAreEqual = a.equals(b);
		
		// then
		Assert.assertTrue(_____.equals(theyAreEqual));
	}
	
	@Test
	public void whoPeedInThePool(){
		// given
		final int ONE_MILLIION = 10000000;
		
		//when
		boolean theyAreEqual = Integer.valueOf(ONE_MILLIION) == Integer.valueOf(ONE_MILLIION);
		
		// then
		Assert.assertTrue(_____.equals(theyAreEqual));
	}
	
}
