package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by user on 26-02-2018.
 */

@Entity(tableName = "Document_list")

public class Document_list {

    @PrimaryKey(autoGenerate = true)
    public int dummyID;

    @ColumnInfo(name = "Id")
    public String Id;
    @ColumnInfo(name = "DocumentName")
    public String DocumentName;
    @ColumnInfo(name = "VisibleToFieldStaff")
    public String VisibleToFieldStaff;
    @ColumnInfo(name = "Title")
    public String Title;
    @ColumnInfo(name = "Document")
    public String Document;
    @ColumnInfo(name = "CaseId")
    public int CaseId;

    public int getCaseId() {
        return CaseId;
    }

    public void setCaseId(int caseId) {
        CaseId = caseId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDocumentName() {
        return DocumentName;
    }

    public void setDocumentName(String documentName) {
        DocumentName = documentName;
    }

    public String getVisibleToFieldStaff() {
        return VisibleToFieldStaff;
    }

    public void setVisibleToFieldStaff(String visibleToFieldStaff) {
        VisibleToFieldStaff = visibleToFieldStaff;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }


}
