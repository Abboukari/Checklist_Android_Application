package com.example.apeptodaygroep4.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class DoneTask implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer DoneUserIdTask;
    @ColumnInfo public Integer DoneUserIdUser;
    @ColumnInfo private String DoneTitle;
    @ColumnInfo private String DoneDescription;
    @ColumnInfo public String DoneUserIdLabel; //TODO: set to labelName


    public DoneTask(Integer doneUserIdTask, Integer doneUserIdUser, String doneTitle, String doneDescription, String doneUserIdLabel) {
        DoneUserIdTask = doneUserIdTask;
        DoneUserIdUser = doneUserIdUser;
        DoneTitle = doneTitle;
        DoneDescription = doneDescription;
        DoneUserIdLabel = doneUserIdLabel;
    }

    public DoneTask(){}

    public Integer getDoneUserIdTask() {
        return DoneUserIdTask;
    }

    public void setDoneUserIdTask(Integer doneUserIdTask) {
        DoneUserIdTask = doneUserIdTask;
    }

    public Integer getDoneUserIdUser() {
        return DoneUserIdUser;
    }

    public void setDoneUserIdUser(Integer doneUserIdUser) {
        DoneUserIdUser = doneUserIdUser;
    }

    public String getDoneTitle() {
        return DoneTitle;
    }

    public void setDoneTitle(String doneTitle) {
        DoneTitle = doneTitle;
    }

    public String getDoneDescription() {
        return DoneDescription;
    }

    public void setDoneDescription(String doneDescription) {
        DoneDescription = doneDescription;
    }

    public String getDoneUserIdLabel() {
        return DoneUserIdLabel;
    }

    public void setDoneUserIdLabel(String doneUserIdLabel) {
        DoneUserIdLabel = doneUserIdLabel;
    }

    @Override
    public String toString() {
        return "DoneTask{" +
                "DoneUserIdTask=" + DoneUserIdTask +
                ", DoneUserIdUser=" + DoneUserIdUser +
                ", DoneTitle='" + DoneTitle + '\'' +
                ", DoneDescription='" + DoneDescription + '\'' +
                ", DoneUserIdLabel='" + DoneUserIdLabel + '\'' +
                '}';
    }

}
