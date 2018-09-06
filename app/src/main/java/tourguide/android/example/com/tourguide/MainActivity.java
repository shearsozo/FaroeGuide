package tourguide.android.example.com.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/*
 * Landing page of the tour guide app is powered by this Activity class
 * Loads the ViewPager to offer sliding transition between the Category tabs
 * Loads the GridViewFragment to show the SubCategory items as grid items with the use of GridViewAapter
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
            }
            //ViewPager control with tab layout
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    /*
     * Accepts viewPager control and adds as many pages to it as the number of categories
     * present in the input json file
     */
    private void setupViewPager(ViewPager viewPager) {
        List<Category> categories = JSONHelper.getCategories(this);
        assert (categories != null);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Intent listPageIntent = new Intent(MainActivity.this, ItemListActivity.class);
        //For each category a) generate a gridview
        for (Category category : categories) {
            GridViewFragment subCategoryGrid = new GridViewFragment();
            subCategoryGrid.setCategory(category);
            subCategoryGrid.setListPageIntent(listPageIntent);
            adapter.addFrag(subCategoryGrid, category.getTitle());
        }
        viewPager.setAdapter(adapter);
    }

    /*
     * Adapter object for the viewPager control
     */
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}