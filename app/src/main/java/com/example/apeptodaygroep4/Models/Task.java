package com.example.apeptodaygroep4.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

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


public class Task implements Serializable {
    @PrimaryKey (autoGenerate = true) public Integer uIdTask;
    @ColumnInfo public Integer uIdUser;
    @ColumnInfo private String title;
    @ColumnInfo private String description;
    @ColumnInfo public String uIdLabel; //TODO: set to labelName
    public Date dateTime;

    public Task(){}

    public Task(Integer uIdUser, String title, String description, Date dateTime) {
        this.uIdUser = uIdUser;
        this.title = title;
        this.description = description;
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

    public String getuIdLabel() {
        return uIdLabel;
    }

    public void setuIdLabel(String uIdLabel) {
        this.uIdLabel = uIdLabel;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void editTask(Integer uIdTask, String  title,String description,Date dateTime){
        this.uIdTask = uIdTask;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return title;
    }

    public String detailsToString() {
        return "Task" +
                "Title: " + title + "\n\n" +
                "Description: " + description + "\n\n" +
                "Label: " + uIdLabel + "\n\n" +
                "Date and Time: " + dateTime;
    }
}
