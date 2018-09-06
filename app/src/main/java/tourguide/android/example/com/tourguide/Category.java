package tourguide.android.example.com.tourguide;

import java.util.List;
/*
 * Top most item for data read from JSON file
 */
public class Category {
    //identifier to distinguish categories
    private int id;

    //Name of this category
    private String title;

    //List of sub categories under the category item
    private List<SubCategory> subCategories;

    Category(int id, String title) {
        this.id = id;
        this.title = title;
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

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
