package com.probert999.marsrover;

import com.probert999.marsrover.model.DirectionEnum;
import com.probert999.marsrover.model.HeadingEnum;
import com.probert999.marsrover.model.SurfaceRover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals(true, testRover.setPosition(0,0, HeadingEnum.NORTH));
    }

    @Test
    public void shouldNotBeAbleToSetNegativeXCoordinates()
    {
        SurfaceRover testRover = new SurfaceRover("testRover1");
        assertEquals(false, testRover.setPosition(-1,0, HeadingEnum.NORTH));
    }

    @Test
    public void shouldNotBeAbleToSetNegativeYCoordinates()
    {
        SurfaceRover testRover = new SurfaceRover("testRover1");
        assertEquals(false, testRover.setPosition(0,-1, HeadingEnum.NORTH));
    }

    @Test
    public void shouldBeAbleToGetLocation()
    {
        SurfaceRover testRover = new SurfaceRover("testRover1");
        testRover.setPosition(0,0, HeadingEnum.NORTH);
        assertEquals("0 0 N", testRover.getLocation());
    }

    @Test
    public void shouldNotBeAbleToGetLocationIfItHasNotBeenSet()
    {
        SurfaceRover testRover = new SurfaceRover("testRover1");
        assertThrows(
                IllegalStateException.class, () -> testRover.getLocation());
    }

    @Test
    public void shouldNotBeAbleToSpinLeft()
    {
        SurfaceRover testRover = new SurfaceRover("testRover1");
        testRover.setPosition(0,0, HeadingEnum.NORTH);
        testRover.spin(DirectionEnum.LEFT);
        assertEquals("0 0 W", testRover.getLocation());

    }


    }
