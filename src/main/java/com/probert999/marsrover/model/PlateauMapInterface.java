package com.probert999.marsrover.model;

import java.util.List;

interface PlateauMapInterface {

    void show(List<RoverDetails> rovers);

    void hide(boolean finish);

    boolean isMapVisible();

}
