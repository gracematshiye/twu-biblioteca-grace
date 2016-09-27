package com.twu.biblioteca;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @Test
    public void givenNumberOneThenEndTheProgram() throws Exception {
        int number = 1;
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.checkMenuOptionIsValid(number);


    }
}
