# Mars Rover Kata

Mars Rover tech task - UML, TDD, Java

Input from console or file

### Design

[UML Class Diagram](https://github.com/probert999/mars-rover/blob/main/uml/MarsRoverClassDiagram.png)


### Components
Plateau is only concerned with it's own dimensions
Rover 

### Commands
0 0 N

Maximum size for a Quad Plateau is 9999999,9999999

L
R
M

### Move logic
Rover cannot land on or move to the space as another Rover

Rover is provided with a link back to Capcom and on receipt of a move command calculates 
it's new coordinates then asks Capcom if it's ok to move to that space.

Capcom checks with the plateau that the coordinates are within the dimensions and 
then contacts other rovers on the same plateau to check their current position before 
confirming the move.

[Sequence Diagram](https://github.com/probert999/mars-rover/blob/main/uml/MarsRoverSequenceDiagram.png)
 


### Future enhancements
Different shaped plateaus
Flying Rover
Switch between Rovers & Plateaus
Additional Capcoms