# Mars Rover kata

Mars Rover kata using UML, TDD, Java

Input from console or file

### Design

[UML Class Diagram](https://github.com/probert999/mars-rover/blob/main/uml/MarsRoverClassDiagram.png)


### Components
Plateau - is only concerned with its own dimensions

Rover 

Capcom


### Commands
All rovers need a plateau to land on, to create a plateau.

Currently only quadrilateral shaped plateaus are supported, simply enter the maximum x and y coordinates.

<li>10 10 (creates a 10x10 square, with 0,0 coordinate at the bottom left-hand corner)

Maximum size for a Quad Plateau is 9999999,9999999

To create a rover on the plateau specify its x and y coordinates and heading, e.g.

<li>3 5 N

Move and Spin commands can be sent to the Rover using the following
<li>L - spin left
<li>R - spin right
<li>M - move

A single command can contain multiple move and spin instructions, e.g. LLMMMRMMM

<li>SWITCH PLATEAU <plateau_id> - switches to the plateau specified
<li>SWITCH ROVER <rover_id> - switches to the rover specified
<li>LIST PLATEAUS - displays a list of all plateaus
<li>LIST ROVERS - displays a list of all rovers and their location
<li>FINISH - exists the console application

On exit a simple report of all rover positions is produced.

### Move logic
Rovers cannot land on or move to the space as another Rover

A Rover is provided with a link back to Capcom and on receipt of a move command calculates 
it's new coordinates then asks Capcom if it's ok to move to that space.

Capcom checks with the plateau that the coordinates are within its dimensions and 
then contacts other rovers on the same plateau to check their current position before 
confirming the move.

[Sequence Diagram](https://github.com/probert999/mars-rover/blob/main/uml/MarsRoverSequenceDiagram.png)
 


### Future enhancements
<li>Different shaped plateaus
<li>Flying Rover
<li>Additional Capcoms