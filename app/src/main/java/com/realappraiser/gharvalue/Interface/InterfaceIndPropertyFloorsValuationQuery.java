package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.realappraiser.gharvalue.model.IndPropertyFloorsValuation;

import java.util.List;

/**
 * Created by user on 15-02-2018.
 */

@Dao
public interface InterfaceIndPropertyFloorsValuationQuery {

    @Query("SELECT * FROM IndPropertyFloorsValuationModal")
    List<IndPropertyFloorsValuation> geIndPropertyFloorsValuation();

    @Query("SELECT * FROM IndPropertyFloorsValuationModal where CaseId = (:caseid_)")
    List<IndPropertyFloorsValuation> getIndPropertyFloorsValuation_caseID(int caseid_);

    @Query("SELECT COUNT(*) from IndPropertyFloorsValuationModal")
    int countdata();

    @Insert
    void insertAll(IndPropertyFloorsValuation indPropertyFloorsValuation);

    @Delete
    void delete(IndPropertyFloorsValuation indPropertyFloorsValuation);

    @Query("DELETE FROM IndPropertyFloorsValuationModal")
    public void deleteTable();

    @Query("DELETE FROM IndPropertyFloorsValuationModal where CaseId = (:caseid_)")
    public void deleteRow(int caseid_);

    // Delete all rows
    @Query("DELETE FROM IndPropertyFloorsValuationModal")
    public void deleteRow();


}
