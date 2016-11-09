package com.app.vipin.taskcreator;

/**
 * Created by vipin on 8/11/16.
 */
public class Task {
    private String title,detail;
    private int id,status;

    public Task() {
    }
    public Task( String title, String detail, int status) {
        this.detail = detail;
        this.status = status;
        this.title = title;
    }
    public Task(int id, String title ,String detail,  int status) {
        this.id = id;
        this.detail = detail;
        this.status = status;
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

