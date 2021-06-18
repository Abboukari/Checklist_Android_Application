package com.example.apeptodaygroep4.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(
        entity = User.class,
        parentColumns = "id",
        childColumns = "doneUserIdUser"),
})
public class DoneTask implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer doneUserIdTask;
    @ColumnInfo
    public Integer doneUserIdUser;
    @ColumnInfo
    private String doneTitle;
    @ColumnInfo
    private String doneDescription;
    @ColumnInfo
    public String doneUserIdLabel; //TODO: set to labelName
    public Date dateTime;



    public DoneTask() {
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getDoneUserIdTask() {
        return doneUserIdTask;
    }

    public void setDoneUserIdTask(Integer doneUserIdTask) {
        this.doneUserIdTask = doneUserIdTask;
    }

    public Integer getDoneUserIdUser() {
        return doneUserIdUser;
    }

    public void setDoneUserIdUser(Integer doneUserIdUser) {
        this.doneUserIdUser = doneUserIdUser;
    }

    public String getDoneTitle() {
        return doneTitle;
    }

    public void setDoneTitle(String doneTitle) {
        this.doneTitle = doneTitle;
    }

    public String getDoneDescription() {
        return doneDescription;
    }

    public void setDoneDescription(String doneDescription) {
        this.doneDescription = doneDescription;
    }

    public String getDoneUserIdLabel() {
        return doneUserIdLabel;
    }

    public void setDoneUserIdLabel(String doneUserIdLabel) {
        this.doneUserIdLabel = doneUserIdLabel;
    }

    @NotNull
    @Override
    public String toString() {
        return doneTitle;
    }

}
