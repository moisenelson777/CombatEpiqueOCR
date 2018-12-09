package com.nelson.tests;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadNumberExceptionTest {

/* Warning here when passing the tests might mean askForNumber isn't surrounded by a try catch. Testing this method
 alone works.*/
    @Test
    public void Given_BadNumber_When_AskForNumber_Then_ThrowException() {

        System.setIn(new ByteArrayInputStream("0\n".getBytes()));
        assertThrows(BadNumberException.class, () -> Player.askForNumber(1,3, "question sur le choix " +
                "de classe du joueur"));
    }

}
