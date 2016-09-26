package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class BibliotecaTest {
    
    @Test
    public void welcomeMessageIsDisplayed() throws Exception {
        Biblioteca biblioteca = new Biblioteca();
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outSpy));
        biblioteca.welcomeMessage();
        assertEquals("Hello there, Welcome to our Biblioteca\n", outSpy.toString());

    }

}
