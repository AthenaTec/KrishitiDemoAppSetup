package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.realappraiser.gharvalue.model.OflineCase;

import java.util.List;

/**
 * Created by user on 15-02-2018.
 */

@Dao
public interface InterfaceOfflineCaseQuery {

    @Query("SELECT * FROM OflineCase")
    List<OflineCase> get_OflineCase();

    @Query("SELECT * FROM OflineCase where CaseId = (:caseid_)")
    List<OflineCase> getOflineCase_caseID(int caseid_);

    @Insert
    void insertAll(OflineCase oflineCase);

    @Query("DELETE FROM OflineCase where CaseId = (:caseid_)")
    public void deleteRow(int caseid_);

    // Delete all rows
    @Query("DELETE FROM OflineCase")
    public void deleteRow();


}
