package com.anuj.pocjava;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.anuj.pocjava.log.Logger;
import com.anuj.pocjava.util.Utility;
import com.squareup.picasso.Picasso;

public class Binding {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Logger.debugLog("URL to be loaded = " + url);

        if(Utility.isValidURL(url))
            Picasso.get().load(url).placeholder(R.drawable.ic_image_black_24dp).error(R.drawable.ic_image_black_24dp).into(view);
        else
            view.setImageResource(R.drawable.ic_image_black_24dp);
    }
}
