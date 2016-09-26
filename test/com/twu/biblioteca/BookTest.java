package com.twu.biblioteca;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookTest {
    private Book book1;
    private Book book2;

    @Before
    public void setUp() throws Exception {
        book1 = new Book("Java OOP", "J.R Berglund", "July 16, 2011");
        book2 = new Book("Gradle for Beginners", "D.R Dallaway", "May 10, 2010");
    }

    @Test
    public void testBookEqualsItself() throws Exception {
        Assert.assertTrue(book1 == book1);
    }

    @Test
    public void testTwoBooksWithDifferentDescriptionShouldNotBeEqual() throws Exception {
        Assert.assertNotEquals(book1, book2);
    }

    @Test
    public void testBookIsNotNull() throws Exception {
        Assert.assertNotEquals(book1, null);
    }

    @Test
    public void testBookNotEqualToAnObjectOfDifferentType() throws Exception {
        Object object = new Object();
        Assert.assertNotEquals(book1, object);
    }

    @Test
    public void testEqualBooksHaveSameHashCode() throws Exception {
        book2 = book1;
        Assert.assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    public void testUnEqualBooksHaveDifferentHashCode() throws Exception {
        Assert.assertNotEquals(book1.hashCode(), book2.hashCode());
    }
}