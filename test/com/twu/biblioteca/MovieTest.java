package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by gracem on 2016/09/29.
 */
public class MovieTest {

    @Test
    public void testMovieWithValidRating() throws InvalidMovieRating {
        Movie greatMovie = new Movie("Pass", 2016, "Grace D", "2.3");
        assertNotNull(greatMovie);
        assertEquals("Pass", greatMovie.getName());
    }

    @Test(expected = InvalidMovieRating.class)
    public void testMovieWithInvalidRating() throws InvalidMovieRating {
        Movie greatMovie = new Movie("Pass", 2016, "Grace D", "0.3");
        assertNull(greatMovie);
    }

    @Test
    public void testMovieWithInvalidRatingOf1() throws InvalidMovieRating {
        Movie greatMovie = new Movie("Pass", 2016, "Grace D", "1");
        assertNotNull(greatMovie);
    }
}
