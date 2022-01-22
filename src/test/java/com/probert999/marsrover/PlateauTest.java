package com.probert999.marsrover;

import com.probert999.marsrover.app.model.QuadPlateau;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlateauTest {

    @Test
    public void shouldReturnCorrectPlateauId()
    {
        // Arrange
        String expectedResult = "QuadTestId";
        QuadPlateau quadPlateau = new QuadPlateau(expectedResult, 5, 5);

        // Act
        String actualResult = quadPlateau.getPlateauId();

        // Assert
        assertEquals(expectedResult, actualResult);
    }


}
