package com.cj.nan.koans.java.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

public class Wrangler {

	public String contentsOfAsciiFile(String absolutePathToFile) throws Exception {
		throw notImplemented();
	}

	public void replaceTextInAsciiFile(String absolutePathToFile, String pattern, String replacement) throws Exception {
		throw notImplemented();
	}

	public Map<String, Integer> asciiWordCounts(String absolutePathToFile) throws Exception {
		throw notImplemented();
	}

	public int linesInAsciiFile(String absolutePathToFile) throws Exception {
		throw notImplemented();
	}

	public byte[] readUntilEndOfStream(InputStream in) throws Exception {
		throw notImplemented();
	}

	public void copyBytesAndClose(InputStream in, OutputStream out) throws Exception {
		throw notImplemented();
	}

	public void writeThrough(String stringToWrite, Writer writer) throws Exception {
		throw notImplemented();
	}

	public void writeThrough(byte[] dataToWrite, OutputStream outputStream) throws Exception {
		throw notImplemented();
	}
	
	private RuntimeException notImplemented(){
		return new RuntimeException("NOT IMPLEMENTED");
	}
}
