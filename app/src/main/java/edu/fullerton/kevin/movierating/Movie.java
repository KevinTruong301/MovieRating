package edu.fullerton.kevin.movierating;

/**
 * Created by Kevin on 12/6/2017.
 */

public class Movie {
    private String rName;
    private String rDate;
    private int rating;
    private boolean askMeLater;
    private int movieID;

    public Movie(int ID, String rName, String rDate, int rating, boolean askMeLater) {
        this.movieID = ID;
        this.rName = rName;
        this.rDate = rDate;
        this.rating = rating;
        this.askMeLater = askMeLater;
    }


    public Movie(String rName, String rDate, int rating, boolean askMeLater) {
        this.rName = rName;
        this.rDate = rDate;
        this.rating = rating;
        this.askMeLater = askMeLater;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean getAskMeLater() {
        return askMeLater;
    }

    public void setAskMeLater(boolean askMeLater) {
        this.askMeLater = askMeLater;
    }
}
