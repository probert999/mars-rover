package com.probert999.marsrover;

import com.probert999.marsrover.service.NASACapcomService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class NASACapcomTest {

  @Test
  public void shouldBeAbleToCreateOneQuadPlateauFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");

    assertEquals("PLATEAU-1 (5,5) Map visible: false", capcom.getPlateauList());
  }

  @Test
  public void shouldBeAbleToCreateMoreThanOneQuadPlateauFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("8 8");

    assertEquals("PLATEAU-1 (5,5) Map visible: false\nPLATEAU-2 (8,8) Map visible: false", capcom.getPlateauList());
  }

  @Test
  public void shouldBeAbleToCreateARoverFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");

    assertEquals("ROVER-1 on PLATEAU-1 (5,5) at position and heading 0 0 N", capcom.getRoverList());
  }

  @Test
  public void shouldNotBeAbleToCreateARoverWithoutAPlateauFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    assertThrows(IllegalStateException.class, () -> capcom.processInstruction("5 5 N"));
  }

  @Test
  public void shouldBeAbleToSpinRoverLeftFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");
    capcom.processInstruction("L");

    assertEquals("ROVER-1 on PLATEAU-1 (5,5) at position and heading 0 0 W", capcom.getRoverList());
  }

  @Test
  public void shouldBeAbleToSpinRoverRightFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");
    capcom.processInstruction("R");

    assertEquals("ROVER-1 on PLATEAU-1 (5,5) at position and heading 0 0 E", capcom.getRoverList());
  }

  @Test
  public void shouldBeAbleToMoveRoverToValidSpaceFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");
    capcom.processInstruction("M");

    assertEquals("ROVER-1 on PLATEAU-1 (5,5) at position and heading 0 1 N", capcom.getRoverList());
  }


  @Test
  public void shouldNotBeAbleToMoveRoverToAnInvalidSpaceFromInstruction() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 S");

    assertThrows(IllegalStateException.class, () -> capcom.processInstruction("M"));

    assertEquals("ROVER-1 on PLATEAU-1 (5,5) at position and heading 0 0 S", capcom.getRoverList());
  }

  @Test
  public void shouldBeAbleToProcessMultipleMovesInSequence() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("1 2 N");

    capcom.processInstruction("LMLMLMLMM");

    assertEquals("ROVER-1 on PLATEAU-1 (5,5) at position and heading 1 3 N", capcom.getRoverList());
  }

  @Test
  public void shouldBeAbleToAddTwoRovers() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("1 2 N");
    capcom.processInstruction("3 3 E");

    assertEquals(
        "ROVER-1 on PLATEAU-1 (5,5) at position and heading 1 2 N\n" +
                "ROVER-2 on PLATEAU-1 (5,5) at position and heading 3 3 E",
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
            "ROVER-1 on PLATEAU-1 (5,5) at position and heading 0 1 N\n" +
                    "ROVER-2 on PLATEAU-1 (5,5) at position and heading 1 0 E",
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
            "ROVER-1 on PLATEAU-1 (5,5) at position and heading 0 0 N",
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
            "ROVER-1 on PLATEAU-1 (5,5) at position and heading 0 0 N\n" +
            "ROVER-2 on PLATEAU-1 (5,5) at position and heading 1 0 W",
            capcom.getRoverList());
  }

  @Test
  public void shouldProduceStatusReportForOneRover()
  {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");

    assertEquals("ROVER-1 at 0 0 N on PLATEAU-1",capcom.getStatusReport());
  }

  @Test
  public void shouldProduceStatusReportForMoreThanOneRover()
  {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("0 0 N");
    capcom.processInstruction("1 2 E");

    assertEquals("ROVER-1 at 0 0 N on PLATEAU-1\nROVER-2 at 1 2 E on PLATEAU-1",capcom.getStatusReport());
  }

  @Test
  public void shouldNotBeAbleToIssueMoveInstructionWithNoRovers() {
    NASACapcomService capcom = new NASACapcomService();

    assertThrows(IllegalStateException.class, () -> capcom.processInstruction("M"));

  }

  @Test
  public void shouldBeAbleToCreateAPlateauOfDoubleDigitSize() {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("10 10");
    assertEquals("PLATEAU-1 (10,10) Map visible: false", capcom.getPlateauList());
  }

  @Test
  public void shouldProduceStatusReportForNoRovers()
  {
    NASACapcomService capcom = new NASACapcomService();
    assertEquals("No rovers to report",capcom.getRoverList());
  }

  @Test
  public void shouldProduceStatusReportForNoPlateaus()
  {
    NASACapcomService capcom = new NASACapcomService();
    assertEquals("No plateaus to report",capcom.getPlateauList());
  }

  @Test
  public void shouldBeAbleToSwitchBetweenPlateaus()
  {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("3 3");
    capcom.processInstruction("SWITCH PLATEAU-1");
    capcom.processInstruction("3 4 S");

    assertEquals("ROVER-1 at 3 4 S on PLATEAU-1",capcom.getStatusReport());
  }

  @Test
  public void shouldNotBeAbleToSwitchPlateauIfNoneExist()
  {
    NASACapcomService capcom = new NASACapcomService();
    assertEquals("PLATEAU-1 not found",capcom.processInstruction("SWITCH PLATEAU-1"));
  }

  @Test
  public void shouldNotBeAbleToSwitchToUnknownPlateau()
  {
    NASACapcomService capcom = new NASACapcomService();
    capcom.processInstruction("5 5");
    capcom.processInstruction("3 3");
    capcom.processInstruction("2 2");
    capcom.processInstruction("SWITCH PLATEAU-10");

    assertEquals("PLATEAU-10 not found",capcom.processInstruction("SWITCH PLATEAU-10"));
  }

  @Test
  public void shouldBeAbleToSwitchBetweenRovers()
  {
    NASACapcomService capcom = new NASACapcomService();

    capcom.processInstruction("5 5");
    capcom.processInstruction("3 4 S");
    capcom.processInstruction("2 2 N");

    assertEquals("Rover is now ROVER-1. Plateau is now PLATEAU-1",capcom.processInstruction("SWITCH ROVER-1"));
  }

  @Test
  public void shouldNotBeAbleToSwitchToUnknownRover()
  {
    NASACapcomService capcom = new NASACapcomService();
    assertEquals("ROVER-1 not found",capcom.processInstruction("SWITCH ROVER-1"));
  }

  @Test
  public void shouldNotBeAbleToShowMapsWithNoPlateaus()
  {
    NASACapcomService capcom = new NASACapcomService();
    assertThrows(IllegalStateException.class, () -> capcom.processInstruction("SHOW MAP"));
  }

  @Test
  public void shouldBeAbleToShowMapsWithMapEnabledPlateaus()
  {
    NASACapcomService capcom = new NASACapcomService();
    capcom.processInstruction("5 5");
    capcom.processInstruction("SHOW MAP");
    assertEquals("PLATEAU-1 (5,5) Map visible: true", capcom.getPlateauList());
  }

  @Test
  public void shouldNotBeAbleToHideMapsWithNoPlateaus()
  {
    NASACapcomService capcom = new NASACapcomService();
    assertThrows(IllegalStateException.class, () -> capcom.processInstruction("HIDE MAP"));
  }

  @Test
  public void shouldBeAbleToHideMapsWithMapEnabledPlateaus()
  {
    NASACapcomService capcom = new NASACapcomService();
    capcom.processInstruction("5 5");
    capcom.processInstruction("SHOW MAP");
    capcom.processInstruction("HIDE MAP");
    assertEquals("PLATEAU-1 (5,5) Map visible: false", capcom.getPlateauList());
  }

  @Test
  public void shouldNotShowMapAfterMoveRoverMapNotVisible()
  {
    NASACapcomService capcom = new NASACapcomService();
    capcom.processInstruction("5 5");
    capcom.processInstruction("SHOW MAP");
    capcom.processInstruction("HIDE MAP");
    capcom.processInstruction("3 4 S");
    assertEquals("PLATEAU-1 (5,5) Map visible: false", capcom.getPlateauList());
  }

  @Test
  public void shouldShowMapAfterMoveRoverMapVisible()
  {
    NASACapcomService capcom = new NASACapcomService();
    capcom.processInstruction("5 5");
    capcom.processInstruction("SHOW MAP");
    capcom.processInstruction("3 4 S");
    assertEquals("PLATEAU-1 (5,5) Map visible: true", capcom.getPlateauList());
  }


}
