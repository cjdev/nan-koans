package com.cj.nan.koans.java.io.impl;

import java.io.IOException;
import java.io.OutputStream;

public class BufferingOutputStreamWrapper extends OutputStream {

	private final int bufferSize;
	private int[] bufferOfInts;
	private int numTimesWriteWasCalled = 0;
	private final OutputStream wrapped;
	private boolean isClosed = false;
	
	public BufferingOutputStreamWrapper(int bufferSize, OutputStream wrapped) {
		super();
		this.bufferSize = bufferSize;
		bufferOfInts = new int[bufferSize];
		this.wrapped = wrapped;
	}

	public void write(int someByte) throws IOException {
			numTimesWriteWasCalled++;
			bufferOfInts[numTimesWriteWasCalled-1] = someByte;
			
			if(numTimesWriteWasCalled >= bufferSize) {
				flush();
			}
	}

	public void flush() throws IOException {
		for(int x=0;x<numTimesWriteWasCalled;x++){
			wrapped.write(bufferOfInts[x]);
		}
		numTimesWriteWasCalled = 0;
		wrapped.flush();
	}

	public void close() throws IOException {
		flush();
		wrapped.close();
		isClosed = true;
	}
	
	public boolean isClosed() {
		return isClosed;
	}
}
