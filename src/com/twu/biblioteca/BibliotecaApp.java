package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        biblioteca.welcomeMessage();

        System.out.println("\nHere is a list of all library books");

        List<String> listOfBooks= new ArrayList<String>();
        listOfBooks.add("Building with Gradle");
        listOfBooks.add("The JHipster Mini-book");
        biblioteca.displayListOfBook(listOfBooks);

        System.out.println("\n");
        biblioteca.printColumnHeader();

        Book book1 = new Book("Building with Gradle", "Tim Berglund", "July 16, 2011");
        Book book2 = new Book("The JHipster Mini-book", "Richard Dallaway", "May 10, 2010");

        biblioteca.addBookInTheBookList(book1);
        biblioteca.addBookInTheBookList(book2);

        biblioteca.printBooksInDetails();

        System.out.println("\nWelcome to the Main Menu. Select your option");
        biblioteca.mainMenuOptions();


    }

}
