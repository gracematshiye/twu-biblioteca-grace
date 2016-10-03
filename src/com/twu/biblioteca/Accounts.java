package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gracem on 2016/10/03.
 */

public class Accounts {
    private List<User> users;

    public Accounts()
    {
        users = new ArrayList<User>();
    }

    public User login(User user)
    {
        for (User existingUser: users)
        {
            if (user.getLibraryNumber().equals(existingUser.getLibraryNumber()) && user.getPassword().equals(existingUser.getPassword()))
            {
                existingUser.setLoggedIn(true);
                return existingUser;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
    }
}