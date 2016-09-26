package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gracem on 2016/09/26.
 */
public class Biblioteca {

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

    public void printBooksInDetails(List<Book> aBookList) {
        int counter = 0;
        for (Book book: aBookList) {
            ++counter;
            System.out.printf(columnFormat(),counter + ". " + book.getTitle(), book.getAuthor(), book.getYearPublished());
        }
    }

    public void mainMenuOptions() {
        HashMap<Integer,String> menuOption = new HashMap<Integer, String>();
        menuOption.put(1, "List Books");
        System.out.print("1. " + menuOption.get(1));
    }
}
