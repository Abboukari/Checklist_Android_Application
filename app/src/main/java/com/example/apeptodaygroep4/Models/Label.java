package com.example.apeptodaygroep4.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Label implements Serializable {

    @PrimaryKey (autoGenerate = true) private int labelId;
    @ColumnInfo private int userId;
    @ColumnInfo private String labelName;

    public Label(int userId, String labelName) {
        this.labelName = labelName;
        this.userId = userId;
    }

    public Label(){}

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public void updateLabel(int labelId, int userId ,String labelName){
        this.labelId = labelId;
        this.userId = userId;
        this.labelName = labelName;
    }

    @Override
    public String toString() {
        return labelName;
    }
}
