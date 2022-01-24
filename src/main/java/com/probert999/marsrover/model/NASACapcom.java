package com.probert999.marsrover.model;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

public abstract class NASACapcom implements NASACapcomInterface {

  private List<Plateau> plateauList = new ArrayList<>();
  private LinkedHashMap<Rover, Plateau> roverMap = new LinkedHashMap<Rover, Plateau>();
  private Plateau currentPlateau = null;

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
    String roverId = MessageFormat.format("Rover-{0}", roverMap.size() + 1);

    SurfaceRover rover = new SurfaceRover(this, roverId);
    boolean positionSet = rover.setPosition(xCoordinate, yCoordinate, heading);
    roverMap.put(rover, currentPlateau);
  }

  public String getPlateauList() {
    return plateauList.stream().map(Plateau::getPlateauId).collect(Collectors.joining("\n"));
  }

  public String getRoverList() {
    String roverList = "";

    for(Map.Entry<Rover, Plateau> entry : roverMap.entrySet()){
      Rover rover = entry.getKey();
      Plateau plateau = entry.getValue();

      roverList =
          MessageFormat.format(
              "{0} on {1} at position and heading {2}",
              rover.getRoverId(), plateau.getPlateauId(), rover.getLocation());
    }
    return roverList;
  }

  private InstructionTypeEnum getInstructionType(String instruction)
  {
    return InstructionTypeEnum.CREATE_PLATEAU;
  }

}
