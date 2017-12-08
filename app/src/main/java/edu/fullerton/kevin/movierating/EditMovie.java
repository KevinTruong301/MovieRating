package edu.fullerton.kevin.movierating;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by Kevin on 12/7/2017.
 */

public class EditMovie extends AppCompatActivity {

    private int id;
    private String name;
    private String date;
    private String month;
    private String day;
    private String year;
    private float rating;
    private int currentTab;
    private Intent intent;
    private Button save;
    private Button cancel;
    private Button delete;
    private MovieDB db;
    private EditText editName;
    private RatingBar editRating;
    private CheckBox editAskMeLater;
    private String addEdit;
    private final int NOTIFICATION_ID = 1;
    private Notification notification;
    private NotificationManager manager;
    private DatePicker editDate;

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
        currentTab = intent.getIntExtra("currentTab", 0);
        addEdit = intent.getStringExtra("addEdit");

        editName = (EditText) findViewById(R.id.editName);
        editRating = (RatingBar) findViewById(R.id.editRating);
        editAskMeLater = (CheckBox) findViewById(R.id.askMeLater);
        editDate = (DatePicker) findViewById(R.id.editDate);

        db = new MovieDB(this);

        if(!(date == null)){
            parseDate(date);
            editDate.updateDate(Integer.parseInt(year),Integer.parseInt(month), Integer.parseInt(day));
        }



        editName.setText(name);
        editRating.setRating(rating);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(addEdit.equals("add")){
            switch (item.getItemId()){
                case R.id.save:
                    setValues();
                    addMovie();
                    goBackToMain();
                    break;
                case R.id.cancel:
                    goBackToMain();
                    break;
            }
        }
        if(addEdit.equals("edit")){
            switch (item.getItemId()){
                case R.id.save:
                    setValues();
                    saveMovie();
                    goBackToMain();
                    break;
                case R.id.cancel:
                    goBackToMain();
                    break;
            }
        }


        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.addedit_menu, menu);
        return true;
    }

    public void parseDate(String date){
        String[] dateVars= date.split("/");
        month = dateVars[0];
        day = dateVars[1];
        year = dateVars[2];

    }

    public void createNotification(){

        Intent notificationIntent = new Intent(this, EditMovie.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        notificationIntent.putExtra("id", id);
        notificationIntent.putExtra("name", name);
        notificationIntent.putExtra("date", date);
        notificationIntent.putExtra("rating", rating);
        notificationIntent.putExtra("currentTab", currentTab);
        notificationIntent.putExtra("addEdit", "edit");

        int flag = PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, flag);

        int icon = R.drawable.movie;
        CharSequence notiText = "Remember to rate the movie!";
        CharSequence contentTitle = "Remember to rate the movie!";
        CharSequence contentText = "Select to rate movie";

        notification = new NotificationCompat.Builder(this)
                .setSmallIcon(icon)
                .setTicker(notiText)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void deleteMovie(){
        db.deleteMovie(id);
    }

    public void addMovie(){
        Movie newMovie = new Movie(name,date,rating);
        db.insertMovie(newMovie);
    }

    public void setValues(){
        name = editName.getText().toString();
        month = String.valueOf(editDate.getMonth());
        day = String.valueOf(editDate.getDayOfMonth());
        year= String.valueOf(editDate.getYear());

        date = month+"/"+day+"/"+year;
        rating = editRating.getRating();
    }

    public void saveMovie(){
        Movie newMovie = new Movie(id,name,date,rating);
        db.updateMovie(newMovie);
    }

    public void goBackToMain(){
        Intent intent = new Intent(this, Main.class);
        intent.putExtra("currentTab", currentTab);

        if(editAskMeLater.isChecked()){
            createNotification();
            manager.notify(NOTIFICATION_ID, notification);
        }

        startActivity(intent);
    }
}
