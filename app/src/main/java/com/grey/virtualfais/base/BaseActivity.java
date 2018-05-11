package com.grey.virtualfais.base;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.LinkedList;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    public void onBackPressed() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        boolean isStateSaved = fragmentManager.isStateSaved();
        if(!isStateSaved || Build.VERSION.SDK_INT > 25) {
            if(isStateSaved || !fragmentManager.popBackStackImmediate()) {
                super.onBackPressed();
            }

        }
    }

    protected abstract Toolbar getToolbar();

    protected void clearToolbarText(){
        setToolbarTitle("", "");
    }

    private void setToolbarTitle(String title, String subtitle){
        if(title==null)
            getToolbar().setTitle(null);
        else
            getToolbar().setTitle(title);

        if(subtitle==null)
            getToolbar().setSubtitle(null);
        else
            getToolbar().setSubtitle(subtitle);
    }

    protected LinkedList<OnBackPressedListener> backPressedListeners =
            new LinkedList<>();

    public void registerBackPressListener(OnBackPressedListener backPressedListener){
        Log.d("backpresslistener", "register "+backPressedListener.getClass().getSimpleName());
        if(!isBackPressListenerRegistered(backPressedListener))
            backPressedListeners.addLast(backPressedListener);
    }

    public void unregisterBackPressListener(OnBackPressedListener backPressedListener){
        Log.d("backpresslistener", "unregister "+backPressedListener.getClass().getSimpleName());
        if(isBackPressListenerRegistered(backPressedListener))
            backPressedListeners.remove(backPressedListener);
    }

    public boolean isBackPressListenerRegistered(OnBackPressedListener backPressedListener){
        return backPressedListeners.contains(backPressedListener);
    }

    protected abstract int getFragmentContainerId();

    public void attachFragment(Fragment fragment, String tag, boolean addToBackStack){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(addToBackStack) {
            
            ft.addToBackStack(null);
            ft.add(getFragmentContainerId(), fragment, tag);
        } else {
            ft.replace(getFragmentContainerId(), fragment, tag);
        }

        ft.commit();
    }

    protected void attachFragment(Fragment fragment, String tag) {
        attachFragment(fragment, tag, false);
    }

    protected Fragment getFragmentIfExist(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        return fm.findFragmentByTag(tag);
    }

}
