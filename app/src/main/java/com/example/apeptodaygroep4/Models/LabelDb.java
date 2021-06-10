package com.example.apeptodaygroep4.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class LabelDb {
    @PrimaryKey (autoGenerate = true) Integer uIdLabel;
    @ColumnInfo String label;

    @Ignore
    public LabelDb(){}

    public LabelDb(String label) {
        this.label = label;
    }

    public Integer getuIdLabel() {
        return uIdLabel;
    }

    public void setuIdLabel(Integer uIdLabel) {
        this.uIdLabel = uIdLabel;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
