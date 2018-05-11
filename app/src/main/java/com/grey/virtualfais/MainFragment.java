package com.grey.virtualfais;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.grey.virtualfais.base.BaseFragment;


public class MainFragment extends MapViewFragment {

    private String title, subtitle;
    public final static String TAG = "MainFragment",
            TITLE = "MainFragment.TITLE",
            SUBTITLE = "MainFragment.SUBTITLE";

    private ImageView image;
    private ImageView imageMask;

    public static MainFragment newInstance() {
        MainFragment result = new MainFragment();

        Bundle bundle = new Bundle();
        result.setArguments(bundle);

        return result;
    }

    @Override
    public void bind(View view) {
//        image = view.findViewById(R.id.image);
//        imageMask = view.findViewById(R.id.image_areas);
    }

    @Override
    public void setupViews() {
        super.setupViews();
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
