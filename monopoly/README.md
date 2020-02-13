In this experiment i implemented  a non graphical Java version of the classic Monopoly game. 
The game consists of a sequential set of 40 squares containing 28 properties, 6 action squares (3 ”chance”, 3 ”community chest”), 
2 tax squares, ”go”, ”jail”, ”free parking”, and ”go to jail”. 
Every time a player lands on a square, she takes a diﬀerent action if this square is one of the properties.
For the other squares, the player takes a diﬀerent action depend on the type of the square.
For example, if she lands on a ”tax square”, she must pay a certain amount of money to the banker, 
but if she lands on an ”action square” she must draw a card from the appropriate pile and she must follow the instructions on the card.
In this game, properties are classiﬁed as Land, Company and Rail Roads. 
Users are classiﬁed as Player and Banker and they have name and money attributes. 
My task is designed the game with the given rules by using inheritence and polymorphism. 
I use these concepts while designing the program.
I use two JSON formatted input ﬁles: 
a. Properties ﬁle
b. Cards ﬁle
JSON is a format for storing data in the ﬁles for users. 
I use JSON.simple library, which is a simple Java library for reading and writing JSON formatted ﬁles. 
