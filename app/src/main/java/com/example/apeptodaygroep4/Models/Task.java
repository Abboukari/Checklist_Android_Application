package com.example.apeptodaygroep4.Models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(
        entity = User.class,
        parentColumns = "id",
        childColumns = "uIdUser"),
        })
public class Task {
    @PrimaryKey int uIdTask;
    @ColumnInfo int uIdUser;
    @ColumnInfo String Titel;
    @ColumnInfo String Discription;
    Date dateTime;

}
