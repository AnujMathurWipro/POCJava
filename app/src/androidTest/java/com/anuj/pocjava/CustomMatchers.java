package com.anuj.pocjava;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

class CustomMatchers {
    public static Matcher<View> withItemCount(int count) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("RecyclerView with item count: $count");
            }

            @Override
            public boolean matchesSafely(RecyclerView item) {
                return item.getAdapter().getItemCount() == count;
            }
        };
    }
}