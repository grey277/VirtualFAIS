package com.grey.virtualfais.modules.help;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grey.virtualfais.R;
import com.grey.virtualfais.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends BaseFragment {

    public final static String TAG = "HelpFragment",
            TITLE = "HelpFragment.TITLE",
            SUBTITLE = "HelpFragment.SUBTITLE";
    private String title, subtitle;

    public static HelpFragment newInstance(String title, String subtitle) {
        HelpFragment result = new HelpFragment();

        Bundle bundle = new Bundle(2);
        bundle.putString(TITLE, title);
        bundle.putString(SUBTITLE, subtitle);
        result.setArguments(bundle);

        return result;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            if (title == null)
                this.title = getArguments().getString(TITLE, null);
            if (subtitle == null)
                this.subtitle = getArguments().getString(SUBTITLE, null);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        bind(view);
        setupViews();

        return view;
    }

    private void bind(View view) {

    }

    private void setupViews() {

    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubtitle() {
        return null;
    }

    @Override
    public void onBackOnScreen() {
        setToolbarTitle(getTitle(), getSubtitle());
        setArrow();
    }

    @Override
    public boolean onBackPressed() {
        return super.onBackPressed();
    }

}
