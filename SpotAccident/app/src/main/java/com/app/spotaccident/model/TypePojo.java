package com.app.spotaccident.model;
import java.io.Serializable;

public class TypePojo implements Serializable {
    private String title;
    private String path;

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String value) {
        this.path = value;
    }
}

