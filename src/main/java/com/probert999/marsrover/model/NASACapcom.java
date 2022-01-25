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
              roverMap.entrySet().stream().filter(plateauFilter).filter(roverFilter).toList();

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
    Predicate<Map.Entry<Rover, Plateau>> roverFilter = r -> r.getKey().getRoverId().equals(roverId);
    Optional<Map.Entry<Rover, Plateau>> rover = roverMap.entrySet().stream().filter(roverFilter).findFirst();
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
    rover.setPosition(this, xCoordinate, yCoordinate, heading);
    roverMap.put(rover, currentPlateau);
  }

  public void processInstruction(String instruction)
  {
    InstructionTypeEnum instructionType = InstructionTypeEnum.getInstructionType(instruction);

    switch (instructionType)
    {
      case CREATE_PLATEAU -> {
        List<Integer> coordinates = Arrays.stream(instruction.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int xMaximum = coordinates.get(0);
        int yMaximum = coordinates.get(1);
        createPlateau(xMaximum, yMaximum);
      }
      case CREATE_ROVER -> {
        List<Integer> coordinates =
                Arrays.stream(instruction.substring(0,instruction.length()-1).split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int xCoordinate = coordinates.get(0);
        int yCoordinate = coordinates.get(1);
        HeadingEnum heading = HeadingEnum.getByInitial(instruction.charAt(instruction.length()-1));
        createRover(xCoordinate, yCoordinate, heading);
      }
      case MOVE_ROVER -> {
        if (currentRover != null)
        {
          processMoveSequence(instruction);
        }
        else
        {
          throw new IllegalStateException("Invalid instruction received: No Rovers exist");
        }
      }

      case INVALID_INSTRUCTION -> throw new IllegalArgumentException("Invalid instruction received");

    }

  }

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
      switch (move) {
        case 'L' -> currentRover.spin(DirectionEnum.LEFT);
        case 'R' -> currentRover.spin(DirectionEnum.RIGHT);
        case 'M' -> currentRover.move();
      }
    }
  }

  public String getStatusReport()
  {
    StringJoiner statusReport = new StringJoiner("\n");
    List<Map.Entry<Rover, Plateau>> rovers = roverMap.entrySet().stream().toList();

    for (Map.Entry<Rover, Plateau> rover : rovers)
    {
     statusReport.add(rover.getKey().getLocation());
    }

    return statusReport.toString();
  }


}
