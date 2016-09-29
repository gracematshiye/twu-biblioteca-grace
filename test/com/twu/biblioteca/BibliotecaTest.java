package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BibliotecaTest {

    private Biblioteca biblioteca;
    List<Movie> moviesList;
    User user, user2, user3;

    @Before
    public void setUp() throws Exception {
        biblioteca = new Biblioteca();

        user = new User("123-1234", "pass");
        biblioteca.addUser(user);
        biblioteca.login(user);
        user2 = new User("888-1234", "pass123");
        biblioteca.addUser(user2);
        user3 = new User("147-1234", "pass123", "Sipho", "sipho@gmail.com", "2581479632");
        biblioteca.addUser(user3);

        List<Book> listOfBooks= new ArrayList<Book>();
        listOfBooks.add(new Book("TR  Mark", 1992));
        listOfBooks.add(new Book("JF Malfa", 2009));
        biblioteca.setListOfBooks(listOfBooks);

        moviesList= new ArrayList<Movie>();
        Movie movie = new Movie("Speed", 2010, "J. Rule", "6.7");
        moviesList.add(movie);
        movie= new Movie("Speed Bus", 2014, "J. Pule", "8.7");
        moviesList.add(movie);
        biblioteca.setMovieList(moviesList);

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
        assertEquals(0, biblioteca.getCheckOutBookList().size());
        biblioteca.checkOutBook(new Book("TR  Mark", 1992), user);
        assertEquals(1, biblioteca.getListOfBooks().size());
        assertEquals(1, biblioteca.getCheckOutBookList().size());
    }

    @Test
    public void testCheckOutListDetails() throws Exception {
        Book book = new Book("TR  Mark", 1992);
        biblioteca.checkOutBook(book, user);
        assertEquals(1, biblioteca.getCheckOutBookList().size());
        BookUser bookUser = biblioteca.getCheckOutBookList().get(0);
        assertEquals(user, bookUser.getUser());
        assertEquals(book, bookUser.getBook());
    }

    @Test
    public void testCheckOutUserForBook() throws Exception {
        Book book = new Book("TR  Mark", 1992);
        biblioteca.checkOutBook(book, user);
        User userWithBook = biblioteca.whoHasBookCheckedOut(book);
        assertEquals(user, userWithBook);
    }

    @Test
    public void testCheckOutBookWithInvalidUser() throws Exception {
        User invalidUser = new User("876-8977", "pass");
        biblioteca.login(invalidUser);
        String checkresults = biblioteca.checkOutBook(new Book("TR  Mark", 1992), invalidUser);
        assertEquals("Invalid User", checkresults);
    }


    @Test
    public void testSuccessfulCheckout() throws Exception {
        String checkoutMessage = biblioteca.checkOutBook(new Book("TR  Mark", 1992), user);
        assertEquals("Thank you! Enjoy the book", checkoutMessage);
    }

    @Test
    public void testUnsuccessfulCheckout() throws Exception {
        String checkoutMessage = biblioteca.checkOutBook(new Book("TR  John", 1992), user);
        assertEquals("That book is not available", checkoutMessage);

    }

    @Test
    public void testReturnBook() throws Exception {
        assertEquals(2, biblioteca.getListOfBooks().size());
        Book book = new Book("TR  Mark", 1992);
        biblioteca.checkOutBook(book, user);
        assertEquals(1, biblioteca.getListOfBooks().size());

        biblioteca.returnBook(book, user);
        assertEquals(2, biblioteca.getListOfBooks().size());
    }

    @Test
    public void testUserNotLoggedInToReturnBook() throws Exception {
        assertFalse(user2.isLoggedIn());
        biblioteca.login(user2);
        assertTrue(user2.isLoggedIn());
        Book book = new Book("TR  Mark", 1992);
        biblioteca.checkOutBook(book, user2);
        biblioteca.logout(user2);
        assertFalse(user2.isLoggedIn());
        String returnBookResults = biblioteca.returnBook(book, user2);
        assertEquals("Invalid User", returnBookResults);

    }

    @Test
    public void testSuccessfulReturnBook() throws Exception {
        Book book = new Book("TR  Mark", 1992);
        biblioteca.checkOutBook(book, user);
        String returnBookMessage = biblioteca.returnBook(book, user);
        assertEquals("Thank you for returning the book", returnBookMessage);
    }

    @Test
    public void testUnsuccessfulReturnBook() throws Exception {
        String returnBookMessage = biblioteca.returnBook(new Book("TR  Mark", 1992), user);
        assertEquals("That is not a valid book to return", returnBookMessage);
    }


    @Test
    public void testListOfMovies() throws Exception {

        assertEquals(moviesList, biblioteca.getMovieList());
        Movie movie = biblioteca.getMovieList().get(0);
        assertEquals("Speed", movie.getName());
        assertEquals(2010, movie.getYear());
        assertEquals("J. Rule", movie.getDirector());
        assertEquals("6.7", movie.getRate());


    }

    @Test
    public void testCheckOutMovie() throws Exception {
        assertEquals(2, biblioteca.getMovieList().size());
        biblioteca.checkOutMovie(new Movie("Speed", 2010, "J. Rule", "6.7"));
        assertEquals(1, biblioteca.getMovieList().size());
    }

    @Test
    public void testSuccessfulLogin() throws Exception {
        User userToLogIn = new User("888-1234", "pass123");
        assertFalse(userToLogIn.isLoggedIn());
        User loggedInUser = biblioteca.login(userToLogIn);
        assertNotNull(loggedInUser);
        assertTrue(loggedInUser.isLoggedIn());
    }

    @Test
    public void testInvaildLogin() throws Exception {
        assertEquals(3, biblioteca.getListOfUser().size());
        User invalidUser = new User("999-9800", "pass");
        User invalidUserResults = biblioteca.login(invalidUser);
        assertNull(invalidUserResults);

    }

    @Test
    public void testGetUserInformation() throws Exception {
        User myUser = new User("147-1234", "pass123");
        assertFalse(myUser.isLoggedIn());
        assertNull(myUser.getName());
        assertNull(myUser.getEmail());
        assertNull(myUser.getPhoneNumber());
    }

    @Test
    public void testGetUserInformationValid() throws Exception {
        User myUser = new User("147-1234", "pass123");
        User loggedInUser = biblioteca.login(myUser);
        assertTrue(loggedInUser.isLoggedIn());
        assertEquals("Sipho", loggedInUser.getName());
        assertEquals("sipho@gmail.com", loggedInUser.getEmail());
        assertEquals("2581479632", loggedInUser.getPhoneNumber());
    }
}
