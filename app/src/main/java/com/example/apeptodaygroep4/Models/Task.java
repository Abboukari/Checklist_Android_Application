package com.example.apeptodaygroep4.Models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity
public class Task {
    @PrimaryKey int uIdTask;
    @ColumnInfo int uIdUser;
    @ColumnInfo String Titel;
    @ColumnInfo String Discription;
    @ColumnInfo LocalDateTime dateTime;

}
