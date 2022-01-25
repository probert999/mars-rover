package com.probert999.marsrover.service;

import com.probert999.marsrover.model.*;

import java.text.MessageFormat;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class NASACapcomService extends NASACapcom {

  public String getRoverList() {
    StringJoiner roverList = new StringJoiner("\n");

    for (Map.Entry<Rover, Plateau> entry : roverMap.entrySet()) {
      Rover rover = entry.getKey();
      Plateau plateau = entry.getValue();

      roverList.add(
          MessageFormat.format(
              "{0} on {1} at position and heading {2}",
              rover.getRoverId(), plateau.getPlateauId(), rover.getLocation()));
    }

    String roverReport = roverList.toString();
    if (roverReport.isEmpty()) {
      roverReport = "No rovers to report";
    }
    return roverReport;
  }

  public String getPlateauList() {

    String plateauReport =
        plateauList.stream().map(Plateau::getPlateauId).collect(Collectors.joining("\n"));

    if (plateauReport.isEmpty()) {
      plateauReport = "No plateaus to report";
    }
    return plateauReport;
  }
}
