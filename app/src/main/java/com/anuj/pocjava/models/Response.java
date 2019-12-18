package com.anuj.pocjava.models;

import java.util.List;

public class Response {
    private String title;
    private List<RowsItem> rows;

    public String getTitle() {
        return title;
    }

    public List<RowsItem> getRows() {
        return rows;
    }
}
