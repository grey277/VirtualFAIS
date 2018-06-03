package com.grey.virtualfais;


public class MainFragment extends MapViewFragment {

    public static final String TAG = "MainFragment",
            TITLE = "MainFragment.TITLE",
            SUBTITLE = "MainFragment.SUBTITLE";

    public static MainFragment newInstance() {
        return new MainFragment();
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
