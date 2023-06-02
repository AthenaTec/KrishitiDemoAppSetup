package com.realappraiser.gharvalue.model;

/**
 * Created by kaptas on 28/12/17.
 */

public class ImageBase64 extends NewImage {
    private String image64;
    private String editTextValue;

    public String getImage64() {
        return image64;
    }

    public void setImage64(String image64) {
        this.image64 = image64;
    }

    public void setEditTextValue(String editTextValue) {
        this.editTextValue = editTextValue;
    }

    public String getEditTextValue() {
        return editTextValue;
    }

}
