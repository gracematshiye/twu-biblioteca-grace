package com.twu.biblioteca;

import java.util.*;

/**
 * Created by gracem on 2016/09/26.
 */
public class Biblioteca {

    private List<Book> listOfBooks;

    public Biblioteca() {
        listOfBooks = new ArrayList<Book>();
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

        if(checkTheBookExist(bookName) != -1){
            listOfBooks.remove(checkTheBookExist(bookName));
            System.out.print("Thank you! Enjoy the book");
        } else {
            System.out.print("That book is not available.");
        }


    }

    public List<Book> getListOfBooks() {
        return listOfBooks;
    }

    public int checkTheBookExist(String bookName) {

        for (int i = 0; i < getListOfBooks().size(); i++) {
            if(getListOfBooks().get(i).getTitle().equalsIgnoreCase(bookName)){
                return i;
            }
        }
        return (-1);
    }
}
