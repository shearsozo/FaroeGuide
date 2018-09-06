package tourguide.android.example.com.tourguide;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONHelper {

    //Name of the data JSON file from which all tour guide data is loaded
    private static String JSON_FILE_NAME = "faroeislandplaces.json";
    //JSON string constants to identify fields
    //Field name of the top most field in the JSON file which will hold an array of objects
    private static String FIELD_CATEGORIES = "Categories";

    private static JSONArray categoriesArray = null;

    /*
     * Reads the data from JSON file and returns the "Categories" JSONArray
     */
    private static JSONArray parseJSON(Context context) {
        JSONArray categoriesObject;
        try {
            InputStream inputStream = context.getAssets().open(JSON_FILE_NAME);
            byte[] inputBuffer = new byte[inputStream.available()];
            inputStream.read(inputBuffer);
            inputStream.close();
            String jsonString = new String(inputBuffer, "UTF-8");//Find the constant for encoding
            JSONObject faroeIslandInfo = new JSONObject(jsonString);
            categoriesObject = faroeIslandInfo.getJSONArray(FIELD_CATEGORIES);
            return categoriesObject;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            //Parsing json string failed
            e.printStackTrace();
        }
        return null;
    }

    public static List<Category> getCategories(Context context) {
        if (categoriesArray == null) {
            categoriesArray = JSONHelper.parseJSON(context); //populates categoriesArray from file
        }
        assert (categoriesArray != null);
        //Parse categoriesArray JSONArray and read subcategories
        List<Category> categories = new ArrayList<>();
        try {
            for (int j = 0; j < categoriesArray.length(); j++) {
                JSONObject categoryObj = categoriesArray.getJSONObject(j);
                String categoryName = null;
                //The first field name in the category JSONObject is the name of the category
                for (final Iterator<String> iter = categoryObj.keys(); iter.hasNext(); ) {
                    categoryName = iter.next();
                    break;
                }
                Category category = new Category(j, categoryName);
                categories.add(category);
            }
            return categories;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<SubCategory> getSubCategories(Context context, String categoryName) {

        List<SubCategory> subCategories = new ArrayList<>();
        JSONArray subCategoriesArray = null;
        try {
            subCategoriesArray = getSubCategoriesJSONArray(categoryName);
            for (int k = 0; k < subCategoriesArray.length(); k++) {
                JSONObject subCategoryDetails = subCategoriesArray.getJSONObject(k);
                Integer category_id = (Integer) subCategoryDetails.get("category_id");
                String category_thumbnail = (String) subCategoryDetails.get("category_thumbnail");
                String category_name = (String) subCategoryDetails.get("category_name");
                Integer imageId = context.getResources().getIdentifier(category_thumbnail, "drawable", context.getPackageName());
                SubCategory subCategory = new SubCategory(category_id, category_name, imageId);
                subCategories.add(subCategory);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return subCategories;
    }

    public static List<Item> getSubCategoryItems(Context context, String categoryName, int subCategoryId) {
        List<Item> subCategoryItems = new ArrayList<>();
        JSONArray subCategoriesArray = getSubCategoriesJSONArray(categoryName);
        try {
            for (int k = 0; k < subCategoriesArray.length(); k++) {
                JSONObject subCategoryDetails = subCategoriesArray.getJSONObject(k);
                String subCategoryName = (String) subCategoryDetails.get("category_name");
                if (subCategoryId == subCategoryDetails.getInt("category_id")) {
                    JSONArray subCategoryActivities = subCategoryDetails.getJSONArray("category_activities");
                    // Parse subCategoryActivities array to read attractions/popular destination details for the given subcategory
                    for (int i = 0; i < subCategoryActivities.length(); i++) {
                        JSONObject listViewItem = subCategoryActivities.getJSONObject(i);
                        String activity_thumbnail = (String) listViewItem.get("activity_thumbnail");
                        int activityImageId = context.getResources().getIdentifier(activity_thumbnail, "drawable", context.getPackageName());
                        if (activityImageId == 0) {
                            System.out.println("Something wrong activity_thumbnail: [" + activity_thumbnail + "] image exist?");
                            continue;
                        }
                        Integer activity_id = listViewItem.getInt("activity_id");
                        String activity_name = listViewItem.getString("activity_name");
                        Item item = new Item(activity_id, activity_name, activityImageId);
                        item.setActivity_teaser_text(listViewItem.getString("activity_teaser_text"));
                        item.setActivity_details(listViewItem.getString("activity_details"));
                        item.setActivity_website(listViewItem.getString("activity_website"));
                        item.setActivity_phone_number(listViewItem.getString("activity_phone_number"));
                        item.setAddress_street(listViewItem.getString("address_street"));
                        item.setCity_state_zip(listViewItem.getString("city_state_zip"));
                        item.setAddress_directions_link("address_directions_link");
                        item.setActivity_specifics("activity_specifics");
                        item.setActivity_hours("activity_hours");
                        item.setSubCategoryName(subCategoryName);
                        subCategoryItems.add(item);
                    }
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return subCategoryItems;
    }

    private static JSONArray getSubCategoriesJSONArray(String categoryName) {
        assert (categoryName != null);
        JSONObject categoryObj = null;
        try {
            for (int j = 0; j < categoriesArray.length(); j++) {
                categoryObj = categoriesArray.getJSONObject(j);
                boolean found = false;
                for (final Iterator<String> iter = categoryObj.keys(); iter.hasNext(); ) {
                    if (categoryName.equals(iter.next())) {
                        found = true;
                        break;
                    }
                }
                if(found) {
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assert (categoryObj != null);
        try {
            JSONArray subCategoriesArray = categoryObj.getJSONArray(categoryName);
            if (subCategoriesArray == null) {
                throw new IllegalArgumentException("Category Name: [" + categoryName + "] was not found in JSON file");
            }
            return subCategoriesArray;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Category Name: [" + categoryName + "] was not found in JSON file");
    }
}
