package com.twu.biblioteca;

public class Movie {
    private String name;
    private int year;
    private String director;
    private String rate;

    public Movie(String name, int year, String director, String rate) throws InvalidMovieRating {
        validateRating(rate);
        this.name = name;
        this.year = year;
        this.director =director;
        this.rate = rate;
    }

    private void validateRating(String rating) throws InvalidMovieRating{
        if (rating != "unrated") {
            Double intRating = Double.valueOf(rating);
            if (intRating < 1 || intRating > 10) {
                throw new InvalidMovieRating("Rating is invalid : " + rating);
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (year != movie.year) return false;
        if (name != null ? !name.equals(movie.name) : movie.name != null) return false;
        if (director != null ? !director.equals(movie.director) : movie.director != null) return false;
        return rate != null ? rate.equals(movie.rate) : movie.rate == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + year;
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        return result;
    }
}
