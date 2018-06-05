package com.grey.virtualfais;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.grey.virtualfais.modules.search.SearchFragment;

public class MainFragment extends MapViewFragment {

    private Activity activity;

    public static final String TAG = "MainFragment",
            TITLE = "MainFragment.TITLE",
            SUBTITLE = "MainFragment.SUBTITLE";

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        activity = getActivity();
        if (activity != null) {
            activity.getMenuInflater().inflate(R.menu.search_menu, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.right_search_btn) {
            if (activity != null) {
                MainActivity mainActivity = ((MainActivity)activity);
                mainActivity.currentFragment = getString(R.string.nav_search_title);
                mainActivity.runFragment();
            }
        }

        return false;
    }

    @Override
    public String getTitle() {
        return getString(R.string.app_name);
    }

    @Override
    public String getSubtitle() {
        return null;
    }

    @Override
    public void onBackOnScreen() {
        setToolbarTitle(getTitle(), getSubtitle());
        setBurger();
    }

}
