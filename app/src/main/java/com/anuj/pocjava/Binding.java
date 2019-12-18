package com.anuj.pocjava;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.anuj.pocjava.log.Logger;
import com.squareup.picasso.Picasso;

public class Binding {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Logger.debugLog("URL to be loaded = " + url);
        Picasso.get().load(url).into(view);
    }
}
