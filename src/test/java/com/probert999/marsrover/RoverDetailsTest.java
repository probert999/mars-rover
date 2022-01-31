package com.probert999.marsrover;

import com.probert999.marsrover.model.RoverDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoverDetailsTest {

    private String roverName = "ROVER-TEST";
    private int xPosition = 100;
    private int yPosition = 200;
    private char heading = 'N';
    private RoverDetails testRover;

    @BeforeEach
    public void setup()
    {
       testRover = new RoverDetails(roverName, xPosition, yPosition, heading);
    }

    @Test
    public void shouldBeAbleToCreateRoverDetailsAndGetCorrectName(){
        assertEquals(roverName, testRover.getRoverName());
    }

    @Test
    public void shouldBeAbleToCreateRoverDetailsAndGetCorrectXPosition(){
        assertEquals(xPosition, testRover.getXPosition());
    }

    @Test
    public void shouldBeAbleToCreateRoverDetailsAndGetCorrectYPosition(){
        assertEquals(yPosition, testRover.getYPosition());
    }

    @Test
    public void shouldBeAbleToCreateRoverDetailsAndGetCorrectHeading(){
        assertEquals('N', testRover.getHeading());
    }

    @Test
    public void shouldBeAbleToUpdateThePositionAndHeading(){
        testRover.updatePosition(50, 51, 'S');
        assertEquals(50, testRover.getXPosition());
        assertEquals(51, testRover.getYPosition());
        assertEquals('S', testRover.getHeading());
    }

}
