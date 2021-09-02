package com.app.spotaccident.model;

import java.io.Serializable;
import java.util.List;

public class DataPojo implements Serializable {
    private String title;
    private List<TypePojo> type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public List<TypePojo> getType() {
        return type;
    }

    public void setType(List<TypePojo> value) {
        this.type = value;
    }
}
