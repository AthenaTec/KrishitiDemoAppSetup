package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.realappraiser.gharvalue.model.IndPropertyValuation;

import java.util.List;

/**
 * Created by user on 15-02-2018.
 */

@Dao
public interface InterfaceIndPropertyValuationQuery {

    @Query("SELECT * FROM IndPropertyValuationModal")
    List<IndPropertyValuation> getIndPropertyValuation();

    @Query("SELECT * FROM IndPropertyValuationModal where CaseId = (:caseid_)")
    List<IndPropertyValuation> getIndPropertyValuation_caseID(int caseid_);

    @Query("SELECT COUNT(*) from IndPropertyValuationModal")
    int countdata();

    @Insert
    void insertAll(IndPropertyValuation indPropertyValuation);

    @Delete
    void delete(IndPropertyValuation indPropertyValuation);

    @Query("DELETE FROM IndPropertyValuationModal")
    public void deleteTable();

    @Query("DELETE FROM IndPropertyValuationModal where CaseId = (:caseid_)")
    public void deleteRow(int caseid_);

    // Delete all rows
    @Query("DELETE FROM IndPropertyValuationModal")
    public void deleteRow();



}
