package edu.fullerton.kevin.movierating;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Main extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    private MovieDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

       mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());
       mViewPager = (ViewPager) findViewById(R.id.pager);
       setmViewPager(mViewPager);
       db = new MovieDB(getApplicationContext());
       tabLayout = (TabLayout) findViewById((R.id.tabs));
       tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Movie movie = new Movie("New Movie", "DATE", 1, false);
        db.insertMovie(movie);
        Intent intent = new Intent(Main.this, Main.class);
        this.startActivity(intent);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.movie_menu, menu);
        return true;
    }

    private void setmViewPager(ViewPager viewPager){
        DemoCollectionPagerAdapter adapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch(i){
                case 0:
                    return new MostRecent();
                case 1:
                    return new HighestRated();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0:
                    return "Most Recent";
                case 1:
                    return "Highest Rated";
                default:
                    return null;
            }
        }
    }
}
