package com.probert999.marsrover;

import com.probert999.marsrover.app.Main;
import com.probert999.marsrover.service.NASACapcomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MainTest {

    private Path resourceDirectory;
    private String absolutePath;

    @BeforeEach
    public void setup()
    {
        resourceDirectory = Paths.get("src","test","resources","testdata");
        absolutePath = resourceDirectory.toFile().getAbsolutePath();
    }

    @Test
    public void shouldTakeUserInput() {
        NASACapcomService capcom = new NASACapcomService();
        String[] args = new String[0];
        String input = "5 5\n1 2 N\nLMLMLMLMM\nfinish";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("ROVER-1 at 1 3 N on PLATEAU-1",  Main.start(capcom, in,args));
    }

    @Test
    public void shouldTakeFileAsInputToStartMethod() {
        NASACapcomService capcom = new NASACapcomService();
        String testFile = absolutePath + "\\shouldHandleInvalidCommandInFile.txt";
        String[] args = {testFile};
        InputStream in = System.in;
        assertEquals("ROVER-1 at 1 3 N on PLATEAU-1",  Main.start(capcom, in,args));
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
        String testFile = absolutePath + "\\shouldHandleInvalidCommandInFile.txt";
        String[] args = {testFile};
        InputStream in = System.in;
        assertEquals("ROVER-1 at 1 3 N on PLATEAU-1",  Main.start(capcom,in,args));
    }

    @Test
    public void shouldTestMapShowAndHide() {
        NASACapcomService capcom = new NASACapcomService();
        String testFile = absolutePath + "\\quadMap.txt";
        String[] args = {testFile};
        InputStream in = System.in;
        assertEquals("ROVER-1 at 0 7 W on PLATEAU-1\nROVER-2 at 500 493 S on PLATEAU-2",  Main.start(capcom,in,args));
    }

    @Test
    public void shouldHandleBigMoves() {
        NASACapcomService capcom = new NASACapcomService();

        String testFile = absolutePath + "\\bigMap.txt";
        String[] args = {testFile};
        InputStream in = System.in;
        assertEquals("ROVER-1 at 0 0 N on PLATEAU-1",  Main.start(capcom,in,args));
    }

    @Test
    public void shouldHandleFileWithNoFinishCommand() {
        NASACapcomService capcom = new NASACapcomService();

        String testFile = absolutePath + "\\shouldHandleNoFinishCommand.txt";
        String[] args = {testFile};
        InputStream in = System.in;
        assertEquals("ROVER-1 at 5 5 N on PLATEAU-1",  Main.start(capcom,in,args));
    }

}
