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


    }

}
