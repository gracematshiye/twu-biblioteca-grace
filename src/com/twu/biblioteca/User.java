package com.twu.biblioteca;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gracem on 2016/09/29.
 */
public class User {

    private String libraryNumber;
    private String password;
    private boolean loggedIn;
    private String name;
    private String email;
    private String phoneNumber;

    public User(String libraryNumber, String password) throws InvalidLibraryNumber{
        validateLibraryNumber(libraryNumber);
        this.libraryNumber =libraryNumber;
        this.password = password;
    }

    public User(String libraryNumber, String password, String name,String email, String phoneNumber) throws InvalidLibraryNumber{
        this(libraryNumber, password);
        this.name =name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    private void validateLibraryNumber(String libraryNumber) throws InvalidLibraryNumber {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(libraryNumber);
        if (!matcher.matches()){
            throw new InvalidLibraryNumber("Library number is invalid "+ libraryNumber);
        }
    }

    public void setLoggedIn(boolean loggedIn){
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (loggedIn != user.loggedIn) return false;
        if (!libraryNumber.equals(user.libraryNumber)) return false;
        return password.equals(user.password);

    }

    @Override
    public int hashCode() {
        int result = libraryNumber.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (loggedIn ? 1 : 0);
        return result;
    }
}
