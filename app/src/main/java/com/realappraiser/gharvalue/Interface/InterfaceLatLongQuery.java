package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.realappraiser.gharvalue.model.LatLongDetails;

import java.util.List;

/**
 * Created by kaptas on 16/2/18.
 */

@Dao
public interface InterfaceLatLongQuery {

    @Query("SELECT * FROM LatLongDetails where CaseId = (:caseid_)")
    List<LatLongDetails> getLatLongDetails_caseID(int caseid_);

    @Insert
    void insertAll(LatLongDetails latLongDetails);

    @Query("DELETE FROM LatLongDetails where CaseId = (:caseid_)")
    public void deleteRow(int caseid_);

    // Delete all rows
    @Query("DELETE FROM LatLongDetails")
    public void deleteRow();



}

