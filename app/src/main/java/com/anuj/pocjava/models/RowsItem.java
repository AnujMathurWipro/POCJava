package com.anuj.pocjava.models;

public class RowsItem {
    private String imageHref;
    private String description;
    private String title;

    public RowsItem(String imageHref, String description, String title) {
        this.imageHref = imageHref;
        this.description = description;
        this.title = title;
    }

    public String getImageHref() {
        return imageHref;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
