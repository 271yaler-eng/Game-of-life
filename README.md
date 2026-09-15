# Game-of-life
-Why is a 2D array appropriate for the Game of Life?
The 2D array is appropriate for the Game of Life because it makes it so that the squares can easily be 
displayed on the screen. If we were to use just a regular array, the Game of Life wouldn't be 2D,
or at least it would be much harder to display the game of life in a 2D fashion.  
-What do society.length and society[row].length represent? 
society.length represents the amount of rows that the 2D society array has starting from index 0. 
society[row].length represents the amount of columns that the 2D society array has starting from index 0, but 
more specifically, at a specific row. 
-Why could changing society directly while traversing it produce incorrect results?
Because certain conditions could be met as the program runs that would screw up how the program is supposed to
run, and would break a bunch of the game rules. Each change to society is predetermined by the arrangement of
the living cells, and changing society directly would activate conditions that could ruin the predetermined changes. 
-Why must neighborCount() check array boundaries?
neighborCount() must check array boundaries, because if the neighborCount() method checks a cell that is outside of the boundaries, that would cause an ArrayIndexOutOfBoundsException.
-Why do we need a second 2D array inside update()?
We need a second 2D array inside of update() because that helps the program fully update the whole society without changing society directly while traversing it. A second 2D array basically just helps lay out the predetermined pattern for the updated society. 
-How can the same GameOfLife object be displayed as both text and graphics?
The same GameOfLife object can be displayed as both text and graphics because they both use the same 2D array, just with different methods that implement each of them: 1. as a graphic, and 2. as text. 
-What happens to a glider when it reaches the edge of our board, and how is that different 
from wraparound?
When a glider reaches the edge of the board, each cell that moves outside of the boundaries gets ignored, and pretty much just gets deleted the next time that the society updates. This is different from a wraparound in the sense that instead of the living cell moving and coming around the other side of the society, the living cell dies by going out of bounds. 
