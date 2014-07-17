package com.cj.nan.koans.java.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.cj.nan.koans.java.io.Wrangler;
import com.cj.nan.koans.java.io.impl.BufferingOutputStreamWrapper;
import com.cj.nan.koans.java.io.impl.Utils;

public class WranglerTest {

    @Test 
    public void testWritingThroughBufferedCharacterStreams() throws Exception {
        // given
        StringWriter sWriter = new StringWriter();
        BufferedWriter bWriter = new BufferedWriter(sWriter, 1024);
        Wrangler wrangler = new Wrangler();

        // when
        wrangler.writeThrough("I am my own grandpa", bWriter);

        // then
        assertEquals("I am my own grandpa", sWriter.toString());
    }

    @Test
    public void testWritingThroughBufferedByteStreams() throws Exception {
        //given
        ByteArrayOutputStream finalDestination = new ByteArrayOutputStream();
        BufferingOutputStreamWrapper outputStream = new BufferingOutputStreamWrapper(1024, finalDestination);
        Wrangler wrangler = new Wrangler();

        // when
        wrangler.writeThrough(new byte[]{1, 2, 3}, outputStream);

        // then
        byte[] result = finalDestination.toByteArray();
        Utils.assertBytesEqual(new byte[]{1, 2, 3}, result);
        assertTrue("The stream should not be closed", !outputStream.isClosed());

    }

    @Test
    public void testCopyBytesAndClose() throws Exception {
        // GIVEN
        byte[] data = Utils.randomBytesWithEveryPossibleByteValueRepresentedAtLeastOnce();
        Utils.ByteArrayInputStreamThatExposesClosedStatus in = new Utils.ByteArrayInputStreamThatExposesClosedStatus(data);
        Utils.ByteArrayOutputStreamThatExposesClosedStatus out = new Utils.ByteArrayOutputStreamThatExposesClosedStatus();
        Wrangler wrangler = new Wrangler();

        // when
        wrangler.copyBytesAndClose(in, out);

        // then
        byte[] dataOut = out.toByteArray();
        Utils.assertBytesEqual(data, dataOut);
        Assert.assertTrue(in.wasClosed);
        Assert.assertTrue(out.wasClosed);
    }


    @Test
    public void testReadingBinaryData() throws Exception {
        // GIVEN
        byte[] data = Utils.randomBytesWithEveryPossibleByteValueRepresentedAtLeastOnce();
        InputStream in = new ByteArrayInputStream(data);
        Wrangler wrangler = new Wrangler();

        // when
        byte[] dataOut = wrangler.readUntilEndOfStream(in);

        Utils.assertBytesEqual(data, dataOut);
    }

    @Test
    public void testContentsOfFile() throws Exception {
        // GIVEN
        String testFileContents = Utils.createLinesOfSemiRandomText(10);
        File theTestFile =  Utils.newTempTxtFile();
        Utils.writeToFile(theTestFile, testFileContents);
        Wrangler wrangler = new Wrangler();

        // WHEN
        String contentsBack = wrangler.contentsOfAsciiFile(theTestFile.getAbsolutePath());
        
        // THEN
        Assert.assertEquals(contentsBack, testFileContents);
    }


    @Test
    public void replaceTextInAFile() throws Exception {
        String fileContents = "A hornet flew over the cookoos nest. hornet hornet";

        File theTestFile = Utils.newTempTxtFile();
        Utils.writeToFile(theTestFile, fileContents);
        Wrangler wrangler = new Wrangler();

        wrangler.replaceTextInAsciiFile(theTestFile.getAbsolutePath(), "hornet", "bird");

        String contentsBack = wrangler.contentsOfAsciiFile(theTestFile.getAbsolutePath());

        Assert.assertEquals(contentsBack, "A bird flew over the cookoos nest. bird bird");        
    }

    @Test
    public void histogramWordsInAFile() throws Exception {
        String fileContents = "bird cat dog dog hippo fish dog hippo hippogryph chicken";
        File theTestFile = Utils.newTempTxtFile();
        Utils.writeToFile(theTestFile, fileContents);
        Wrangler wrangler = new Wrangler();

        Map<String, Integer> wordCounts = wrangler.asciiWordCounts(theTestFile.getAbsolutePath());

        Assert.assertNotNull(wordCounts);
        Assert.assertEquals(Integer.valueOf(2), wordCounts.get("hippo"));

        Assert.assertEquals(Integer.valueOf(0), wordCounts.get("wordNotThereLOL"));
    }

    @Test
    public void testLinesInFile() throws Exception {
        // GIVEN
        int numLines = new Random().nextInt(5)+17;
        String testFileContents = Utils.createLinesOfSemiRandomText(numLines);
        File theTestFile = Utils.newTempTxtFile();
        Utils.writeToFile(theTestFile, testFileContents);
        Wrangler wrangler = new Wrangler();
        
        // WHEN
        int linesReturned = wrangler.linesInAsciiFile(theTestFile.getAbsolutePath());
        
        // THEN
        Assert.assertEquals(numLines, linesReturned);
    }
}
