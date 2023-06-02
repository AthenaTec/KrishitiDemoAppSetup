package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.realappraiser.gharvalue.model.IndPropertyFloor;

import java.util.List;

/**
 * Created by user on 15-02-2018.
 */

@Dao
public interface InterfaceIndPropertyFloorsQuery {

    @Query("SELECT * FROM IndPropertyFloorModal")
    List<IndPropertyFloor> getIndPropertyFloor();

    @Query("SELECT * FROM IndPropertyFloorModal where CaseId = (:caseid_)")
    List<IndPropertyFloor> getIndPropertyFloor_caseID(int caseid_);

    @Query("SELECT COUNT(*) from IndPropertyFloorModal")
    int countdata();

    @Insert
    void insertAll(IndPropertyFloor indPropertyFloor);

    @Delete
    void delete(IndPropertyFloor indPropertyFloor);

    @Query("DELETE FROM IndPropertyFloorModal")
    public void deleteTable();

    @Query("DELETE FROM IndPropertyFloorModal where CaseId = (:caseid_)")
    public void deleteRow(int caseid_);

    // Delete all rows
    @Query("DELETE FROM IndPropertyFloorModal")
    public void deleteRow();



}
