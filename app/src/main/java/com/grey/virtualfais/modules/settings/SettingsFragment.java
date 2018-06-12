package com.grey.virtualfais.modules.settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.grey.virtualfais.MainActivity;
import com.grey.virtualfais.R;
import com.grey.virtualfais.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends BaseFragment{

    public final static String TAG = "SettingsFragment",
            TITLE = "SettingsFragment.TITLE",
            SUBTITLE = "SettingsFragment.SUBTITLE";
    private String title, subtitle;
    private Button plFlag;
    private Button enFlag;


    public static SettingsFragment newInstance(String title, String subtitle) {
        SettingsFragment result = new SettingsFragment();

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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        plFlag = view.findViewById(R.id.polish_lang);
        enFlag = view.findViewById(R.id.english_lang);

        plFlag.setOnClickListener(v -> ((MainActivity) getActivity()).setLanguage("pl"));

        enFlag.setOnClickListener(v -> ((MainActivity) getActivity()).setLanguage("en"));
        return view;
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
