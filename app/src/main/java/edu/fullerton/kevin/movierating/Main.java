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
    private int currentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        Intent intent = getIntent();
        currentTab = intent.getIntExtra("currentTab", 0);


       mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());
       mViewPager = (ViewPager) findViewById(R.id.pager);
       setmViewPager(mViewPager);
        mViewPager.setCurrentItem(currentTab);
       db = new MovieDB(getApplicationContext());
       tabLayout = (TabLayout) findViewById((R.id.tabs));
       tabLayout.setupWithViewPager(mViewPager);
    }

    public int getCurrentTab(){

        return tabLayout.getSelectedTabPosition();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(Main.this, EditMovie.class);
        intent.putExtra("currentTab", tabLayout.getSelectedTabPosition());
        intent.putExtra("addEdit", "add");
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
