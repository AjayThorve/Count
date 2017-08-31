package org.aat.count;

/**
 * Created by ajayanilthorve on 6/18/17.
 */

public class items_counter {

    private float count;
    private String Category;

    public items_counter(float count, String category) {
        this.count = count;
        Category = category;
    }

    public String getCategory() {
        return Category;
    }

    public float getCount() {
        return count;
    }
}
