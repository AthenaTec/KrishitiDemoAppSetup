package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.realappraiser.gharvalue.model.GetPhoto;

import java.util.List;

/**
 * Created by user on 15-02-2018.
 */

@Dao
public interface InterfaceGetPhotoQuery {

    @Query("SELECT * FROM GetPhotoModel")
    List<GetPhoto> getPhoto();


    @Query("SELECT * FROM GetPhotoModel where propertyId = (:propertyid)")
    List<GetPhoto> getPhoto_propertyid(int propertyid);

    @Insert
    void insertAll(GetPhoto getPhoto);

    @Query("DELETE FROM GetPhotoModel where propertyId = (:propertyid)")
    public void deleteRow(int propertyid);

    @Delete
    void delete(GetPhoto delacase);

    // Delete all rows
    @Query("DELETE FROM GetPhotoModel")
    public void deleteRow();

    @Query("SELECT * FROM GetPhotoModel where propertyId = (:propertyid) AND defaultimage = (:is_open)")
    List<GetPhoto> getPhoto_propertyid_getDefaultimage(int propertyid,boolean is_open);





}
