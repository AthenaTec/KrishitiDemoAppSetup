package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.realappraiser.gharvalue.model.PropertyUpdateRoomDB;

import java.util.List;

/**
 * Created by kaptas on 6/3/18.
 */

@Dao
public interface PropertyUpdateCategory {

    @Query("SELECT * FROM propertyupdateroomdb")
    List<PropertyUpdateRoomDB> getPropertyUpdateModel();


    @Query("SELECT * FROM propertyupdateroomdb where caseid = (:caseid)")
    PropertyUpdateRoomDB getPropertyUpdateRoomDB(String caseid);


    @Query("SELECT COUNT(*) from propertyupdateroomdb")
    int countdata();

    @Insert
    void insertAll(PropertyUpdateRoomDB propertyupdateroomdb);

    @Update
    void updateData(PropertyUpdateRoomDB propertyupdateroomdb);


    @Query("update propertyupdateroomdb set property_type=:isproperty_type, property_category_id=:isproperty_category_id where caseid in(:caseidIn)")
    long updatePropertytype(String isproperty_type, String isproperty_category_id, String caseidIn);

    @Delete
    void delete(PropertyUpdateRoomDB propertyupdateroomdb);

    @Query("DELETE FROM propertyupdateroomdb")
    public void deleteTable();

    // Delete all rows
    @Query("DELETE FROM propertyupdateroomdb")
    public void deleteRow();




}
