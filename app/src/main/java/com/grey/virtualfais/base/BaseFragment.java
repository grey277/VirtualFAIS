package com.grey.virtualfais.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.grey.virtualfais.MainActivity;


public abstract class BaseFragment extends Fragment implements DrawerFragmentInterface, OnFragmentBackOnScreen {

    private Context context;

    @Override
    public Context getContext() {
        return context != null ? context : super.getContext();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onBackOnScreen() {
        setToolbarTitle(getTitle(), getSubtitle());
    }
    @Override
    public boolean isBackFragment() {
        return true;
    }

    public void onBackStackChanged(boolean isOnTop){
        if(isOnTop)
            onBackOnScreen();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onBackOnScreen();
    }

    public void setToolbarTitle(String title, String subtitle) {
        ActionBar ab = ((AppCompatActivity) getContext()).getSupportActionBar();
        if (ab == null)
            return;

        if (title == null)
            ab.setTitle(null);
        else
            ab.setTitle(title);

        if (subtitle == null)
            ab.setSubtitle(null);
        else
            ab.setSubtitle(subtitle);
    }

    public void registerBackPressListener() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.registerBackPressListener(this);
        }
    }

    public void unregisterBackPressListener() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.unregisterBackPressListener(this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        registerBackPressListener();
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterBackPressListener();
    }

    public void setBurger() {
        if (getActivity() != null && getActivity() instanceof MainActivity) {
            MainActivity baseActivity = (MainActivity) getActivity();
            baseActivity.enableArrow(false);
        }
    }

    public void setArrow() {
        if (getActivity() != null && getActivity() instanceof MainActivity) {
            MainActivity baseActivity = (MainActivity) getActivity();
            baseActivity.enableArrow(true);
        }
    }
}
