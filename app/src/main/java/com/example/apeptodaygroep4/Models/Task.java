package com.example.apeptodaygroep4.Models;




import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.apeptodaygroep4.Models.LabelDb;
import com.example.apeptodaygroep4.Models.User;

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


public class Task{
    @PrimaryKey (autoGenerate = true) public Integer uIdTask;
    @ColumnInfo public Integer uIdUser;
    @ColumnInfo private String title;
    @ColumnInfo private String Description;
    @ColumnInfo public String labelName;
    @ColumnInfo private Boolean completed = false;
    @ColumnInfo public Integer uIdLabel;
    public Date dateTime;

    public Task(){}
    public Task(Integer uIdUser, String title, String description, Date dateTime) {
        this.uIdUser = uIdUser;
        Task.this.title = title;
        Description = description;
        this.completed = false;
        this.dateTime = dateTime;
    }

    public Task(String title, String label, Boolean completed) {
        this.title = title;
        this.labelName = label;
        this.completed = completed;
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
        Task.this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getlabelName() {
        return labelName;
    }

    public void setlabelName(String labelName) {
        this.labelName = labelName;
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
        return title;
    }

    public Integer getuIdLabel() {
        return uIdLabel;
    }

    public void setuIdLabel(Integer uIdLabel) {
        this.uIdLabel = uIdLabel;
    }
}
