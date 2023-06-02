package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.realappraiser.gharvalue.model.GetPhoto_measurment;

import java.util.List;

/**
 * Created by user on 15-02-2018.
 */

@Dao
public interface InterfaceGetPhotoMeasurmentQuery {

    @Query("SELECT * FROM GetPhoto_measurmentModel")
    List<GetPhoto_measurment> getPhoto();


    @Query("SELECT * FROM GetPhoto_measurmentModel where propertyId = (:propertyid)")
    List<GetPhoto_measurment> getPhoto_propertyid(int propertyid);

    @Insert
    void insertAll(GetPhoto_measurment getPhoto);

    @Query("DELETE FROM GetPhoto_measurmentModel where propertyId = (:propertyid)")
    public void deleteRow(int propertyid);

    @Delete
    void delete(GetPhoto_measurment delacase);

    // Delete all rows
    @Query("DELETE FROM GetPhoto_measurmentModel")
    public void deleteRow();


}
