package edu.fullerton.kevin.movierating;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by Kevin on 12/7/2017.
 */

public class EditMovie extends AppCompatActivity implements View.OnClickListener{

    private int id;
    private String name;
    private String date;
    private float rating;
    private boolean askMeLater;
    private Intent intent;
    private Button save;
    private Button cancel;
    private Button delete;
    private MovieDB db;
    private EditText editName;
    private EditText editDate;
    private RatingBar editRating;


    public EditMovie() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_movie);

        intent = getIntent();

        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        date = intent.getStringExtra("date");
        rating = intent.getFloatExtra("rating", 0);
        askMeLater = intent.getBooleanExtra("askMeLater", false);

        editName = (EditText) findViewById(R.id.editName);
        editDate = (EditText) findViewById(R.id.editDate);
        editRating = (RatingBar) findViewById(R.id.editRating);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(this);

        db = new MovieDB(this);


        editName.setText(name);
        editDate.setText(date);
        editRating.setRating(rating);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save:
                setValues();
                saveMovie();
                goBackToMain();
                break;
            case R.id.cancel:
                goBackToMain();
                break;
            case R.id.delete:
                deleteMovie();
                goBackToMain();
                break;
        }
    }

    public void deleteMovie(){
        db.deleteMovie(id);
    }

    public void setValues(){
        name = editName.getText().toString();
        date = editDate.getText().toString();
        rating = editRating.getRating();
    }

    public void saveMovie(){
        Movie newMovie = new Movie(id,name,date,rating,askMeLater);
        db.updateMovie(newMovie);
    }

    public void goBackToMain(){
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }
}
