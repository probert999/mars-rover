package com.probert999.marsrover;

import com.probert999.marsrover.app.model.SurfaceRover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoverTest {

    @Test
    public void shouldReturnCorrectRoverId()
    {
        String testRoverId = "Rover1";
        SurfaceRover testRover = new SurfaceRover(testRoverId);
        assertEquals(testRoverId, testRover.getRoverId());
    }

}
