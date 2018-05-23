package com.grey.virtualfais.modules.search;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.grey.virtualfais.R;
import com.grey.virtualfais.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends BaseFragment {

    public final static String TAG = "SearchFragment",
            TITLE = "SearchFragment.TITLE",
            SUBTITLE = "SearchFragment.SUBTITLE";
    private String title, subtitle;
    private AutoCompleteTextView autoCompleteTextView;
    private Button navigate;

    public static SearchFragment newInstance(String title, String subtitle) {
        SearchFragment result = new SearchFragment();

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
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        bind(view);
        setupViews();

        return view;
    }

    private void bind(View view) {
        autoCompleteTextView = view.findViewById(R.id.auto_text_view);
        navigate = view.findViewById(R.id.confirm);
    }

    private void setupViews() {
        // TODO: pass catchword list
        List<String> catchwordsList = new ArrayList<>();
        catchwordsList.add("Test A");
        catchwordsList.add("Test B");
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.select_dialog_item, catchwordsList);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);

        navigate.setOnClickListener(v -> {
            //TODO: make some action
        });
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
