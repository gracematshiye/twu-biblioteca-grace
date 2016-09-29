package com.twu.biblioteca;

public class Book {

    private String author;
    private int yearPublished;

    public Book(String author, int yearPublished) {
        this.author = author;
        this.yearPublished = yearPublished;

    }


    public String getAuthor() {
        return author;
    }


    public int getYearPublished() {
        return yearPublished;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (yearPublished != book.yearPublished) return false;
        return author != null ? author.equals(book.author) : book.author == null;

    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + yearPublished;
        return result;
    }





}