package com.anuj.pocjava.models;

import androidx.annotation.Nullable;

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

    @Override
    public boolean equals(@Nullable Object obj) {
        if (super.equals(obj))
            return true;
        else if (obj instanceof RowsItem) {
            RowsItem i = (RowsItem) obj;
            return this.title.equals(i.title) && this.description.equals(i.description) && this.imageHref.equals(i.imageHref);
        } else
            return false;
    }
}
