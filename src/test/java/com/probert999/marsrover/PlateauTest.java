package com.probert999.marsrover;

import com.probert999.marsrover.app.model.QuadPlateau;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlateauTest {

    @Test
    public void shouldReturnCorrectPlateauId()
    {
        String plateauId = "QuadTestId";
        QuadPlateau quadPlateau = new QuadPlateau(plateauId, 5, 5);
        assertEquals(plateauId, quadPlateau.getPlateauId());
    }

    @Test
    public void shouldReturnCoordinateIsValid()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5);
        assertEquals(true, quadPlateau.isValidCoordinate(0,0));
    }

    @Test
    public void shouldReturnCoordinateIsInValid()
    {
        QuadPlateau quadPlateau = new QuadPlateau("QuadTestId", 5, 5);
        assertEquals(false, quadPlateau.isValidCoordinate(-1,0));
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
    
}
