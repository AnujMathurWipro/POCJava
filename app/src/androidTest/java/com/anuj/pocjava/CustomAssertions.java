package com.anuj.pocjava;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.CoreMatchers;

class CustomAssertions {
    public static ViewAssertion hasItemCount(int count) {
        return new RecyclerViewItemCountAssertion(count);
    }

    private static class RecyclerViewItemCountAssertion implements ViewAssertion {

        private int count;

        RecyclerViewItemCountAssertion(int count) {
            this.count = count;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            if (!(view instanceof RecyclerView)) {
                throw new IllegalStateException("The asserted view is not RecyclerView");
            }
            RecyclerView rv = (RecyclerView) view;
            if (rv.getAdapter() == null) {
                throw new IllegalStateException("No adapter is assigned to RecyclerView");
            }

            ViewMatchers.assertThat("RecyclerView item count", rv.getAdapter().getItemCount(), CoreMatchers.equalTo(count));
        }
    }
}