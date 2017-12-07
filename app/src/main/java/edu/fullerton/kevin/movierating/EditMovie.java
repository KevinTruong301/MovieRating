package edu.fullerton.kevin.movierating;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Kevin on 12/7/2017.
 */

public class EditMovie extends AppCompatActivity {

    private String name;
    private String date;
    private int rating;
    private boolean askMeLater;
    private Intent intent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_movie);

        intent = getIntent();

        name = intent.getStringExtra("name");
        date = intent.getStringExtra("date");
        rating = intent.getIntExtra("rating", 0);
        askMeLater = intent.getBooleanExtra("askMeLater", false);
    }



    public EditMovie() {

    }
}
