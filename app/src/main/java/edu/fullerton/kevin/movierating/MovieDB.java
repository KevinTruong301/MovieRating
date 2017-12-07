package edu.fullerton.kevin.movierating;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Kevin on 12/6/2017.
 */

public class MovieDB {

    public static final String DB_NAME = "movies.db";
    public static final int DB_VERSION = 1;

    //DB constants
    public static final String TABLE = "Movie";

    public static final String ID = "_id";
    public static final int ID_COLUMN = 0;

    public static final String NAME = "name";
    public static final int NAME_COLUMN = 1;

    public static final String RATING = "rating";
    public static final int RATING_COLUMN = 2;

    public static final String DATE = "date";
    public static final int DATE_COLUMN = 3;

    public static final String ASK_ME_LATER = "askMeLater";
    public static final int ASK_ME_LATER_COLUMN = 4;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " (" +
                    ID      +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME    +" TEXT, " +
                    RATING  +" REAL, " +
                    DATE    +" TEXT, " +
                    ASK_ME_LATER + " BOOL );";

    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE;

    public static final String TRUNCATE_ALARM_TABLE =
            "DELETE FROM " + TABLE;

    private static class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
            super(context, name, factory, version, errorHandler);
        }

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public MovieDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    public ArrayList<Movie> getMovies() {
        openReadableDB();
        //gets everything
        Cursor cursor = db.query(TABLE, null, null, null, null, null, null);
        ArrayList<Movie> movies = new ArrayList<Movie>();
        while(cursor.moveToNext()){
            movies.add(getInfoFromCursor(cursor));
        }

        closeDB();
        return movies;
    }

    public Movie getInfoFromCursor(Cursor cursor){
        try{
            Movie movie = new Movie(
                    cursor.getInt(ID_COLUMN),
                    cursor.getString(NAME_COLUMN),
                    cursor.getString(DATE_COLUMN),
                    cursor.getFloat(RATING_COLUMN),
                    cursor.getInt(ASK_ME_LATER_COLUMN) > 0
            );

            return movie;
        } catch (Exception e)
        {
            return null;
        }
    }

    public void insertMovie(Movie movie){
        ContentValues cv = new ContentValues();
        cv.put(NAME, movie.getrName());
        cv.put(RATING,movie.getRating());
        cv.put(DATE,movie.getrDate());
        cv.put(ASK_ME_LATER, movie.getAskMeLater());

        this.openWriteableDB();
        db.insert(TABLE,null, cv);
        this.closeDB();
    }

    public void updateMovie(Movie movie){
        ContentValues cv = new ContentValues();
        cv.put(NAME, movie.getrName());
        cv.put(RATING,movie.getRating());
        cv.put(DATE,movie.getrDate());
        cv.put(ASK_ME_LATER, movie.getAskMeLater());

        String where = ID + "=?";
        String[] whereArgs = { String.valueOf(movie.getMovieID()) };

        this.openWriteableDB();
        db.update(TABLE, cv, where, whereArgs);
        this.closeDB();
    }

    public void deleteMovie(int id){
        String where = ID + "=?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        db.delete(TABLE, where, whereArgs);
        this.closeDB();
    }

    public void deleteTable(){
        this.openWriteableDB();
        db.execSQL(TRUNCATE_ALARM_TABLE);
        this.closeDB();
    }
}
