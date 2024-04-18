package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserHandlingTest {

    @Test
    public void moveReturnsCharForValidInput() {
        final var input = "f";
        UserHandling checkChar = new UserHandling();
        final var result = checkChar.moveType(input);
        Assertions.assertEquals(result, "StringInputHandling :: moveType should return a char for valid input.");
    }

    @Test
    public void moveReturnsIntForValidMove() {
        final var input = "8";
        UserHandling checkInt = new UserHandling();
        final var result = checkInt.revealMove(input);
        Assertions.assertEquals(result, "IntegerInputHandling :: revealMove should return an int for valid input");

    }
}
