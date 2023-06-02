package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by kaptas on 6/3/18.
 */

@Entity(tableName = "PropertyUpdateRoomDB")
public class PropertyUpdateRoomDB {

    @PrimaryKey(autoGenerate = true)
    public int property_id;

    @ColumnInfo(name = "caseid")
    public String caseid;
    @ColumnInfo(name = "property_type")
    public String property_type;
    @ColumnInfo(name = "property_category_id")
    public String property_category_id;

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public String getProperty_category_id() {
        return property_category_id;
    }

    public void setProperty_category_id(String property_category_id) {
        this.property_category_id = property_category_id;
    }
}
