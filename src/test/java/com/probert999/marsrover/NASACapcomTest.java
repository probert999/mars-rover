package com.probert999.marsrover;

import com.probert999.marsrover.service.NASACapcomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class NASACapcomTest {

    @Test
    public void shouldBeAbleToCreateOneQuadPlateauFromInstruction()
    {
        NASACapcomService capcom = new NASACapcomService();

        capcom.processInstruction("5 5");

        assertEquals("Plateau-1 (5,5)", capcom.getPlateauList());
    }

    @Test
    public void shouldBeAbleToCreateMoreThanOneQuadPlateauFromInstruction()
    {
        NASACapcomService capcom = new NASACapcomService();

        capcom.processInstruction("5 5");
        capcom.processInstruction("8 8");

        assertEquals("Plateau-1 (5,5)\nPlateau-2 (8,8)", capcom.getPlateauList());
    }

    @Test
    public void shouldBeAbleToCreateARoverFromInstruction()
    {
        NASACapcomService capcom = new NASACapcomService();

        capcom.processInstruction("5 5");
        capcom.processInstruction("0 0 N");

        assertEquals("Rover-1 on Plateau-1 (5,5) at position and heading 0 0 N", capcom.getRoverList());
    }

    @Test
    public void shouldBeAbleToSpinRoverLeftFromInstruction()
    {
        NASACapcomService capcom = new NASACapcomService();

        capcom.processInstruction("5 5");
        capcom.processInstruction("0 0 N");
        capcom.processInstruction("L");

        assertEquals("Rover-1 on Plateau-1 (5,5) at position and heading 0 0 W", capcom.getRoverList());
    }

}
