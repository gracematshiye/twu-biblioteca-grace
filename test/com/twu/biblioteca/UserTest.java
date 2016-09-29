package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by gracem on 2016/09/29.
 */
public class UserTest {

    @Test
    public void testValidUser() throws Exception {
        User user = new User("234-3456", "password");
        assertNotNull(user);

    }

    @Test(expected = InvalidLibraryNumber.class)
    public void testInvalidUser() throws Exception {
        User user = new User("234-456", "password");
    }

    @Test
    public void testValidUserWithFetails() throws Exception {
        User user = new User("234-3456", "password", "Tess", "tess@gmail.com", "124568900");
    }

    @Test(expected = InvalidLibraryNumber.class)
    public void testInvalidUserWithDetails() throws Exception {
        User user = new User("234-4x6", "password", "Maria", "maria@gmail.com", "4567892589");

    }
}
