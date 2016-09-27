package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BibliotecaTest {

    private Biblioteca biblioteca;
    private ByteArrayOutputStream outSpy;
    private Book book1;
    private Book book2;

    @Before
    public void setUp() throws Exception {
        biblioteca = new Biblioteca();

        book1 = new Book("Building with Gradle", "Tim Berglund", "July 16, 2011");
        book2 = new Book("The JHipster Mini-book", "Richard Dallaway", "May 10, 2010");
        biblioteca.addBookInTheBookList(book1);
        biblioteca.addBookInTheBookList(book2);

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

    @Test
    public void addABookToTheBookList() throws Exception {

        Book newBook = new Book("Building with Gradle", "Tim Berglund", "July 16, 2011");
        List<Book> aBookList = new ArrayList<Book>();
        aBookList.add(book1);
        aBookList.add(book2);
        aBookList.add(newBook);
        biblioteca.addBookInTheBookList(newBook);
        assertEquals(aBookList,biblioteca.getListOfBooks());
    }

    @Test
    public void listOfBooksInDetailsAreDisplayed() throws Exception {

        biblioteca.printBooksInDetails();
        String expectOutput = new String(outSpy.toByteArray());

        assertTrue(expectOutput.contains("Building with Gradle"));
        assertTrue(expectOutput.contains("Tim Berglund"));
        assertTrue(expectOutput.contains("July 16, 2011"));
        assertTrue(expectOutput.contains("The JHipster Mini-book"));
        assertTrue(expectOutput.contains("Richard Dallaway"));
        assertTrue(expectOutput.contains("May 10, 2010"));

    }

    @Test
    public void mainMenuShouldBeDisplayed() throws Exception {
        biblioteca.mainMenuOptions();
        String header = "Main Menu, select your option by entering a number\n";
        String menuOption1 = "1. List Books";
        String expectOutput = new String(outSpy.toByteArray());
        assertTrue(expectOutput.contains(menuOption1));
        assertTrue(expectOutput.contains(header));
    }

    @Test
    public void invalidMenuOptionShouldReturnFalse() throws Exception {
        int menuOptionTwo = 0;

        boolean isValid = biblioteca.checkMenuOptionIsValid(menuOptionTwo);
        assertThat(isValid,is(false));
    }

    @Test
    public void invalidMenuOptionShouldDisplayErrorMessage() throws Exception {

        int menuOptionZero = 0;
        boolean isValid = biblioteca.checkMenuOptionIsValid(menuOptionZero);
        String errorMessage = "Select a valid option!";
        assertEquals(errorMessage,outSpy.toString());

    }

    @Test
    public void menuOptionOneShouldBeTrue() throws Exception {
        int menuOptionOne = 1;
        boolean isValid = biblioteca.checkMenuOptionIsValid(menuOptionOne);
        assertThat(isValid,is(true));
    }

    @Test
    public void whenTheMenuOptionIsQuitTheProgramShouldEnd() throws Exception {
        int quitOption = 99;
        boolean isValid = biblioteca.checkMenuOptionIsValid(quitOption);
        assertEquals("Goodbye", outSpy.toString());
        assertThat(isValid,is(true));
    }

    @Test
    public void checkOutABookShouldBeRemovedFromListOfBooks() throws Exception {
        List<Book> aBookList = new ArrayList<Book>();
        aBookList.add(book1);
        aBookList.add(book2);

        biblioteca.checkOutABook("Building with Gradle");
        aBookList.remove(book1);
        biblioteca.printBooksInDetails();
        assertEquals(aBookList, biblioteca.getListOfBooks());

        String expectOutput = new String(outSpy.toByteArray());
        assertTrue(expectOutput.contains("The JHipster Mini-book"));
        assertTrue(expectOutput.contains("Richard Dallaway"));
        assertTrue(expectOutput.contains("May 10, 2010"));
    }

    @Test
    public void successfulCheckoutMessageShouldBeDisplayed() throws Exception {
        biblioteca.checkOutABook("Building with Gradle");
        assertEquals("Thank you! Enjoy the book",outSpy.toString());

    }

    @Test
    public void checkoutBookShouldBeAddedCheckOutBookList() throws Exception {
        String bookName = book1.getTitle();
        biblioteca.checkOutABook(bookName);
        assertTrue(biblioteca.getCheckOutBookList().contains(book1));

    }

    @Test
    public void givenNonExistingBookNameThenReturnFalse() throws Exception {
        String bookName = "The Java Book";
        int index = biblioteca.checkTheBookExist(bookName, biblioteca.getListOfBooks());
        assertFalse(0 <= index);
    }

    @Test
    public void givenNonExistingBookNameThenDisplayErrorMessage() throws Exception {
        String bookName = "Language Book";
        biblioteca.checkOutABook(bookName);

        assertEquals("That book is not available.",outSpy.toString());
    }

    @Test
    public void givenTheReturnBookNameShouldExistInTheCheckOutList() throws Exception {
        Book returnBook = new Book("The Adventures of Pinocchio", "Richard Dallaway", "May 10, 2010");
        biblioteca.addBookToCheckOutListOfBooks(returnBook);
        int index = biblioteca.checkTheBookExist(returnBook.getTitle(), biblioteca.getCheckOutBookList());
        assertTrue(0 <= index);
    }



    @Test
    public void givenAReturnBookNameThenItShouldBeDisplayedOnListOFBooks() throws Exception {
        Book returnBook = new Book("The Adventures of Pinocchio", "Richard Dallaway", "May 10, 2010");
        biblioteca.addBookToCheckOutListOfBooks(returnBook);

        biblioteca.returnBook(returnBook.getTitle());
        biblioteca.printBooksInDetails();

        String expectOutput = new String(outSpy.toByteArray());
        assertTrue(expectOutput.contains("The Adventures of Pinocchio"));
        assertTrue(expectOutput.contains("Richard Dallaway"));
        assertTrue(expectOutput.contains("May 10, 2010"));

    }

    @Test
    public void givenNonExistingBookNameToReturnBookShouldDisplayAErrorMessage() throws Exception {
        String returnBook = "The moon and the stars";
        biblioteca.returnBook(returnBook);

        assertEquals("That is not a valid book to return.",outSpy.toString());

    }

    @Test
    public void givenAReturnBookNameThenSuccesfulMessageShouldBeDisplayed() throws Exception {
        Book returnBook = new Book("The Adventures of Pinocchio", "Richard Dallaway", "May 10, 2010");
        biblioteca.addBookToCheckOutListOfBooks(returnBook);

        biblioteca.returnBook(returnBook.getTitle());
        assertEquals("Thank you for returning the book.",outSpy.toString());
    }

    @Test
    public void givenNumberOneThenListOfAllBooksShouldBeDisplayed() throws Exception {
        int number = 1;
        biblioteca.checkMenuOptionIsValid(number);
        String expectOutput = new String(outSpy.toByteArray());

        assertTrue(expectOutput.contains("Building with Gradle"));
        assertTrue(expectOutput.contains("Tim Berglund"));
        assertTrue(expectOutput.contains("July 16, 2011"));
        assertTrue(expectOutput.contains("The JHipster Mini-book"));
        assertTrue(expectOutput.contains("Richard Dallaway"));
        assertTrue(expectOutput.contains("May 10, 2010"));

    }


    @Test
    public void checkoutOptionInTheMainMenuShoulDisplayInstruction() throws Exception {
        int menuOptionTwo = 2;
        biblioteca.checkMenuOptionIsValid(menuOptionTwo);
        assertEquals("Enter the the name of the Book you want to checkout",outSpy.toString());
    }

    @Test
    public void returnOptionInTheMainMenuShoulDisplayInstruction() throws Exception {
        int menuOptionThree = 3;
        biblioteca.checkMenuOptionIsValid(menuOptionThree);
        assertEquals("Enter the the name of the Book you want to return",outSpy.toString());
    }
}
