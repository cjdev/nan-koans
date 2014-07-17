package com.cj.nan.koans.impl;

import java.util.Arrays;

public class TheBlank {
	private TheBlank() {
		
	}
	public static final TheBlank _____ = new TheBlank(){
		@Override
		public String toString() {
			return "FILL_IN_THE_BLANK";
		}
		
		
	};
	

	public boolean equals(Object obj) {
		Error t = new Error("You need to fill in the blank.");
		t.fillInStackTrace();
		StackTraceElement[] trace = t.getStackTrace();
		t.setStackTrace(Arrays.copyOfRange(trace, 1, trace.length-1));
		throw t;
	}
	
	public boolean equals(Boolean obj) {
		Error t = new Error("You need to fill in the blank with either Boolean.FALSE or BOOLEAN.TRUE");
		t.fillInStackTrace();
		StackTraceElement[] trace = t.getStackTrace();
		t.setStackTrace(Arrays.copyOfRange(trace, 1, trace.length-1));
		throw t;
	}
	public boolean equals(Integer obj) {
		Error t = new Error("You need to fill in the blank with an instance of java.lang.Integer");
		t.fillInStackTrace();
		StackTraceElement[] trace = t.getStackTrace();
		t.setStackTrace(Arrays.copyOfRange(trace, 1, trace.length-1));
		throw t;
	}
}
