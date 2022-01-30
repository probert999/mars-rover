package com.probert999.marsrover;

import com.probert999.marsrover.model.QuadPlateau;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlateauTest {

    @Test
    public void shouldReturnCorrectPlateauId()
    {
        String plateauId = "QuadTestId";
        QuadPlateau quadPlateau = new QuadPlateau(plateauId, 5, 5, false);
        assertEquals(plateauId, quadPlateau.getId());
    }

    @Test
    public void shouldReturnCoordinateIsValid()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5, false);
        assertTrue(quadPlateau.isValidCoordinate(0, 0));
    }

    @Test
    public void shouldReturnCoordinateIsInValid()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5, false);
        assertFalse(quadPlateau.isValidCoordinate(-1, 0));
    }

    @Test
    public void shouldRejectNegativeXCoordinate()
    {
        assertThrows(
                IllegalArgumentException.class, () -> new QuadPlateau("QuadTestId", -5, 5, false));
    }

    @Test
    public void shouldRejectNegativeYCoordinate()
    {
        assertThrows(
                IllegalArgumentException.class, () -> new QuadPlateau("QuadTestId", 5, -5, false));
    }

    @Test
    public void shouldRejectXCoordinateGreaterThanMaxSize()
    {
        assertThrows(
                IllegalArgumentException.class, () -> new QuadPlateau("QuadTestId", 99999999, 0, false));
    }

    @Test
    public void shouldRejectYCoordinateGreaterThanMaxSize()
    {
        assertThrows(
                IllegalArgumentException.class, () -> new QuadPlateau("QuadTestId", 0, 99999999, false));
    }

    @Test
    public void shouldBeAbleToStoreRoverPosition()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5, false);
        assertTrue(quadPlateau.storeRoverPosition("Rover-1",5,5));
    }

    @Test
    public void shouldNotBeAbleToStoreInvalidPositionForRover()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5, false);
        assertFalse(quadPlateau.storeRoverPosition("Rover-1",10,5));
    }

    @Test
    public void shouldNotBeAbleToStorePositionForRoverIfAnotherRoverIsAlreadyThere()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5, false);
        assertTrue(quadPlateau.storeRoverPosition("Rover-1",1,1));
        assertFalse(quadPlateau.storeRoverPosition("Rover-2",1,1));
    }

    @Test
    public void shouldReturnNotSupportedForShowMapIfNotSupported()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5, false);
        assertThrows(IllegalStateException.class, () -> quadPlateau.showMap());
    }

    @Test
    public void shouldShowMapIfSupported()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5, true);
        quadPlateau.showMap();
        assertTrue(quadPlateau.isMapVisible());
    }

    @Test
    public void shouldBeAbleToHideMapIfSupported()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5, true);
        quadPlateau.hideMap(false);
        assertFalse(quadPlateau.isMapVisible());
    }

    @Test
    public void shouldReturnNotVisibleMapIfNotSupported()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5, false);
        assertFalse(quadPlateau.isMapVisible());
    }


}
