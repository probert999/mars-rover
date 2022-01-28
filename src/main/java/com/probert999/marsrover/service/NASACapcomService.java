package com.probert999.marsrover.service;

import com.probert999.marsrover.model.*;

import java.text.MessageFormat;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class NASACapcomService extends NASACapcom {

  public String getRoverList() {
    StringJoiner listOfRovers = new StringJoiner("\n");

    for (Map.Entry<Rover, Plateau> entry : roverMap.entrySet()) {
      Rover rover = entry.getKey();
      Plateau plateau = entry.getValue();

      listOfRovers.add(
          MessageFormat.format(
              "{0} on {1} {2} at position and heading {3}",
              rover.getRoverId(), plateau.getPlateauId(), plateau.getPlateauDimensions(), rover.getLocation()));
    }

    String roverReport = listOfRovers.toString();
    if (roverReport.isEmpty()) {
      roverReport = "No rovers to report";
    }
    return roverReport;
  }

  public String getPlateauList() {

    StringJoiner listOfPlateaus = new StringJoiner("\n");
     for (Plateau plateau : plateauList)
     {
       listOfPlateaus.add(
               MessageFormat.format(
                       "{0} {1}",
                       plateau.getPlateauId(), plateau.getPlateauDimensions()));
     }

    String plateauReport = listOfPlateaus.toString();
    if (plateauReport.isEmpty()) {
      plateauReport = "No plateaus to report";
    }
    return plateauReport;
  }
}
