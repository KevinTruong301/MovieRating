package edu.fullerton.kevin.movierating;

/**
 * Created by Kevin on 12/6/2017.
 */

public class Movie {
    private String rName;
    private String rDate;
    private float rating;
    private int movieID;

    public Movie(int ID, String rName, String rDate, float rating) {
        this.movieID = ID;
        this.rName = rName;
        this.rDate = rDate;
        this.rating = rating;
    }


    public Movie(String rName, String rDate, float rating) {
        this.rName = rName;
        this.rDate = rDate;
        this.rating = rating;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getrName(){
        return rName;
    };

    public String getrDate() {
        return rDate;
    };

    public void setrName(String rName) {
        this.rName = rName;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
