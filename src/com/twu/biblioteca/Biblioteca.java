package com.twu.biblioteca;

import java.util.*;


public class Biblioteca {

    private List<Book> listOfBooks;
    private List<BookUser> checkOutBookList;
    private Map<Integer,String> mainMenuOptions;
    private List<Movie> movieList;
    private List<User> listOfUser;
    private int users;

    public Biblioteca() {
        listOfBooks = new ArrayList<Book>();
        checkOutBookList = new ArrayList<BookUser>();
        listOfUser = new ArrayList<User>();
        mainMenuOptions = new HashMap<Integer, String>();
        this.mainMenuOptions.put(1, "List books");
        this.mainMenuOptions.put(99, "Quit");
    }

    public String startApplication() {
        return "Hello there, Welcome to our Biblioteca\n";
    }

    public void setListOfBooks(List<Book> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

    public List<Book> getListOfBooks() {
        return this.listOfBooks;
    }

    public Map<Integer, String> getMainMenuOptions() {
        return mainMenuOptions;
    }

    public String selectMenu(int menuKey) {
        String menuDescription = mainMenuOptions.get(menuKey);
        return menuDescription != null ? menuDescription : "Select a valid option!";
    }

    public String checkOutBook(Book book, User user) {
        if(user.isLoggedIn()) {
            for (Book existingBook : listOfBooks) {
                if (existingBook.equals(book)) {
                    checkOutBookList.add(new BookUser(existingBook, user));
                    listOfBooks.remove(existingBook);
                    return "Thank you! Enjoy the book";
                }
            }
            return "That book is not available";
        }
        return "Invalid User";
    }

    public String returnBook(Book book, User user) {

        if(user.isLoggedIn()) {
            for (BookUser checkedOutBookUser : checkOutBookList) {
                if (checkedOutBookUser.getBook().equals(book)) {
                    listOfBooks.add(checkedOutBookUser.getBook());
                    checkOutBookList.remove(checkedOutBookUser);
                    return "Thank you for returning the book";
                }
            }
            return "That is not a valid book to return";
        }
        return "Invalid User";
    }


    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void checkOutMovie(Movie movie) {

        for(Movie existingMovie: movieList){
            movieList.remove(movie);
        }
    }

    public void addUser(User user) {
        listOfUser.add(user);
    }

    public User login(User user) {
        User existingUser = checkUserExist(user);
        if (existingUser != null){
            existingUser.setLoggedIn(true);
            return existingUser;
        }
        return existingUser;
    }

    public String logout(User user){
        User existingUser = checkUserExist(user);
        if (existingUser!= null && user.isLoggedIn()){
            user.setLoggedIn(false);
            return "Logout successfully";
        }
        return "Invalid user";
    }

    private User checkUserExist(User user) {
        for (User existingUser: listOfUser) {
            if (existingUser.equals(user))
                return existingUser;
        }
        return null;
    }

    public List<User> getListOfUser() {
        return listOfUser;
    }

    public List<BookUser> getCheckOutBookList() {
        return checkOutBookList;
    }

    public User whoHasBookCheckedOut(Book book) {
        for (BookUser bookUser: checkOutBookList) {
            if(bookUser.getBook().equals(book)){
                return bookUser.getUser();
            }

        }
        return null;
    }
}
