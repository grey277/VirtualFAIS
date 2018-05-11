package com.grey.virtualfais.base;


public interface OnFragmentBackOnScreen extends OnBackPressedListener {

    boolean onBackPressed();

    void onBackStackChanged(boolean isOnTop);

    void onBackOnScreen();

    boolean isBackFragment();

}
