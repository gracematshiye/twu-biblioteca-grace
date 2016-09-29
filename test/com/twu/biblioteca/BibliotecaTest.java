package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        List<Book> listOfBooks= new ArrayList<Book>();
        listOfBooks.add(new Book("TR  Mark", 1992));
        listOfBooks.add(new Book("JF Malfa", 2009));
        biblioteca.setListOfBooks(listOfBooks);

    }

    @Test
    public void welcomeMessageIsDisplayed() throws Exception {
        String message = biblioteca.startApplication();
        assertEquals("Hello there, Welcome to our Biblioteca\n", message);

    }

    @Test
    public void listOfAllLibraryBooksIsDisplayed() throws Exception {

        List<Book> result = biblioteca.getListOfBooks();
        assertEquals(2, result.size());
    }

    @Test
    public void testBookDetails() throws Exception {
        List<Book> listOfBooks= new ArrayList<Book>();
        listOfBooks.add(new Book("Grace", 2011));
        biblioteca.setListOfBooks(listOfBooks);
        assertEquals(1, biblioteca.getListOfBooks().size());
        assertEquals("Grace", biblioteca.getListOfBooks().get(0).getAuthor());
        assertEquals(2011, biblioteca.getListOfBooks().get(0).getYearPublished());
    }

    @Test
    public void testMainMenuOption() throws Exception {
        Map<Integer, String> mainMenu = biblioteca.getMainMenuOptions();
        assertTrue(mainMenu.keySet().contains(1));
        assertEquals("List books", mainMenu.get(1));
    }

    @Test
    public void testInvalidMenuOption() throws Exception {
        String menuResults = biblioteca.selectMenu(2);
        assertEquals("Select a valid option!", menuResults);
    }

    @Test
    public void testQuitMenuOption() throws Exception {
        String menuResults = biblioteca.selectMenu(99);
        assertEquals("Quit", menuResults);
    }

    @Test
    public void testCheckOutBook() throws Exception {
        assertEquals(2, biblioteca.getListOfBooks().size());
        biblioteca.checkOutBook(new Book("TR  Mark", 1992));
        assertEquals(1, biblioteca.getListOfBooks().size());
    }

    @Test
    public void testSuccessfulCheckout() throws Exception {
        String checkoutMessage = biblioteca.checkOutBook(new Book("TR  Mark", 1992));
        assertEquals("Thank you! Enjoy the book", checkoutMessage);
    }

    @Test
    public void testUnsuccessfulCheckout() throws Exception {
        String checkoutMessage = biblioteca.checkOutBook(new Book("TR  John", 1992));
        assertEquals("That book is not available", checkoutMessage);

    }

    @Test
    public void testReturnBook() throws Exception {
        assertEquals(2, biblioteca.getListOfBooks().size());
        Book book = new Book("TR  Mark", 1992);
        biblioteca.checkOutBook(book);
        assertEquals(1, biblioteca.getListOfBooks().size());

        biblioteca.returnBook(book);
        assertEquals(2, biblioteca.getListOfBooks().size());
    }

    @Test
    public void testSuccessfulReturnBook() throws Exception {
        Book book = new Book("TR  Mark", 1992);
        biblioteca.checkOutBook(book);
        String returnBookMessage = biblioteca.returnBook(book);
        assertEquals("Thank you for returning the book", returnBookMessage);
    }

    @Test
    public void testUnsuccessfulReturnBook() throws Exception {
        String returnBookMessage = biblioteca.returnBook(new Book("TR  Mark", 1992));
        assertEquals("That is not a valid book to return", returnBookMessage);
    }


}
