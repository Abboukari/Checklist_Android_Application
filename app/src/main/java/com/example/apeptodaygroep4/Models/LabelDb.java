package com.example.apeptodaygroep4.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LabelDb {
    @PrimaryKey int uIdLabel;
    @ColumnInfo String label;
}
