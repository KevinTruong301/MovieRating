package edu.fullerton.kevin.movierating;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Kevin on 12/7/2017.
 */

public class EditMovie extends AppCompatActivity implements View.OnClickListener{

    private String name;
    private String date;
    private int rating;
    private boolean askMeLater;
    private Intent intent;
    private Button save;
    private Button cancel;
    private Button delete;


    public EditMovie() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_movie);

        intent = getIntent();

        name = intent.getStringExtra("name");
        date = intent.getStringExtra("date");
        rating = intent.getIntExtra("rating", 0);
        askMeLater = intent.getBooleanExtra("askMeLater", false);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save:
                break;
            case R.id.cancel:
                Intent intent = new Intent(this, Main.class);
                startActivity(intent);
                break;
            case R.id.delete:
        }
    }
}
