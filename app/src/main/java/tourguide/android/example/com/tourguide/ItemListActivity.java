package tourguide.android.example.com.tourguide;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/*
 * Shows a list view of recommendations/attraction for the given subcategory
 * recommendation/attraction list data is received through extra data in the intent object
 */
public class ItemListActivity extends AppCompatActivity {

    private Integer subCategoryId = null;
    private String categoryName = null;
    private String subCategoryName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        final ListView listview = (ListView) findViewById(R.id.itemlist);
        Intent detailPageIntent = new Intent(ItemListActivity.this, ItemDetailsActivity.class);

        if(subCategoryId == null) {
            subCategoryId = getIntent().getExtras().getInt("tourguide.android.example.com.tourguide.subcategory_id");
        }

        if(categoryName == null) {
            categoryName = getIntent().getExtras().getString("tourguide.android.example.com.tourguide.category_name");
        }

        if(subCategoryName == null) {
            subCategoryName = getIntent().getExtras().getString("tourguide.android.example.com.tourguide.sub_category_name");
        }

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
//            supportActionBar.setTitle(R.string.app_name);
            supportActionBar.setTitle(categoryName);
        }
        TextView pageTitle = (TextView) findViewById(R.id.subcategoryTitle);
        pageTitle.setText(subCategoryName);

        List<Item> items = JSONHelper.getSubCategoryItems(this, categoryName, subCategoryId);
        ItemListAdapter adapter = new ItemListAdapter(this, items, detailPageIntent);
        listview.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
