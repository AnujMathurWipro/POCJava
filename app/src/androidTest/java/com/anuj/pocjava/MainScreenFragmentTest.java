package com.anuj.pocjava;

import android.Manifest;
import android.content.Context;
import android.net.wifi.WifiManager;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.platform.content.PermissionGranter;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.anuj.pocjava.log.Logger;
import com.anuj.pocjava.ui.MainActivity;
import com.anuj.pocjava.ui.fragment.MainScreenFragment;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class MainScreenFragmentTest {

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.CHANGE_WIFI_STATE);
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    class SimpleIdlingRes implements IdlingResource {
        private boolean idle = false;
        ResourceCallback callback;


        void setIdle(boolean idle) {
            Logger.debugLog("setIdle " + idle);
            this.idle = idle;
            if(idle && callback != null)
                callback.onTransitionToIdle();
        }

        @Override
        public String getName() {
            return "TestName";
        }

        @Override
        public boolean isIdleNow() {
            return idle;
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback callback) {
            this.callback = callback;
        }
    }

    private SimpleIdlingRes mIdlingResource;

    @Before
    public void setUp() {
        mIdlingResource = new SimpleIdlingRes();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }
    // Remember to unregister resources when not needed to avoid malfunction.
    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }

    @Test
    public void testCorrect() {
        MainScreenFragment.IdlingCallback cb = () -> mIdlingResource.setIdle(true);
        MainScreenFragmentFactory factory = new MainScreenFragmentFactory(cb);
        mIdlingResource.registerIdleTransitionCallback(() -> Logger.debugLog("Now went idle"));

        FragmentScenario.launchInContainer(MainScreenFragment.class, null, factory);

        Espresso.onView(ViewMatchers.withId(R.id.tv_errorText))
                .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())));
        Espresso.onView(ViewMatchers.withId(R.id.rvItemList))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.rvItemList))
                .check(ViewAssertions.matches(CustomMatchers.withItemCount(14)));
        Espresso.onView(ViewMatchers.withId(R.id.rvItemList))
                .check(CustomAssertions.hasItemCount(14));
    }

    @Test
    public void testNoInternet() {
        WifiManager wifi = (WifiManager) activityRule.getActivity().getSystemService(Context.WIFI_SERVICE);
        wifi.disconnect();
        MainScreenFragment.IdlingCallback cb = () -> mIdlingResource.setIdle(true);
        MainScreenFragmentFactory factory = new MainScreenFragmentFactory(cb);
        mIdlingResource.registerIdleTransitionCallback(() -> Logger.debugLog("Now went idle"));

        FragmentScenario.launchInContainer(MainScreenFragment.class, null, factory);

        Espresso.onView(ViewMatchers.withId(R.id.tv_errorText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.rvItemList))
                .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())));
        Espresso.onView(ViewMatchers.withId(R.id.rvItemList))
                .check(ViewAssertions.matches(CustomMatchers.withItemCount(0)));
    }
}
