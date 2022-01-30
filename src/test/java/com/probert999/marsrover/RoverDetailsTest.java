package com.probert999.marsrover;



import com.probert999.marsrover.model.RoverDetails;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverDetailsTest {

    private String roverName = "ROVER-TEST";
    private int xPosition = 100;
    private int yPosition = 200;
    private RoverDetails testRover;

    @BeforeEach
    public void setup()
    {
       testRover = new RoverDetails(roverName, xPosition, yPosition);
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

}
