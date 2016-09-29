package com.twu.biblioteca;

/**
 * Created by gracem on 2016/09/29.
 */
public class BookUser {
    private Book book;
    private User user;

    public BookUser(Book book, User user){
        this.book = book;
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }
}
