package tourguide.android.example.com.tourguide;

import android.graphics.drawable.Drawable;

import java.util.List;

/*
 * Sub category data read from JSON are represented via this class
 * Each Category object may contain one or more subcategories
 * Each subcategory contains list of activities under the given sub category
 */
public class SubCategory {
    // identifier to distinguish sub category items within a category
    private int id;

    // Sub category name
    private String title;

    // Drawable object that corresponds to the image name read from the JSON file for this sub category
    private Integer imageResource;

    // List of activities/attractions under this subcategory
    private List<Item> itemsInSubCategory;

    SubCategory(int id, String title, Integer imageResource) {
        this.id = id;
        this.title = title;
        this.imageResource = imageResource;
    }

    public List<Item> getItemsInSubCategory() {
        return itemsInSubCategory;
    }

    public void setItemsInSubCategory(List<Item> itemsInSubCategory) {
        this.itemsInSubCategory = itemsInSubCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImageResource() {
        return imageResource;
    }
}
