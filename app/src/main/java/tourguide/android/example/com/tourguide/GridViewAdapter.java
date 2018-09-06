package tourguide.android.example.com.tourguide;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
 * Responsible for driving GridViews with list of subcategory data
 * Accepts the list of subcategory items and inflates the layout xml with data from this list
 * For each item in the subcategory list, generates a list view to show the attractions for the given
 * sub-category
 */
public class GridViewAdapter extends BaseAdapter {
    private final String categoryName;
    private List<SubCategory> subCategories;
    private LayoutInflater inflater;
    private Context context;
    private Intent listPageIntent;

    GridViewAdapter(Context context, String categoryName) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.categoryName = categoryName;
        this.subCategories = JSONHelper.getSubCategories(context, categoryName);
        Log.d("Adapter", "Create subcategory Adapter " + subCategories.size());
    }

    @Override
    public int getCount() {
        return subCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return subCategories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.sub_category_grid_item, parent, false);
            holder = new ViewHolder();
            assert view != null;
            holder.imageView = (ImageView) view.findViewById(R.id.sub_category_image);
            holder.subCategoryTitle = (TextView) view.findViewById(R.id.sub_category_title);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final SubCategory subCategory = this.subCategories.get(position);
        holder.imageView.setImageDrawable(context.getResources().getDrawable(subCategory.getImageResource()));
        View.OnClickListener loadListView = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Starts a list page content activity when the subcategory grid item is clicked
                GridViewAdapter.this.listPageIntent.putExtra("tourguide.android.example.com.tourguide.subcategory_id", subCategory.getId());
                GridViewAdapter.this.listPageIntent.putExtra("tourguide.android.example.com.tourguide.category_name", GridViewAdapter.this.categoryName);
                GridViewAdapter.this.listPageIntent.putExtra("tourguide.android.example.com.tourguide.sub_category_name", subCategory.getTitle());
                GridViewAdapter.this.context.startActivity(GridViewAdapter.this.listPageIntent);
            }
        };
        holder.imageView.setOnClickListener(loadListView);
        String title = subCategory.getTitle().trim();
        if (!title.isEmpty()) {
            holder.subCategoryTitle.setText(title);
        }
        return view;
    }
    public void setListPageIntent(Intent listPageIntent) {
        this.listPageIntent = listPageIntent;
    }
}

//Place holder for controls in each of the subcategory GridItem view
class ViewHolder {
    ImageView imageView;
    TextView subCategoryTitle;
}