package edu.fullerton.kevin.movierating;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Kevin on 12/6/2017.
 */

public class MovieLayout extends RelativeLayout{

    private RatingBar ratingBar;
    private TextView nameTextView;
    private TextView dateTextView;

    private Movie movie;
    private MovieDB db;
    private Context context;

    public MovieLayout(Context context) {
        super(context);
    }

    public MovieLayout(Context context, Movie m){
        super(context);

        this.context = context;

        db = new MovieDB(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_movies, this, true);

        ratingBar = (RatingBar) findViewById(R.id.rating);
        nameTextView = (TextView) findViewById(R.id.name);
        dateTextView = (TextView) findViewById(R.id.date);


        setMovie(m);
    }

    public void setMovie(Movie m){
        movie = m;
        nameTextView.setText(m.getrName());
        dateTextView.setText(m.getrDate());
        ratingBar.setRating(m.getRating());

    }

}
