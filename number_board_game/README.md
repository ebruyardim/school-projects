The board consists of several rows and columns where numbers are distributed randomly among the cells of the board. 
In this assignment, I read these numbers from an input ﬁle (input.txt), so that the program will work on diﬀerent board sizes.
Every cell has four neighbors left, right, above and below. 
This game is a collect game where of each turn you should collect two or more numbers based on spatial relationship. 
That is, once I pick a cell, all neighboring (including the cell i picked) that containing cells the same number will disappear from the board. 
The selected cell includes at least one neighboring cell with the same value. 
If a cell disappears in a column, the rest of that column moves down to ﬁll the row blank cells.
Moreover, if a column disappears completely, all the cells which are at the right side of that blank column should move left to ﬁll the empty space.
