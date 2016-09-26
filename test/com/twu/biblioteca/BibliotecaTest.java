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
import static org.junit.Assert.assertTrue;

public class BibliotecaTest {

    private Biblioteca biblioteca;
    private ByteArrayOutputStream outSpy;

    @Before
    public void setUp() throws Exception {
        biblioteca = new Biblioteca();
        outSpy = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outSpy));
    }

    @Test
    public void welcomeMessageIsDisplayed() throws Exception {
        biblioteca.welcomeMessage();
        assertEquals("Hello there, Welcome to our Biblioteca\n", outSpy.toString());

    }

    @Test
    public void listOfAllLibraryBooksIsDisplayed() throws Exception {
        List<String> listOfBooks= new ArrayList<String>();
        listOfBooks.add("Building and Testing with Gradle");
        listOfBooks.add("The JHipster Mini-book");

        biblioteca.displayListOfBook(listOfBooks);
        String output = "1. Building and Testing with Gradle\n" +
                "2. The JHipster Mini-book\n" ;
        assertEquals(output,outSpy.toString());
    }

    @Test
    public void columnFormatIsCorrect() throws Exception {
        String tableFormat = "%-35s %-20s %-20s\n";
        assertEquals(tableFormat, biblioteca.columnFormat());
    }

    @Test
    public void columnHeaderIsDisplayed() throws Exception {
        biblioteca.printColumnHeader();
        String expectOutput = new String(outSpy.toByteArray());
        assertTrue(expectOutput.contains("TITLE"));
        assertTrue(expectOutput.contains("AUTHOR"));
        assertTrue(expectOutput.contains("YEAR PUBLISHED"));

    }


}
