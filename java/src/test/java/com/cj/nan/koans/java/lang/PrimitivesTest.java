package com.cj.nan.koans.java.lang;

import static com.cj.nan.koans.impl.TheBlank._____;

import org.junit.Assert;
import org.junit.Test;

public class PrimitivesTest {

	@Test
	public void doubleTrouble(){
		// given
		double step = 0.1;
		int numMeters = 0;
		
		// when
		for(double i = 0; i<1; i+=step) {
			numMeters++;
		}
		
		// then
		Assert.assertTrue(_____.equals(numMeters));
	}
	
	
	@Test
	public void castIntoOblivion(){
		// given
		 double centsDouble = Double.parseDouble("0.29") * 100;
		
		// when
		 int centsInt = (int) centsDouble;
		 
		// then
		Assert.assertTrue(_____.equals(centsInt));
	}
}
