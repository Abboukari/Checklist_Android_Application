package com.example.apeptodaygroep4.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class LabelDb {
    @PrimaryKey @NonNull
    String uIdLabel;
    @ColumnInfo String label;

    @Ignore
    public LabelDb(){}

    public LabelDb(String label) {
        this.label = label;
    }

    public String getuIdLabel() {
        return uIdLabel;
    }

    public void setuIdLabel(String uIdLabel) {
        this.uIdLabel = uIdLabel;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
