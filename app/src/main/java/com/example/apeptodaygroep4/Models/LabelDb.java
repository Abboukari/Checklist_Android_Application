package com.example.apeptodaygroep4.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LabelDb {
    @PrimaryKey (autoGenerate = true) int uIdLabel;
    @ColumnInfo String label;

    public LabelDb(){}
    public LabelDb(String label) {
        this.label = label;
    }

    public int getuIdLabel() {
        return uIdLabel;
    }

    public void setuIdLabel(int uIdLabel) {
        this.uIdLabel = uIdLabel;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
