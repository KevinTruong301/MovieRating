package edu.fullerton.kevin.movierating;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Kevin on 12/6/2017.
 */

public class MovieAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MovieLayout movieLayout = null;
        Movie movie = movies.get(i);

        if(view == null){
            movieLayout = new MovieLayout(context, movie);
        }
        else{
            movieLayout = (MovieLayout) view;
            movieLayout.setMovie(movie);
        }

        return movieLayout;
    }
}
