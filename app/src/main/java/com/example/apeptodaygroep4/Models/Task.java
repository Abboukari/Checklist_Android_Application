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
    @PrimaryKey public int uIdTask;
    @ColumnInfo public int uIdUser;
    @ColumnInfo public String Title;
    @ColumnInfo public String Discription;
    public Date dateTime;

}
