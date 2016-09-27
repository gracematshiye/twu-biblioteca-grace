package com.twu.biblioteca;

import java.util.*;


public class Biblioteca {

    private List<Book> listOfBooks;
    private List<Book> checkOutBookList;

    public Biblioteca() {
        listOfBooks = new ArrayList<Book>();
        checkOutBookList = new ArrayList<Book>();
    }

    public void welcomeMessage() {
        System.out.print( "Hello there, Welcome to our Biblioteca\n");
    }

    public void displayListOfBook(List<String> listOfBooks) {
        String message = "";
        for (int i = 0; i < listOfBooks.size(); i++) {
            message += i+1 + ". " + listOfBooks.get(i) + "\n";
        }
        System.out.print(message);
    }

    public String columnFormat() {
        return "%-35s %-20s %-20s\n";

    }

    public void printColumnHeader() {
        System.out.printf(columnFormat(),"TITLE", "AUTHOR", "YEAR PUBLISHED");
    }
    public void addBookInTheBookList(Book book){
        listOfBooks.add(book);
    }
    public void addBookToCheckOutListOfBooks(Book book){
        checkOutBookList.add(book);
    }

    public void printBooksInDetails() {
        int counter = 0;
        for (Book book: listOfBooks) {
            ++counter;
            System.out.printf(columnFormat(),counter + ". " + book.getTitle(), book.getAuthor(), book.getYearPublished());
        }
    }

    public void mainMenuOptions() {
        HashMap<Integer,String> menuOption = new HashMap<Integer, String>();
        System.out.println("1. List Books");
        System.out.println("2. Quit");

    }

    public boolean checkMenuOptionIsValid(int menuOption) {

        if(menuOption == 2){
            System.out.print("Goodbye");
//            System.exit(0);
            return true;

        }else if(menuOption != 1){
            System.out.print("Select a valid option!");
            return false;
        }

        return true;
    }
    public void checkOutABook(String bookName) {

        int index = checkTheBookExist(bookName, getListOfBooks());

        if(index != -1){
            addBookToCheckOutListOfBooks(listOfBooks.get(index));
            listOfBooks.remove(index);
            System.out.print("Thank you! Enjoy the book");
        } else {
            System.out.print("That book is not available.");
        }


    }

    public List<Book> getListOfBooks() {
        return listOfBooks;
    }

    public int checkTheBookExist(String bookName, List<Book> list) {

        for (int index = 0; index < list.size(); index++) {
            if(list.get(index).getTitle().equalsIgnoreCase(bookName)){
                return index;
            }
        }
        return (-1);
    }

    public List<Book> getCheckOutBookList() {
        return checkOutBookList;
    }

    public void returnBook(String title) {
        int index = checkTheBookExist(title, getCheckOutBookList());

        if(index != -1) {
            addBookInTheBookList(getCheckOutBookList().get(index));
        }


    }


}
