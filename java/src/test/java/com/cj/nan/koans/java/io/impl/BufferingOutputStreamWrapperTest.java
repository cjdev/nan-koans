package com.cj.nan.koans.java.io.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

public class BufferingOutputStreamWrapperTest {
	
	interface OutputStreamWriteMethod {
		void invoke(int byteAsInt, OutputStream sink) throws IOException;
	}
	
	class WriteOneByteAsInt implements OutputStreamWriteMethod {
		@Override
		public void invoke(int byteAsInt, OutputStream sink) throws IOException {
			sink.write(byteAsInt);
		}
	}
	
	class WriteAllBytesInByteArray implements OutputStreamWriteMethod {
		@Override
		public void invoke(int byteAsInt, OutputStream sink) throws IOException {
			byte[] bytes = new byte[]{(byte)byteAsInt};
			sink.write(bytes);
		}
		
	}
	
	class WriteAsPartOfByteArrayStartingAtOffset implements OutputStreamWriteMethod {
		
		final int fromOffset;
		
		public WriteAsPartOfByteArrayStartingAtOffset(int fromOffset) {
			super();
			this.fromOffset = fromOffset;
		}

		@Override
		public void invoke(int byteAsInt, OutputStream sink) throws IOException {
			byte[] bytes = new byte[fromOffset+1];
			bytes[fromOffset] = (byte) byteAsInt;
			sink.write(bytes, fromOffset, 1);
		}
		
	}
	
	OutputStreamWriteMethod[] methods = new OutputStreamWriteMethod[]{
											new WriteOneByteAsInt(), 
											new WriteAllBytesInByteArray(), 
											new WriteAsPartOfByteArrayStartingAtOffset(0), 
											new WriteAsPartOfByteArrayStartingAtOffset(6)};
	
	
	@Test
	public void doesntFlushWhenTheBufferIsNotFull() throws IOException {
		for(OutputStreamWriteMethod outputStreamWriteMethod: methods){
			for(int byteValue : new int[]{0, 127, 255}){
				// given
				ByteArrayOutputStream finalDestination = new ByteArrayOutputStream();
				BufferingOutputStreamWrapper out = new BufferingOutputStreamWrapper(2, finalDestination);
				
				// when
				outputStreamWriteMethod.invoke(byteValue, out);
				
				// then
				Assert.assertEquals("Should not have flushed " + byteValue + " yet.", 0, finalDestination.toByteArray().length);
			}
		}
	}
	

	@Test
	public void flushesWhenTheBufferIsFull() throws IOException {
		for(OutputStreamWriteMethod outputStreamWriteMethod: methods){
			for(int byteValue : new int[]{0, 1, 127, 255}){
				// given
				ByteArrayOutputStream finalDestination = new ByteArrayOutputStream();
				BufferingOutputStreamWrapper out = new BufferingOutputStreamWrapper(1, finalDestination);
				
				// when
				outputStreamWriteMethod.invoke(byteValue, out);
				
				// then
				byte[] written = finalDestination.toByteArray();
				Assert.assertEquals("Should have flushed " + byteValue + ".", 1, written.length);
				Assert.assertEquals((byte)byteValue, written[0]);
			}
		}
	}
	

	@Test
	public void flushesOnlyTheBytesInTheBuffer() throws IOException {
		for(OutputStreamWriteMethod outputStreamWriteMethod: methods){
			// given
			ByteArrayOutputStream finalDestination = new ByteArrayOutputStream();
			BufferingOutputStreamWrapper out = new BufferingOutputStreamWrapper(2, finalDestination);
			out.write(1);
			out.write(1);
			
			// when
			outputStreamWriteMethod.invoke(1, out);
			
			// then
			Assert.assertEquals("Should have flushed 2 bytes through.", 2, finalDestination.toByteArray().length);
		}
	}
}
