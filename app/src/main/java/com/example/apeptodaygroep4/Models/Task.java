package com.example.apeptodaygroep4.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(foreignKeys = {
        @ForeignKey(
        entity = User.class,
        parentColumns = "id",
        childColumns = "uIdUser"),
        @ForeignKey(
                entity = Label.class,
                parentColumns = "labelId",
                childColumns = "uIdLabel")
})


public class Task implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public Integer uIdTask;
    @ColumnInfo
    public Integer uIdUser;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String description;
    @ColumnInfo
    public Integer uIdLabel;
    public Date dateTime;
    public boolean done;

    public Task(Integer uIdTask, Integer uIdUser, String title, String description, Integer uIdLabel, Date dateTime) {
        this.uIdTask = uIdTask;
        this.uIdUser = uIdUser;
        this.title = title;
        this.description = description;
        this.uIdLabel = uIdLabel;
        this.dateTime = dateTime;
    }

    public Task() {
    }

    public Task(Integer uIdUser, String title, String description, Date dateTime) {
        this.uIdUser = uIdUser;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
    }


    public void editTask(Integer uIdTask, String title, String description, Date dateTime, Integer uIdLabel) {
        this.uIdTask = uIdTask;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.uIdLabel = uIdLabel;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Integer getuIdTask() {
        return uIdTask;
    }

    public void setuIdTask(Integer uIdTask) {
        this.uIdTask = uIdTask;
    }

    public Integer getuIdUser() {
        return uIdUser;
    }

    public void setuIdUser(Integer uIdUser) {
        this.uIdUser = uIdUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getuIdLabel() {
        return uIdLabel;
    }

    public void setuIdLabel(Integer uIdLabel) {
        this.uIdLabel = uIdLabel;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }


    @NotNull
    @Override
    public String toString() {
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.MEDIUM, Locale.GERMAN);
        String dateAndTime = dateFormat.format(dateTime);

        return title + "    |     " + dateAndTime;
    }


}
