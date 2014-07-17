package com.cj.nan.koans.java.io.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class Utils {

	public static class ByteArrayOutputStreamThatExposesClosedStatus extends ByteArrayOutputStream {
		public boolean wasClosed = false;
	
		@Override
		public void close() throws IOException {
			super.close();
			wasClosed = true;
		}
	}

	public static class ByteArrayInputStreamThatExposesClosedStatus extends ByteArrayInputStream {
		public boolean wasClosed = false;
	
		public ByteArrayInputStreamThatExposesClosedStatus(byte[] buf) {
			super(buf);
		}
	
		@Override
		public void close() throws IOException {
			super.close();
			wasClosed = true;
		}
	}

	public static byte[] randomBytesWithEveryPossibleByteValueRepresentedAtLeastOnce(){
		Random r = new Random();
		byte[] data = new byte[1024*1024*10];
		r.nextBytes(data);
	
		byte next = Byte.MIN_VALUE;
		int numByteValuesPossible = (-Byte.MIN_VALUE) + Byte.MAX_VALUE;
		for(int x=0;x<numByteValuesPossible;x++){
			data[x] = next;
			next ++;
		}
	
		return data;
	}

	public static String createLinesOfSemiRandomText(int numLines) {
		StringBuilder randomStringBuilder = new StringBuilder();
		String randomString;
	
		for(int i = 0; i < numLines; i++) {
			randomString = UUID.randomUUID().toString();
			randomStringBuilder.append(randomString);
			if(i%3==0)
				randomStringBuilder.append("\n");
			else if(i%3==1)
				randomStringBuilder.append("\r\n");
			else if(i%3==2)
				randomStringBuilder.append("\r");
		}
		return randomStringBuilder.toString();
	}

	public static File newTempTxtFile() throws IOException {
		File f = File.createTempFile("aFileName_", ".txt");
		f.deleteOnExit();
		return f;
	}

	public static void assertBytesEqual(byte[] data, byte[] dataOut) {
		assertNotNull(dataOut);
		assertEquals(data.length, dataOut.length);
		for(int x=0;x<data.length;x++){
			byte expected = data[x];
			byte actual = dataOut[x];
			if(expected!=actual){
				throw new RuntimeException("At byte # " + x + ", I was expectecting " + expected + " but found " +actual);
			}
		}
	}

	public static void writeToFile(File testFile, String content) throws IOException {
		FileWriter fstream = new FileWriter(testFile);
		BufferedWriter writer = new BufferedWriter(fstream);
		writer.write(content);
		writer.close();
	}

}
