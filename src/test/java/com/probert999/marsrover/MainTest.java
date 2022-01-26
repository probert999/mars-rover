package com.probert999.marsrover;

import com.probert999.marsrover.app.Main;
import com.probert999.marsrover.service.NASACapcomService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void shouldTakeUserInput() {
        NASACapcomService capcom = new NASACapcomService();
        String[] args = new String[0];
        String input = "5 5\n1 2 N\nLMLMLMLMM\nfinish";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("1 3 N",  Main.start(capcom, in,args));
    }

    @Test
    public void shouldTakeFileAsInputToStartMethod() {
        NASACapcomService capcom = new NASACapcomService();
        String[] args = {"testdata\\shouldTakeFileInputTest.txt"};
        InputStream in = System.in;
        assertEquals("1 3 N",  Main.start(capcom, in,args));
    }

    @Test
    public void shouldStopProcessingIfFileNotFound() {
        NASACapcomService capcom = new NASACapcomService();
        String[] args = {"nosuchfile.nosuchfile"};
        InputStream in = System.in;
        assertNull(Main.start(capcom, in,args));
    }

    @Test
    public void shouldHandleInvalidCommandInFile() {
        NASACapcomService capcom = new NASACapcomService();
        String[] args = {"testdata\\shouldHandleInvalidCommandInFile.txt"};
        InputStream in = System.in;
        assertEquals("1 3 N",  Main.start(capcom,in,args));
    }



}