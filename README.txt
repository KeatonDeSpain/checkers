# Checkers-KeatonDeSpain

* Author: Keaton DeSpain

## Overview

This was a personal project I created outside of class. I created by own checkers game using Java. This is just like any other game of checkers. You can move your pieces diagonally only and you can capture by jumping over pieces.

## Compiling and Using

Hit run inside the IDE.

## Discussion

When I first wanted to start this project, I had no experience with Java GUI's. Because of this setback I only created a board using a nested for-loop. Then after we learned about GUI's, in class I got started on the real work. I first started with many different classes but realized I could fit everything into three classes to make things simpler for me. After successfully creating the GUI and the pieces on the board I had to make sure pieces would move correctly and accurately. This was more difficult than anticipated, I first had to make sure that the user was clicking on a piece and not on the board. I made sure the user was clicking on a piece by using an array for the pieces that whenever the user clicked on the GUI it would return what color the piece is on the GUI. 

I then had to make sure that the user only moved diagonally and only one space at a time. I did this by creating a function to check the validation of the move. To do this I used multiple validation checks to see if the user was jumping only one space or if they were capturing a piece. Then if they were capturing a piece it would go to a different validation check then just a normal move.

After validating a capture, I realized I needed to make sure that if you queened a piece that you would be able to move backwards, so to do this I added a queen value to each of the pieces and set each of them to false until they got to the opposite end of the board. Once they reached the end of the board the pieces would go through a different validation move to allow them to move in the opposite direction.

## Testing

To test this project, I have run through many games to make sure the pieces move and are captured correctly, however this project is still a work in progress because I have not added double or triple captures.
