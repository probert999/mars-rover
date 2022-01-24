package com.probert999.marsrover.service;

import com.probert999.marsrover.model.*;

import java.text.MessageFormat;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class NASACapcomService extends NASACapcom {

    public String getRoverList() {
        StringJoiner roverList = new StringJoiner("\n");

        for(Map.Entry<Rover, Plateau> entry : roverMap.entrySet()){
            Rover rover = entry.getKey();
            Plateau plateau = entry.getValue();

            roverList.add(MessageFormat.format(
                            "{0} on {1} at position and heading {2}",
                            rover.getRoverId(), plateau.getPlateauId(), rover.getLocation()));
        }
        return roverList.toString();
    }


    public String getPlateauList() {
        return plateauList.stream().map(Plateau::getPlateauId).collect(Collectors.joining("\n"));
    }

}
