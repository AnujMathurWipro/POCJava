package com.anuj.pocjava;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class Binding {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Log.d("BindingAdapter", "URL to be loaded = " +url);
        Picasso.get().load(url).into(view);
    }
}
