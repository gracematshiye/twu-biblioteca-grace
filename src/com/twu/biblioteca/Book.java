package com.twu.biblioteca;

public class Book {

    private String title;
    private String author;
    private String yearPublished;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return getTitle() + ", " + getAuthor() + ", " + getYearPublished();
    }

    public Book() {
    }

    public Book(String title, String author, String yearPublished) {

        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        return yearPublished != null ? yearPublished.equals(book.yearPublished) : book.yearPublished == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (yearPublished != null ? yearPublished.hashCode() : 0);
        return result;
    }
}