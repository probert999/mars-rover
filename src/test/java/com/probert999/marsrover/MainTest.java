package com.probert999.marsrover;

import com.probert999.marsrover.app.Main;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void shouldTakeUserInput() {
        Main main = new Main();
        String[] args = new String[0];
        String input = "5 5\n1 2 N\nLMLMLMLMM\nfinish";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("1 3 N",  Main.start(in,args));
    }

}
