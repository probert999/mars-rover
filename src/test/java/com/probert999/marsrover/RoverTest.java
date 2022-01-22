package com.probert999.marsrover;

import com.probert999.marsrover.app.model.HeadingEnum;
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

    @Test
    public void shouldBeAbleToSetValidCoordinates()
    {
        SurfaceRover testRover = new SurfaceRover("testRover1");
        assertEquals(true, testRover.setPosition(0,0, HeadingEnum.North));
    }

    @Test
    public void shouldNotBeAbleToSetNegativeXCoordinates()
    {
        SurfaceRover testRover = new SurfaceRover("testRover1");
        assertEquals(false, testRover.setPosition(-1,0, HeadingEnum.North));
    }

    @Test
    public void shouldNotBeAbleToSetNegativeYCoordinates()
    {
        SurfaceRover testRover = new SurfaceRover("testRover1");
        assertEquals(false, testRover.setPosition(0,-1, HeadingEnum.North));
    }



}
