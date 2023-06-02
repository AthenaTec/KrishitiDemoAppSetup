package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.realappraiser.gharvalue.model.Document_list;

import java.util.List;

/**
 * Created by user on 15-02-2018.
 */

@Dao
public interface InterfaceDocumentListQuery {

    @Query("SELECT * FROM Document_list")
    List<Document_list> getDocument_list();

    @Query("SELECT * FROM Document_list where CaseId = (:caseid_)")
    List<Document_list> getDocument_list_caseID(int caseid_);

    @Insert
    void insertAll(Document_list document_list);

    @Query("DELETE FROM Document_list where CaseId = (:caseid_)")
    public void deleteRow(int caseid_);

    // Delete all rows
    @Query("DELETE FROM Document_list")
    public void deleteRow();



}
