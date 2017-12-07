package edu.fullerton.kevin.movierating;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Kevin on 12/6/2017.
 */

public class MostRecent extends Fragment {
    private ListView movieListView;
    private String currentTabTag;
    private Menu menu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.most_recent, container, false);
        movieListView = (ListView) view.findViewById(R.id.mostRecent);

        setHasOptionsMenu(true);
        refreshMovieList();

        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), EditMovie.class);
                Movie movie = (Movie) movieListView.getItemAtPosition(position);
                intent.putExtra("id", movie.getMovieID());
                intent.putExtra("name", movie.getrName());
                intent.putExtra("date", movie.getrDate());
                intent.putExtra("rating", movie.getRating());
                intent.putExtra("askMeLater", movie.getAskMeLater());
                startActivity(intent);

            }
        });
        return view;
    }

    public void refreshMovieList(){
        Context context = getActivity().getApplicationContext();
        MovieDB db = new MovieDB(context);
        ArrayList<Movie> movies = db.getMovies();

        MovieAdapter adapter = new MovieAdapter(context, movies);
        movieListView.setAdapter(adapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        refreshMovieList();
    }
}
