package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.realappraiser.gharvalue.model.IndProperty;

import java.util.List;

/**
 * Created by user on 15-02-2018.
 */

@Dao
public interface InterfaceIndpropertyQuery {

    @Query("SELECT * FROM indpropertymodal")
    List<IndProperty> getIndProperty();

    @Query("SELECT * FROM indpropertymodal where CaseId = (:caseid_)")
    List<IndProperty> getIndProperty_caseID(int caseid_);

    @Query("SELECT COUNT(*) from indpropertymodal")
    int countdata();

    @Insert
    void insertAll(IndProperty indProperty);

    @Delete
    void delete(IndProperty indProperty);

    @Query("DELETE FROM indpropertymodal")
    public void deleteTable();

    @Query("DELETE FROM indpropertymodal where CaseId = (:caseid_)")
    public void deleteRow(int caseid_);

    // Delete all rows
    @Query("DELETE FROM indpropertymodal")
    public void deleteRow();




}
