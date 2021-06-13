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
        @ForeignKey(
                entity = LabelDb.class,
                parentColumns = "uIdLabel",
                childColumns = "uIdLabel"
        )
        })


public class Task {
    @PrimaryKey (autoGenerate = true) public Integer uIdTask;
    @ColumnInfo public Integer uIdUser;
    @ColumnInfo private String Title;
    @ColumnInfo private String Description;
    @ColumnInfo public String uIdLabel; //TODO: set to labelName
    @ColumnInfo private Boolean completed;
    public Date dateTime;

    public Task(){}

    public Task(Integer uIdUser, String title, String description, Date dateTime) {
        this.uIdUser = uIdUser;
        Title = title;
        Description = description;
        this.completed = false;
        this.dateTime = dateTime;
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
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getuIdLabel() {
        return uIdLabel;
    }

    public void setuIdLabel(String uIdLabel) {
        this.uIdLabel = uIdLabel;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return Title;
    }

    public String detailsToString() {
        return "Task" +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", uIdLabel='" + uIdLabel + '\'' +
                ", completed=" + completed +
                ", dateTime=" + dateTime +
                '}';
    }
}
