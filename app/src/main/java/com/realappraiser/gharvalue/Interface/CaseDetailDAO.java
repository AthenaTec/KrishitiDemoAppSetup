package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.realappraiser.gharvalue.model.CaseDetail;

import java.util.List;

/**
 * Created by kaptas on 16/2/18.
 */

@Dao
public interface CaseDetailDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertCase(CaseDetail caseDetail);

    @Update
    public void updateCase(CaseDetail caseDetail);

    @Delete
    public void deleteCase(CaseDetail caseDetail);


    @Delete
    public void deleteAllCase(List<CaseDetail> getallDetails);

    @Query("SELECT * FROM casedetail")
    public List<CaseDetail> getallDetails();

    @Query("SELECT * FROM casedetail WHERE caseid=:caseidIn")
    public CaseDetail getCaseByID(String caseidIn);

    // Delete all rows
    @Query("DELETE FROM casedetail")
    public void deleteRow();

}

