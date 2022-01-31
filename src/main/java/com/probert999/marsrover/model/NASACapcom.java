package com.probert999.marsrover.model;

import java.text.MessageFormat;
import java.util.*;

public abstract class NASACapcom implements NASACapcomInterface {

  protected LinkedHashMap<String, Plateau> plateauMap = new LinkedHashMap<>();
  protected LinkedHashMap<String, Rover> roverMap = new LinkedHashMap<>();
  protected Plateau currentPlateau = null;
  protected Rover currentRover = null;


  private List<String> getParametersFromInstruction(String instruction) {
    return Arrays.stream(instruction.split(" ")).toList();
  }

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
    QuadPlateau quadPlateau = new QuadPlateau(plateauId, xMaximum, yMaximum, true);

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

  public String processInstruction(String instruction) {
    String outcomeMessage = "Processed instruction";

    InstructionTypeEnum instructionType = InstructionTypeEnum.getInstructionType(instruction);

    boolean mapRefresh = false;
    boolean showMap = false;
    List<String> parameters = getParametersFromInstruction(instruction);

    switch (instructionType) {
      case CREATE_PLATEAU -> {
        int xMaximum = Integer.parseInt(parameters.get(0));
        int yMaximum = Integer.parseInt(parameters.get(1));
        createPlateau(xMaximum, yMaximum);
        outcomeMessage = MessageFormat.format("{0} created", currentPlateau.getId());
      }
      case SWITCH_PLATEAU -> {
        String plateauName = parameters.get(1);
        Plateau plateau = getPlateauById(plateauName);
        if (plateau != null) {
          currentPlateau = plateau;
          outcomeMessage = MessageFormat.format("Current plateau is now {0}", currentPlateau.getId());
        }
        else {
          outcomeMessage = MessageFormat.format("{0} not found", plateauName);
        }
        mapRefresh = true;
      }
      case CREATE_ROVER -> {
        int xCoordinate = Integer.parseInt(parameters.get(0));
        int yCoordinate = Integer.parseInt(parameters.get(1));
        HeadingEnum heading = HeadingEnum.getByInitial(instruction.charAt(instruction.length()-1));
        createRover(xCoordinate, yCoordinate, heading);
        outcomeMessage = MessageFormat.format("{0} created", currentRover.getId());

        mapRefresh = true;
      }

      case SWITCH_ROVER -> {
        String roverName = parameters.get(1);
        Rover rover = getRoverById(roverName);
        if (rover != null) {
          currentRover = rover;
          currentPlateau = rover.getPlateau();
          outcomeMessage =
                  MessageFormat.format("Rover is now {0}. Plateau is now {1}", rover.getId(), currentPlateau.getId());
        }
        else {
          outcomeMessage = MessageFormat.format("{0} not found", roverName);
        }
      }

      case MOVE_ROVER -> {
        if (currentRover == null)
          throw new IllegalStateException("Invalid instruction received: No Rovers exist");

        processMoveSequence(instruction);
        mapRefresh = true;
      }
      case SHOW_MAP -> {
        if (currentPlateau == null)
          throw new IllegalStateException("Invalid instruction received: No Plateaus exist");

        showMap = true;
      }
      case HIDE_MAP -> {
        if (currentPlateau == null)
          throw new IllegalStateException("Invalid instruction received: No Plateaus exist");

        currentPlateau.hideMap(false);
      }
      case INVALID_INSTRUCTION -> throw new IllegalArgumentException("Invalid instruction received");
    }

    if (currentPlateau != null && (mapRefresh  && currentPlateau.isMapVisible() || showMap))  {
      currentPlateau.showMap();
    }

    return outcomeMessage;
  }

  private void processMoveSequence(String moveSequence) {
    char[] moves = moveSequence.toCharArray();
    for (char move : moves) {
      switch (move) {
        case 'L' -> currentRover.spin(this, DirectionEnum.LEFT);
        case 'R' -> currentRover.spin(this, DirectionEnum.RIGHT);
        case 'M' -> currentRover.move(this);
      }
    }
  }

  public String getStatusReport() {
    StringJoiner statusReport = new StringJoiner("\n");
    List<Map.Entry<String, Rover>> rovers = roverMap.entrySet().stream().toList();

    for (Map.Entry<String, Rover> rover : rovers) {
     statusReport.add(rover.getValue().getLocation());
    }

    return statusReport.toString();
  }


}
