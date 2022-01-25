package com.probert999.marsrover;

import com.probert999.marsrover.service.NASACapcomService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class NASACapcomTest {

  @Test
  public void shouldBeAbleToCreateOneQuadPlateauFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");

    assertEquals("Plateau-1 (5,5)", capcom.getPlateauList());
  }

  @Test
  public void shouldBeAbleToCreateMoreThanOneQuadPlateauFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("8 8");

    assertEquals("Plateau-1 (5,5)\nPlateau-2 (8,8)", capcom.getPlateauList());
  }

  @Test
  public void shouldBeAbleToCreateARoverFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");

    assertEquals("Rover-1 on Plateau-1 (5,5) at position and heading 0 0 N", capcom.getRoverList());
  }

  @Test
  public void shouldBeAbleToSpinRoverLeftFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");
    capcom.processInstruction("L");

    assertEquals("Rover-1 on Plateau-1 (5,5) at position and heading 0 0 W", capcom.getRoverList());
  }

  @Test
  public void shouldBeAbleToSpinRoverRightFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");
    capcom.processInstruction("R");

    assertEquals("Rover-1 on Plateau-1 (5,5) at position and heading 0 0 E", capcom.getRoverList());
  }

  @Test
  public void shouldBeAbleToMoveRoverToValidSpaceFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");
    capcom.processInstruction("M");

    assertEquals("Rover-1 on Plateau-1 (5,5) at position and heading 0 1 N", capcom.getRoverList());
  }

  @Test
  public void shouldReturnTrueForValidMoveInPlateauBounds() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");

    assertTrue(capcom.isValidMove("Rover-1", 0, 1));
  }

  @Test
  public void shouldReturnFalseForInvalidMove() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");

    assertFalse(capcom.isValidMove("Rover-1", 6, 0));
  }

  @Test
  public void shouldNotBeAbleToMoveRoverToAnInvalidSpaceFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 S");

    assertThrows(IllegalStateException.class, () -> capcom.processInstruction("M"));

    assertEquals("Rover-1 on Plateau-1 (5,5) at position and heading 0 0 S", capcom.getRoverList());
  }

  @Test
  public void shouldBeAbleToProcessMultipleMovesInSequence() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("1 2 N");

    capcom.processInstruction("LMLMLMLMM");

    assertEquals("Rover-1 on Plateau-1 (5,5) at position and heading 1 3 N", capcom.getRoverList());
  }

  @Test
  public void shouldBeAbleToAddTwoRovers() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("1 2 N");
    capcom.processInstruction("3 3 E");

    assertEquals(
        "Rover-1 on Plateau-1 (5,5) at position and heading 1 2 N\n" +
                "Rover-2 on Plateau-1 (5,5) at position and heading 3 3 E",
        capcom.getRoverList());
  }

  @Test
  public void shouldBeAbleToAddAndMoveTwoRovers() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");
    capcom.processInstruction("M");
    capcom.processInstruction("0 0 E");
    capcom.processInstruction("M");

    assertEquals(
            "Rover-1 on Plateau-1 (5,5) at position and heading 0 1 N\n" +
                    "Rover-2 on Plateau-1 (5,5) at position and heading 1 0 E",
            capcom.getRoverList());
  }

  @Test
  public void shouldNotBeAbleToCreatePlateauOfNegativeSize()
  {
    NASACapcomService capcom = new NASACapcomService();

    assertThrows(IllegalArgumentException.class, () -> capcom.processInstruction("-5 5"));
  }

  @Test
  public void shouldNotBeAbleToLandARoverOnInvalidCoordinates() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");

    assertThrows(IllegalStateException.class, () ->    capcom.processInstruction("6 0 N"));

  }

  @Test
  public void shouldNotBeAbleToLandARoverIfCoordinatesAreNotFree() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");

    assertThrows(IllegalStateException.class, () -> capcom.processInstruction("0 0 E"));

    assertEquals(
            "Rover-1 on Plateau-1 (5,5) at position and heading 0 0 N",
            capcom.getRoverList());
  }

  @Test
  public void shouldNotBeAbleToCrashIntoAnotherRover() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");
    capcom.processInstruction("1 0 W");

    assertThrows(IllegalStateException.class, () -> capcom.processInstruction("M"));

    assertEquals(
            "Rover-1 on Plateau-1 (5,5) at position and heading 0 0 N\n" +
            "Rover-2 on Plateau-1 (5,5) at position and heading 1 0 W",
            capcom.getRoverList());
  }

  @Test
  public void shouldProduceStatusReportForOneRover()
  {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");

    assertEquals("0 0 N",capcom.getStatusReport());
  }

  @Test
  public void shouldProduceStatusReportForMoreThanOneRover()
  {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");
    capcom.processInstruction("1 2 E");
    capcom.processInstruction("3 4 S");

    assertEquals("0 0 N\n1 2 E\n3 4 S",capcom.getStatusReport());
  }

  @Test
  public void shouldNotBeAbleToIssueMoveInstructionWithNoRovers() {
    NASACapcomService capcom = new NASACapcomService();

    assertThrows(IllegalStateException.class, () ->     capcom.processInstruction("M"));

  }

  @Test
  public void shouldBeAbleToCreateAPlateauOfDoubleDigitSize() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("10 10");

    assertEquals("Plateau-1 (10,10)", capcom.getPlateauList());
  }

}
