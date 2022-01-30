package com.probert999.marsrover.model;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.Predicate;

public abstract class NASACapcom implements NASACapcomInterface {

  protected LinkedHashMap<String, Plateau> plateauMap = new LinkedHashMap<>();
  protected LinkedHashMap<String, Rover> roverMap = new LinkedHashMap<>();
  protected Plateau currentPlateau = null;
  protected Rover currentRover = null;

  protected Rover getRoverById(String roverId)
  {
    return roverMap.get(roverId);
  }

  protected Plateau getPlateauById(String plateuaId)
  {
    return plateauMap.get(plateuaId);
  }

  protected void createPlateau(int xMaximum, int yMaximum) {
    String plateauId =
        MessageFormat.format("PLATEAU-{0}", plateauMap.size() + 1);
    QuadPlateau quadPlateau = new QuadPlateau(plateauId, xMaximum, yMaximum);

    plateauMap.put(plateauId, quadPlateau);
    currentPlateau = quadPlateau;
  }

  protected void createRover(int xCoordinate, int yCoordinate, HeadingEnum heading) {
    if (currentPlateau == null) {
      throw new IllegalStateException("No current plateau set");
    }

    if (!currentPlateau.isValidCoordinate(xCoordinate, yCoordinate)) {
      throw new IllegalStateException("Cannot land rover on plateau at specified coordinates");
    }

    String roverId = MessageFormat.format("ROVER-{0}", roverMap.size() + 1);

    SurfaceRover rover = new SurfaceRover(this, roverId, currentPlateau, xCoordinate, yCoordinate, heading);
    currentRover = rover;
    roverMap.put(roverId, rover);
  }

  public String processInstruction(String instruction)
  {
    String outcomeMessage = "Processed instruction";

    InstructionTypeEnum instructionType = InstructionTypeEnum.getInstructionType(instruction);

    switch (instructionType) {
      case CREATE_PLATEAU -> {
        List<Integer> coordinates = Arrays.stream(instruction.split(" "))
                .map(Integer::parseInt).toList();
        int xMaximum = coordinates.get(0);
        int yMaximum = coordinates.get(1);
        createPlateau(xMaximum, yMaximum);
        outcomeMessage = MessageFormat.format("{0} created", currentPlateau.getId());
      }
      case SWITCH_PLATEAU -> {
        List<String> parameters = Arrays.stream(instruction.split(" ")).toList();
        String plateauName = parameters.get(1);
        Plateau plateau = getPlateauById(plateauName);
        if (plateau != null)
        {
          currentPlateau = plateau;
          outcomeMessage = MessageFormat.format("Current plateau is now {0}", currentPlateau.getId());
        }
        else
        {
          outcomeMessage = MessageFormat.format("{0} not found", plateauName);
        }
      }
      case CREATE_ROVER -> {
        List<Integer> coordinates =
                Arrays.stream(instruction.substring(0, instruction.length() - 1).split(" "))
                        .map(Integer::parseInt).toList();
        int xCoordinate = coordinates.get(0);
        int yCoordinate = coordinates.get(1);
        HeadingEnum heading = HeadingEnum.getByInitial(instruction.charAt(instruction.length()-1));
        createRover(xCoordinate, yCoordinate, heading);
        outcomeMessage = MessageFormat.format("{0} created", currentRover.getId());
      }

      case SWITCH_ROVER -> {
        List<String> parameters = Arrays.stream(instruction.split(" ")).toList();
        String roverName = parameters.get(1);
        Rover rover = getRoverById(roverName);
        if (rover != null)
        {
          currentRover = rover;
          outcomeMessage = MessageFormat.format("Current rover is now {0}", rover.getId());
        }
        else
        {
          outcomeMessage = MessageFormat.format("{0} not found", roverName);
        }
      }

      case MOVE_ROVER -> {
        if (currentRover != null) {
          processMoveSequence(instruction);
        }
        else {
          throw new IllegalStateException("Invalid instruction received: No Rovers exist");
        }
      }
      case INVALID_INSTRUCTION -> throw new IllegalArgumentException("Invalid instruction received");
    }
    return outcomeMessage;
  }

  private void processMoveSequence(String moveSequence)
  {
    char[] moves = moveSequence.toCharArray();
    for (char move : moves) {
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
    List<Map.Entry<String, Rover>> rovers = roverMap.entrySet().stream().toList();

    for (Map.Entry<String, Rover> rover : rovers) {
     statusReport.add(rover.getValue().getLocation());
    }

    return statusReport.toString();
  }


}
