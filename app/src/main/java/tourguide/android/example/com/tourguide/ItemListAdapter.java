package tourguide.android.example.com.tourguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agape on 8/27/2018.
 *
 * Responsible for driving list view with list of attraction/recommendations data for the given subcategory
 * Accepts the list of attraction/recommendation data via list of Item objects
 * and inflates the layout xml with data from this list
 * For each item in the Item list, generates a detailed view Intent to show its details
 */

public class ItemListAdapter extends BaseAdapter {
    private Intent detailPageIntent;
    private List<Item> items;
    private Context context;

    ItemListAdapter(Context context, List<Item> listItems, Intent detailPageIntent) {
        this.context = context;
        this.items = listItems;
        this.detailPageIntent = detailPageIntent;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ItemViewHolder holder;
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ItemViewHolder();
            assert view != null;
            view.setTag(holder);
        } else {
            holder = (ItemViewHolder) view.getTag();
        }

        holder.itemTitle = (TextView) view.findViewById(R.id.item_card_title);
        holder.itemTeaser = (TextView) view.findViewById(R.id.item_card_teaser);
        holder.itemImage = (ImageView) view.findViewById(R.id.item_card_image);

        holder.itemTitle.setText(this.items.get(position).getActivity_name());
        holder.itemTeaser.setText(this.items.get(position).getActivity_teaser_text());
        final Drawable drawableItemImage = context.getResources().getDrawable(this.items.get(position).getImageResourceId());
        holder.itemImage.setImageDrawable(drawableItemImage);

        holder.cardView = (CardView) view.findViewById(R.id.item_card);

        View.OnClickListener showDetailsPage = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Item item = ItemListAdapter.this.items.get(position);
                ItemListAdapter.this.detailPageIntent.putExtra("tourguide.android.example.com.tourguide.this_item", item);
                ItemListAdapter.this.context.startActivity(ItemListAdapter.this.detailPageIntent);
            }
        };
        holder.cardView.setOnClickListener(showDetailsPage);
        return view;
    }
}

//Place holder for controls in list view of each of the attractions/recommendations within the subcategory
class ItemViewHolder {
    ImageView itemImage;
    TextView itemTitle;
    TextView itemTeaser;
    CardView cardView;
}