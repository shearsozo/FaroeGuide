package tourguide.android.example.com.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

// Inflates the sub category grid view for the given category
// Creates a GridViewAdapter with subcategory items for the given category
public class GridViewFragment extends Fragment {
    private Category category;
    private Intent listPageIntent;

    public GridViewFragment() {
    }

    public void setListPageIntent(Intent listPageIntent) {
        this.listPageIntent = listPageIntent;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sub_category_grid_view,null);
        GridView gridView = (GridView) rootView.findViewById(R.id.subCategoryGridView);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(getActivity(), category.getTitle());
        gridViewAdapter.setListPageIntent(listPageIntent);
        gridView.setAdapter(gridViewAdapter);
        // Inflate the layout for this fragment
        return rootView;
    }

}
