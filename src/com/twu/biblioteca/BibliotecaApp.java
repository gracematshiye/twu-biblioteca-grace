package com.twu.biblioteca;

import java.util.*;

public class BibliotecaApp {

    public static void main(String[] args) {



        Scanner scan = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        System.out.println(biblioteca.startApplication());
        Map<Integer, String> mainMenuOptions = biblioteca.getMainMenuOptions();
        printMenuOption(mainMenuOptions);

        int input = scan.nextInt();

        while(input != 99){

            String selectMenu = biblioteca.selectMenu(input);
            System.out.println(selectMenu);
            input = scan.nextInt();
        }
    }

    private static void printMenuOption(Map<Integer, String> mainMenuOptions) {
        System.out.println("Main menu");
        Set<Integer> keys = mainMenuOptions.keySet();
        for (Integer menuKey: mainMenuOptions.keySet()){
            System.out.print(menuKey);
            System.out.print(" \t: \t");
            System.out.println(mainMenuOptions.get(menuKey));
        }
    }
}
