package com.twu.biblioteca;

import java.util.*;


public class Biblioteca {

    private List<Book> listOfBooks;
    private List<Book> checkOutBookList;
    private Map<Integer,String> mainMenuOptions;

    public Biblioteca() {
        listOfBooks = new ArrayList<Book>();
        checkOutBookList = new ArrayList<Book>();
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

    public String checkOutBook(Book book) {

        for (Book existingBook : listOfBooks){
            if (existingBook.equals(book)){
                checkOutBookList.add(existingBook);
                listOfBooks.remove(existingBook);
                return "Thank you! Enjoy the book";
            }
        }
        return "That book is not available";

    }

    public String returnBook(Book book) {
        for (Book checkedOutBook : checkOutBookList){
            if (checkedOutBook.equals(book)){
                listOfBooks.add(checkedOutBook);
                checkOutBookList.remove(book);
                return "Thank you for returning the book";
            }
        }
        return "That is not a valid book to return";
    }





}
