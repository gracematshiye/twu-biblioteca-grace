package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        biblioteca.welcomeMessage();

        List<String> listOfBooks= new ArrayList<String>();
        listOfBooks.add("Building and Testing with Gradle");
        listOfBooks.add("The JHipster Mini-book");

        biblioteca.displayListOfBook(listOfBooks);

    }

}
