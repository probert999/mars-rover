# Mars Rover kata
Mars Rover kata using UML, TDD, Java

### Overview
Program to move rovers the surface of Mars, which is represented by a number of plateaus, divided into a grid.
<p>Rovers can land on a plateau at specified grid coordinates with a compass heading set and then receive commands 
to move to the next grid space or spin left or right to face another direction.
Multiple rovers can be placed on the same plateau. 

### Design
[UML Class Diagram](https://github.com/probert999/mars-rover/blob/main/uml/MarsRoverClassDiagram.png)

#### Capcom
<li>Takes input and processes commands 
<li>Creates and manages plateaus
<li>Creates and manages rovers
<li>Produces status reports

#### Plateau
<li>Handles coordinate validation requests from rovers
<li>Receives and stores position data for rovers on the plateau to ensure no crashes!
<li>Handles map (if enabled)

#### Rover
<li>Receives and executes move and spin commands

<br>The interactions between the components can be seen the in 
[Sequence Diagram](https://github.com/probert999/mars-rover/blob/main/uml/MarsRoverSequenceDiagram.png)
showing the set-up and processing of a move instruction


### Running the application
The main method handles console input or a file contain instructions be passed as a parameter.

<br>Example instruction files are available in the testdata folder.

### Commands to explore Mars
All rovers need a plateau to land on, currently only quadrilateral shaped plateaus are supported.

#### Create a plateau 
Enter the maximum x and y coordinates of the plateau, e.g.

><i>10 10</i>    

Creates a 10x10 square with 0,0 coordinate at the bottom left-hand corner
<BR>On successful creation a plateau id will be returned. Multiple plateaus can be created.
<BR>Maximum size for a Quad Plateau is 9999999,9999999

#### Create a rover
To create a rover on the current plateau specify its x and y coordinates and heading, e.g.

><i>3 5 N</i>

Creates a rover on the current plateau at coordinates 3,5 with a heading of North.
Plateaus can support multiple rovers.

#### Move and Spin commands 
Rover move and spin commends are available to enable exploring:
><i>L</i> - spin left, e.g. if current heading is North, new heading is East
><br><i>R</i> - spin right
><br><i>M</i> - move one grid position in the direction of the current heading

A single command can contain multiple move and spin instructions, e.g. <i>LLMMMRMMM</i>

#### Other commands
<blockquote>
<table>
<tr><td><i>SWITCH PLATEAU &lt;plateau-id&gt;</i></td><td>switch to the plateau specified</td></tr>
<tr><td><i>SWITCH ROVER &lt;rover-id&gt;</i></td><td>switch to the rover specified and plateau rover is on</td></tr>
<tr><td><i>LIST PLATEAUS</i></td><td>display a list of all plateaus</td></tr>
<tr><td><i>LIST ROVERS</i></td><td>display a list of all rovers and their location</td></tr>
<tr><td><i>SHOW MAP</i></td><td>show map for the current plateau (if map feature available)</td></tr>
<tr><td><i>HIDE MAP</i></td><td>hide map for the current plateau</td></tr>
<tr><td><i>FINISH</i></td><td>exits the console application</td></tr>
<tr><td><i>HELP</i></td><td>displays the help text</td></tr>
</table>
</blockquote>

On exit a report of all rover positions is produced.

### Move logic
Rovers cannot land on or move to the space as another Rover on the same plateau.  

On receipt of a move command the rover calculates its new coordinates and validates the move with the plateau.

The plateau checks the coordinates are within bounds and the current position of any other rovers. Two rovers cannot share the same coordinates


### Future enhancements
<li>Different shaped plateaus
<li>Flying rover
<li>Obstacles on the plateau
<li>Disable rover
<li>Additional Capcoms