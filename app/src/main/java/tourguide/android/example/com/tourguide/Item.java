package tourguide.android.example.com.tourguide;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * Details of attractions/activities read from JSON file is represented using the Item class
 */
public class Item implements Parcelable {

    private int id;
    private int imageResourceId;
    private String activity_name;
    private String activity_thumbnail;
    private String activity_teaser_text;
    private String activity_details;
    private String activity_website;
    private String activity_phone_number;
    private String address_name;
    private String address_street;
    private String city_state_zip;
    private String address_directions_link;
    private String activity_specifics;
    private String activity_hours;
    private String subCategoryName;

    public Item(int id, String activity_name, Integer imageResource) {
        this.id = id;
        this.activity_name = activity_name;
        this.imageResourceId = imageResource;
    }

    protected Item(Parcel in) {
        id = in.readInt();
        imageResourceId = in.readInt();
        activity_name = in.readString();
        activity_thumbnail = in.readString();
        activity_teaser_text = in.readString();
        activity_details = in.readString();
        activity_website = in.readString();
        activity_phone_number = in.readString();
        address_name = in.readString();
        address_street = in.readString();
        city_state_zip = in.readString();
        address_directions_link = in.readString();
        activity_specifics = in.readString();
        activity_hours = in.readString();
        subCategoryName = in.readString();
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_thumbnail() {
        return activity_thumbnail;
    }

    public void setActivity_thumbnail(String activity_thumbnail) {
        this.activity_thumbnail = activity_thumbnail;
    }

    public String getActivity_teaser_text() {
        return activity_teaser_text;
    }

    public void setActivity_teaser_text(String activity_teaser_text) {
        this.activity_teaser_text = activity_teaser_text;
    }

    public String getActivity_details() {
        return activity_details;
    }

    public void setActivity_details(String activity_details) {
        this.activity_details = activity_details;
    }

    public String getActivity_website() {
        return activity_website;
    }

    public void setActivity_website(String activity_website) {
        this.activity_website = activity_website;
    }

    public String getActivity_phone_number() {
        return activity_phone_number;
    }

    public void setActivity_phone_number(String activity_phone_number) {
        this.activity_phone_number = activity_phone_number;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public String getAddress_street() {
        return address_street;
    }

    public void setAddress_street(String address_street) {
        this.address_street = address_street;
    }

    public String getCity_state_zip() {
        return city_state_zip;
    }

    public void setCity_state_zip(String city_state_zip) {
        this.city_state_zip = city_state_zip;
    }

    public String getAddress_directions_link() {
        return address_directions_link;
    }

    public void setAddress_directions_link(String address_directions_link) {
        this.address_directions_link = address_directions_link;
    }

    public String getActivity_specifics() {
        return activity_specifics;
    }

    public void setActivity_specifics(String activity_specifics) {
        this.activity_specifics = activity_specifics;
    }

    public String getActivity_hours() {
        return activity_hours;
    }

    public void setActivity_hours(String activity_hours) {
        this.activity_hours = activity_hours;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(imageResourceId);
        parcel.writeString(activity_name);
        parcel.writeString(activity_thumbnail);
        parcel.writeString(activity_teaser_text);
        parcel.writeString(activity_details);
        parcel.writeString(activity_website);
        parcel.writeString(activity_phone_number);
        parcel.writeString(address_name);
        parcel.writeString(address_street);
        parcel.writeString(city_state_zip);
        parcel.writeString(address_directions_link);
        parcel.writeString(activity_specifics);
        parcel.writeString(activity_hours);
        parcel.writeString(subCategoryName);
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }
}
