package com.twu.biblioteca;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
        assertNotNull(user);
    }

    @Test(expected = InvalidLibraryNumber.class)
    public void testInvalidUserWithDetails() throws Exception {
        User user = new User("234-4x6", "password", "Maria", "maria@gmail.com", "4567892589");

    }

    @Test
    public void testUserDetails() throws Exception {
        User user = new User("234-4676", "password", "Maria", "maria@gmail.com", "4567892589");
        String expectedResults = "Name:\t" + user.getName() + "\nEmail:\t" + user.getEmail() + "\nPhone number:\t" + user.getPhoneNumber();
        assertEquals(expectedResults, user.getUserDetails());
    }
    @Test
    public void testGetUserInformation() throws Exception {
        User myUser = new User("147-1234", "pass123");
        assertFalse(myUser.isLoggedIn());
        assertNull(myUser.getName());
        assertNull(myUser.getEmail());
        assertNull(myUser.getPhoneNumber());
    }
}
