package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.realappraiser.gharvalue.model.Proximity;

import java.util.List;

/**
 * Created by user on 15-02-2018.
 */

@Dao
public interface InterfaceProximityQuery {

    @Query("SELECT * FROM ProximityModal")
    List<Proximity> getProximity();

    @Query("SELECT * FROM ProximityModal where CaseId = (:caseid_)")
    List<Proximity> getProximity_caseID(int caseid_);

    @Query("SELECT COUNT(*) from ProximityModal")
    int countdata();

    @Insert
    void insertAll(Proximity proximity);

    @Delete
    void delete(Proximity proximity);

    @Query("DELETE FROM ProximityModal")
    public void deleteTable();

    @Query("DELETE FROM ProximityModal where CaseId = (:caseid_)")
    public void deleteRow(int caseid_);

    // Delete all rows
    @Query("DELETE FROM ProximityModal")
    public void deleteRow();



}
