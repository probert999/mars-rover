# Mars Rover kata
Mars Rover kata using UML, TDD, Java

### Design
[UML Class Diagram](https://github.com/probert999/mars-rover/blob/main/uml/MarsRoverClassDiagram.png)


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

The plateau check the coordinates are within bounds and the position of any other rovers. Two rovers cannot share the same coordinates

[Sequence Diagram](https://github.com/probert999/mars-rover/blob/main/uml/MarsRoverSequenceDiagram.png)
 
### Running the application
The application can run using manual input from the command line or a file containing instructions can be passed as a parameter.

Example instruction files are available in the testdata folder.


### Future enhancements
<li>Different shaped plateaus
<li>Flying Rover
<li>Additional Capcoms