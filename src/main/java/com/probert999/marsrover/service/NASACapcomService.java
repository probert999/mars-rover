package com.probert999.marsrover.service;

import com.probert999.marsrover.model.NASACapcom;
import com.probert999.marsrover.model.Plateau;
import com.probert999.marsrover.model.Rover;

import java.text.MessageFormat;
import java.util.Map;
import java.util.StringJoiner;

public class NASACapcomService extends NASACapcom {

  public String getRoverList() {
    StringJoiner listOfRovers = new StringJoiner("\n");

    for (Map.Entry<String, Rover> entry : roverMap.entrySet()) {
      Rover rover = entry.getValue();
      Plateau plateau = rover.getPlateau();

      listOfRovers.add(
          MessageFormat.format(
              "{0} on {1} {2} at position and heading {3}",
              rover.getId(), plateau.getId(), plateau.getDimensions(), rover.getLocation()));
    }

    String roverReport = listOfRovers.toString();
    if (roverReport.isEmpty()) {
      roverReport = "No rovers to report";
    }
    return roverReport;
  }

  public String getPlateauList() {

    StringJoiner listOfPlateaus = new StringJoiner("\n");
    for (Map.Entry<String, Plateau> entry : plateauMap.entrySet()) {

       Plateau plateau = entry.getValue();
       listOfPlateaus.add(
               MessageFormat.format(
                       "{0} {1} Map visible: {2}",
                       plateau.getId(), plateau.getDimensions(), plateau.isMapVisible()));
     }

    String plateauReport = listOfPlateaus.toString();
    if (plateauReport.isEmpty()) {
      plateauReport = "No plateaus to report";
    }
    return plateauReport;
  }

  public void finish() {

    for (Map.Entry<String, Plateau> entry : plateauMap.entrySet()) {
        Plateau plateau = entry.getValue();
        plateau.hideMap(true);
    }
  }

}
