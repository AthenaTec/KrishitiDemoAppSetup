package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by user on 05-01-2018.
 */

@SuppressWarnings("ALL")
@Entity(tableName = "GetPhoto_measurmentModel")
public class GetPhoto_measurment {

    @PrimaryKey(autoGenerate = true)
    public int dummyID;

    @ColumnInfo(name = "Id")
    private int Id;
    @ColumnInfo(name = "Logo")
    private String Logo;
    @ColumnInfo(name = "Title")
    private String Title;
    @ColumnInfo(name = "newimage")
    private boolean newimage;
    @ColumnInfo(name = "defaultimage")
    private boolean defaultimage;
    @ColumnInfo(name = "PropertyId")
    private int PropertyId;

    public boolean isDefaultimage() {
        return defaultimage;
    }

    public void setDefaultimage(boolean defaultimage) {
        this.defaultimage = defaultimage;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public boolean isNewimage() {
        return newimage;
    }

    public void setNewimage(boolean newimage) {
        this.newimage = newimage;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getPropertyId() {
        return PropertyId;
    }

    public void setPropertyId(int propertyId) {
        PropertyId = propertyId;
    }



}
