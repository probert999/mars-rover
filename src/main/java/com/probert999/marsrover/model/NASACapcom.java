package com.probert999.marsrover.model;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public abstract class NASACapcom implements NASACapcomInterface {

  protected List<Plateau> plateauList = new ArrayList<>();
  protected LinkedHashMap<Rover, Plateau> roverMap = new LinkedHashMap<>();
  protected Plateau currentPlateau = null;
  protected Rover currentRover = null;


  private boolean isValidAndFreeCoordinate(Plateau targetPlateau, Rover requestingRover, int xCoordinate, int yCoordinate) {

    boolean coordinatesValid = targetPlateau.isValidCoordinate(xCoordinate, yCoordinate);

    if (coordinatesValid)
    {
      String coordinateCheck = MessageFormat.format("{0} {1}", xCoordinate, yCoordinate);

      Predicate<Map.Entry<Rover, Plateau>> plateauFilter = filterPlateau->filterPlateau.getValue() == targetPlateau;
      Predicate<Map.Entry<Rover, Plateau>> roverFilter = filterRover->filterRover.getKey() != requestingRover;

      List<Map.Entry<Rover, Plateau>> filteredRovers =
              roverMap.entrySet().stream().filter(plateauFilter).filter(roverFilter).collect(Collectors.toList());

      for (Map.Entry<Rover, Plateau> rovertoCheck : filteredRovers)
      {
        if (rovertoCheck.getKey().getLocation().startsWith(coordinateCheck))
        {
          coordinatesValid = false;
        }
      }

    }

    return coordinatesValid;
  }

  protected Rover getRoverById(String roverId)
  {
    Optional<Map.Entry<Rover, Plateau>> rover = roverMap.entrySet().stream().filter(r -> r.getKey().getRoverId().equals(roverId)).findFirst();
    return rover.get().getKey();
  }

  protected void createPlateau(int xMaximum, int yMaximum) {
    String plateauId =
        MessageFormat.format("Plateau-{0} ({1},{2})", plateauList.size() + 1, xMaximum, yMaximum);
    QuadPlateau quadPlateau = new QuadPlateau(plateauId, xMaximum, yMaximum);
    plateauList.add(quadPlateau);
    currentPlateau = quadPlateau;
  }

  protected void createRover(int xCoordinate, int yCoordinate, HeadingEnum heading) {
    if (currentPlateau == null) {
      throw new IllegalStateException("No current plateau set");
    }

    if (!isValidAndFreeCoordinate(currentPlateau, null, xCoordinate, yCoordinate))
    {
      throw new IllegalStateException("Cannot land rover on plateau at specified coordinates");
    }

    String roverId = MessageFormat.format("Rover-{0}", roverMap.size() + 1);

    SurfaceRover rover = new SurfaceRover(this, roverId);
    currentRover = rover;
    boolean positionSet = rover.setPosition(xCoordinate, yCoordinate, heading);
    roverMap.put(rover, currentPlateau);
  }

  public void processInstruction(String instruction)
  {
    InstructionTypeEnum instructionType = InstructionTypeEnum.getInstructionType(instruction);

    switch (instructionType)
    {
      case CREATE_PLATEAU -> {
        int xMaximum = Character.getNumericValue(instruction.charAt(0));
        int yMaximum = Character.getNumericValue(instruction.charAt(2));
        createPlateau(xMaximum, yMaximum);
        break;
      }
      case CREATE_ROVER -> {
        int xCoordinate = Character.getNumericValue(instruction.charAt(0));
        int yCoordinate = Character.getNumericValue(instruction.charAt(2));
        HeadingEnum heading = HeadingEnum.getByInitial(instruction.charAt(4));
        createRover(xCoordinate, yCoordinate, heading);
        break;
      }
      case MOVE_ROVER -> {
        processMoveSequence(instruction);
        break;
      }
      case INVALID_INSTRUCTION -> {
        throw new IllegalArgumentException("Invalid instruction received");
      }

    }

  };

  public boolean isValidMove(String roverId, int xCoordinate, int yCoordinate)
  {
    Rover rover = getRoverById(roverId);
    Plateau checkPlateau = roverMap.get(rover);

    return isValidAndFreeCoordinate(checkPlateau, rover, xCoordinate, yCoordinate);
  }


  public void processMoveSequence(String moveSequence)
  {
    char[] moves = moveSequence.toCharArray();
    for (char move : moves)
    {
      switch (move)
      {
        case 'L' : currentRover.spin(DirectionEnum.LEFT); break;
        case 'R' : currentRover.spin(DirectionEnum.RIGHT); break;
        case 'M' : currentRover.move(); break;
      }
    }
  }


}
