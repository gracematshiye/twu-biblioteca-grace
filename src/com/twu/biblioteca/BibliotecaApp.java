package com.twu.biblioteca;

import javax.jws.soap.SOAPBinding;
import java.util.*;


public class BibliotecaApp {

    private static Biblioteca biblioteca = new Biblioteca();
    private static Scanner scan = new Scanner(System.in);
    private static User user, loggedInAs;
    private static Accounts userAccounts;

    public static void main(String[] args) {
        try {
            setUpApplicationData();
            setUpUserData();

            System.out.println(biblioteca.startApplication());

            boolean running = true;

            while(running)
            {
                loggingIn();
                if (loggedInAs != null)
                {
                    printMenuOption(biblioteca.getMainMenuOptions());
                    computeMainMenuOption();
                }
                else
                {
                    System.out.println("Sorry, user doesn't exist!");
                    running = false;
                }
            }





        }catch (InvalidLibraryNumber ex){

        }catch (Exception ex) {

        }


    }

    private static void setUpUserData() {


        try {
            User customer = new User("123-1234", "pa", "Lethuli", "lethuli@gmail.com", "0829073737");
            User librarian = new User("123-4321", "pa", "Marry", "marry@gmail.com", "0782323675");
            librarian.setLibrarian(true);

            userAccounts = new Accounts();
            userAccounts.addUser(customer);
            userAccounts.addUser(librarian);


        } catch (InvalidLibraryNumber invalidLibraryNumber) {
        }

    }


    private static void computeMainMenuOption() throws InvalidLibraryNumber {

        int input = scan.nextInt();

        while(input != 99){

            String selectMenu = biblioteca.selectMenu(input);
            System.out.println(selectMenu);

            switch (input){
                case 1:
                    printBookList(biblioteca.getListOfBooks());
                    break;
                case 2:
                    printMovieList(biblioteca.getMovieList());
                    break;
                case 3:
                    computeCheckOutBook(loggedInAs);
                    break;
                case 4:
                    computeCheckOutMovie();
                    break;
                case 5:
                    Book returnBook = computeReturnBook();
                    biblioteca.returnBook(returnBook,loggedInAs);
                    break;
                case 6:
                    computeDisplayUserInformation();

                    break;
                case 7:
                    if(loggedInAs.isLibrarian()){
                        computeBookUser();
                    } else{

                    }
                    break;

                default:
                    System.exit(1);
                    break;
            }

            printMenuOption(biblioteca.getMainMenuOptions());
            System.out.print("\nPlease select an option: ");
            input = scan.nextInt();
        }
    }

    private static void computeDisplayUserInformation() throws InvalidLibraryNumber {

        System.out.println("User's Details : ");
        System.out.println(loggedInAs.getUserDetails());
    }


    private static void loggingIn() throws InvalidLibraryNumber{

        System.out.println("Please log-in");
        System.out.println("Enter library number : ");
        String libraryNumber = scan.nextLine();
        System.out.println("Enter password: ");
        String password = scan.nextLine();
        loggedInAs = userAccounts.login(new User(libraryNumber,password));
    }

    private static void computeBookUser() {
        Map<Book, User> checkedOutBook = biblioteca.getCheckOutBookList();
        for(Map.Entry<Book, User> bookUser : checkedOutBook.entrySet()){
            System.out.println(bookUser.getValue().getName() + " checked out this book by : " + bookUser.getKey().getAuthor());
        }


    }

    private static void computeCheckOutBook(User checkoutBookAs) throws InvalidLibraryNumber{
        printBookList(biblioteca.getListOfBooks());
        System.out.print("\nSelect a book, by number :  ");
        int checkoutBook = getNumber();
        System.out.println(biblioteca.checkOutBook(biblioteca.getListOfBooks().get(checkoutBook - 1), checkoutBookAs));

    }

    private static void computeCheckOutMovie() throws InvalidLibraryNumber {
        if(user.isLoggedIn()) {
            printMovieList(biblioteca.getMovieList());
            System.out.print("\nSelect a movie, by number :  ");
            int movieNumber = getNumber();
            Movie checkoutMovie = biblioteca.getMovieList().get(movieNumber-1);
            biblioteca.checkOutMovie(checkoutMovie);
            System.out.println("You checked-out a movie: " + checkoutMovie.getName() + ", directed by " + checkoutMovie.getDirector());
        }else {
            loggingIn();
        }
    }

    private static Book computeReturnBook() throws InvalidLibraryNumber{
        if(user.isLoggedIn()) {
            Scanner scan = new Scanner(System.in);
            System.out.print("\nEnter book author name : ");
            String authorName = scan.nextLine();
            System.out.print("\nEnter book year published : ");
            int yearPublished = getNumber();
            return new Book(authorName,yearPublished);
        }else {
            loggingIn();
            return null;
         }
    }

    private static int getNumber() {
        boolean isValid = false;
        int number = 0;
        while (!isValid){
            try {
                Scanner input = new Scanner(System.in);
                number = input.nextInt();
                isValid = true;
            } catch (Exception ex){
                System.out.println("Please enter a number");
            }
        }
        return number;
    }

    private static void printMenuOption(Map<Integer, String> mainMenuOptions) {
        System.out.println("Main menu");
        for (Integer menuKey: mainMenuOptions.keySet()){
            System.out.print(menuKey);
            System.out.print(" \t: \t");
            System.out.println(mainMenuOptions.get(menuKey));
        }
    }
    private static void printBookList(List<Book> bookList){
        int count = 1;
        System.out.printf("%-20s %-20s\n","AUTHOR", "YEAR PUBLISHED");
        for (Book book: bookList){
            System.out.printf("%-20s %-20s\n", count + ". " + book.getAuthor(), book.getYearPublished());
            count++;
        }
    }

    private static void printMovieList(List<Movie> moviList){
        int count = 1;
        System.out.printf("%-30s %-20s %-20s %-20s\n","MOVIE NAME", "YEAR RELEASED", "DIRECTOR NAME", "MOVIE RATING");
        for (Movie movie: moviList){
            System.out.printf("%-30s %-20s %-20s %-20s\n", count + ". " + movie.getName(), "" + movie.getYear(), movie.getDirector(), movie.getRate());
            count++;
        }
    }


    private static void setUpApplicationData() throws InvalidMovieRating, InvalidLibraryNumber{

        List<Book> bookList = new ArrayList<Book>();

        Book book1 = new Book("J.R Berglund", 2011);
        Book book2 = new Book("D.R Dallaway", 2009);
        Book book3 = new Book("D.R Maja", 2010);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);

        biblioteca.setListOfBooks(bookList);

        List<Movie> moviesList= new ArrayList<Movie>();
        Movie movie1 = new Movie("Speed", 2010, "J. Rule", "6.7");
        Movie movie2= new Movie("Speed Bus", 2014, "J. Pule", "8.7");
        Movie movie3 = new Movie("Gone In 60 secs", 2006, "J. Phuti", "4.8");
        moviesList.add(movie1);
        moviesList.add(movie2);
        moviesList.add(movie3);

        biblioteca.setMovieList(moviesList);

    }


}
