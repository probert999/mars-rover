package com.probert999.marsrover;

import com.probert999.marsrover.model.QuadPlateau;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlateauTest {

    @Test
    public void shouldReturnCorrectPlateauId()
    {
        String plateauId = "QuadTestId";
        QuadPlateau quadPlateau = new QuadPlateau(plateauId, 5, 5);
        assertEquals(plateauId, quadPlateau.getId());
    }

    @Test
    public void shouldReturnCoordinateIsValid()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5);
        assertTrue(quadPlateau.isValidCoordinate(0, 0));
    }

    @Test
    public void shouldReturnCoordinateIsInValid()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5);
        assertFalse(quadPlateau.isValidCoordinate(-1, 0));
    }

    @Test
    public void shouldRejectNegativeXCoordinate()
    {
        assertThrows(
                IllegalArgumentException.class, () -> new QuadPlateau("QuadTestId", -5, 5));
    }

    @Test
    public void shouldRejectNegativeYCoordinate()
    {
        assertThrows(
                IllegalArgumentException.class, () -> new QuadPlateau("QuadTestId", 5, -5));
    }

    @Test
    public void shouldBeAbleToStoreRoverPosition()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5);
        assertTrue(quadPlateau.storeRoverPosition("Rover-1",5,5));
    }

    @Test
    public void shouldNotBeAbleToStoreInvalidPositionForRover()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5);
        assertFalse(quadPlateau.storeRoverPosition("Rover-1",10,5));
    }

    @Test
    public void shouldNotBeAbleToStorePositionForRoverIfAnotherRoverIsAlreadyThere()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5);
        assertTrue(quadPlateau.storeRoverPosition("Rover-1",1,1));
        assertFalse(quadPlateau.storeRoverPosition("Rover-2",1,1));
    }


}
