package tourguide.android.example.com.tourguide;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * Shows a detailed view of the given recommendations/attraction
 * recommendation/attraction data is received through extra data in the intent object
 */
public class ItemDetailsActivity extends AppCompatActivity {
    private Item item = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        if(item == null) {
            item = getIntent().getParcelableExtra("tourguide.android.example.com.tourguide.this_item");
            getIntent().removeExtra("tourguide.android.example.com.tourguide.this_item");
        }

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(item.getSubCategoryName());
        }

        DetailsViewHolder details = new DetailsViewHolder();

        details.detailPageImage = findViewById(R.id.detail_page_image);
        details.activityName = findViewById(R.id.activity_name);
        details.address = findViewById(R.id.address);
        details.phone = findViewById(R.id.phone);
        details.website = findViewById(R.id.website);
        details.detailText = findViewById(R.id.detail_text);

        details.activityName.setText(item.getActivity_name());
        details.detailText.setText(item.getActivity_details());

        if(showControlIfDataExist(item.getAddress_street(), details.address, R.id.icon_location)) {
            View.OnClickListener showMaps = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String searchString = item.getActivity_name() + " " + item.getAddress_street();
                    String mapsURL = "https://www.google.com/maps/search/" + searchString;
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse(mapsURL));
                    startActivity(intent);
                }
            };
            findViewById(R.id.map_launcher).setOnClickListener(showMaps);
        }

        if(showControlIfDataExist(item.getActivity_phone_number(), details.phone, R.id.icon_phone)) {
            View.OnClickListener callNumber = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String numberWithoutSpaces = item.getActivity_phone_number().replaceAll(" ", "");
                    String phoneNumberURI = "tel:" + numberWithoutSpaces;
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse(phoneNumberURI));
                    startActivity(intent);
                }
            };
            findViewById(R.id.call_number).setOnClickListener(callNumber);
        }

        if(showControlIfDataExist(item.getActivity_website(), details.website, R.id.icon_site)) {
            View.OnClickListener openSite = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(item.getActivity_website()));
                    startActivity(intent);
                }
            };
            findViewById(R.id.site_launcher).setOnClickListener(openSite);
        }

        int imageResourceId = item.getImageResourceId();
        final Drawable drawableItemImage = ItemDetailsActivity.this.getResources().getDrawable(imageResourceId);
        details.detailPageImage.setImageDrawable(drawableItemImage);
    }

    /*
     *  Helper function that shows certain controls and the control's corresponding icon controls,
     *  if the @param data is available and not empty
     *  @returns true if @param data is available and not empty
    */
    private boolean showControlIfDataExist(String data, TextView control, int iconId) {
        boolean doesDataExist = data != null && !data.isEmpty();
        if(doesDataExist) {
            control.setVisibility(View.VISIBLE);
            findViewById(iconId).setVisibility(View.VISIBLE);
            control.setText(data);
        } else {
            control.setVisibility(View.INVISIBLE);
            findViewById(iconId).setVisibility(View.INVISIBLE);
        }
        return doesDataExist;
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

//Place holder for controls in detailview of the attraction/recommendation
class DetailsViewHolder {
    ImageView detailPageImage;
    TextView activityName;
    TextView address;
    TextView phone;
    TextView website;
    TextView detailText;
}
