package com.grey.virtualfais.modules.update;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.grey.virtualfais.ProgressDialogManager;
import com.grey.virtualfais.R;
import com.grey.virtualfais.base.BaseFragment;


public class UpdateFragment extends BaseFragment {

    public final static String TAG = "UpdateFragment",
            TITLE = "UpdateFragment.TITLE",
            SUBTITLE = "UpdateFragment.SUBTITLE";
    private String title, subtitle;
    private Button updateButton;

    public static UpdateFragment newInstance(String title, String subtitle) {
        UpdateFragment result = new UpdateFragment();

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
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        bind(view);
        setupViews();

        return view;
    }

    private void bind(View view) {
        updateButton = view.findViewById(R.id.update_button);
    }

    private void setupViews() {
        updateButton.setOnClickListener(v -> {

            ProgressDialogManager.get().show(getContext());

            new Handler().postDelayed(() -> {
                ProgressDialogManager.get().dismiss();
                doBackground();
            }, 3000);
        });
    }

    private void doBackground() {}

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
