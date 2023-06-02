package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.realappraiser.gharvalue.model.Property;

import java.util.List;

/**
 * Created by user on 15-02-2018.
 */

@Dao
public interface InterfacePropertyQuery {

    @Query("SELECT * FROM propertymodal")
    List<Property> getProperty();

    @Query("SELECT * FROM propertymodal where CaseId = (:caseid_)")
    List<Property> getProperty_caseID(int caseid_);

    @Query("SELECT COUNT(*) from propertymodal")
    int countdata();

    @Insert
    void insertAll(Property property);

    @Delete
    void delete(Property property);

    @Query("DELETE FROM propertymodal where CaseId = (:caseid_)")
    public void deleteRow(int caseid_);

    // Delete all rows
    @Query("DELETE FROM propertymodal")
    public void deleteRow();




}
