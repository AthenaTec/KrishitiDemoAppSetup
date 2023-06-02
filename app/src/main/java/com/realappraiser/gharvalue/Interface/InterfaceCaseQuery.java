package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.realappraiser.gharvalue.model.Case;

import java.util.List;

/**
 * Created by user on 15-02-2018.
 */

@Dao
public interface InterfaceCaseQuery {

    @Query("SELECT * FROM casemodal")
    List<Case> getCase();

    @Query("SELECT * FROM casemodal where CaseId = (:caseid_)")
    List<Case> getCase_caseID(int caseid_);

    @Query("SELECT COUNT(*) from casemodal")
    int countdata();

    @Insert
    void insertAll(Case inscase);

    @Delete
    void delete(Case delacase);

    @Query("DELETE FROM casemodal where CaseId = (:caseid_)")
    public void deleteRow(int caseid_);

    // Delete all rows
    @Query("DELETE FROM casemodal")
    public void deleteRow();

    @Query("update casemodal set status=:updatecasestatus where caseid in(:caseidIn)")
    long updateCaseStatus(String updatecasestatus, String caseidIn);

    @Query("update casemodal set PropertyType=:PropertyType where caseid in(:caseidIn)")
    long updateCase_PropertyType(int PropertyType, String caseidIn);




}
