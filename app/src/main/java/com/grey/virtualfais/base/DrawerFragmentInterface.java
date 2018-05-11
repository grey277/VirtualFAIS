package com.grey.virtualfais.base;

public interface DrawerFragmentInterface {

    String getTitle();

    String getSubtitle();

    void setToolbarTitle(String title, String subtitle);

    void registerBackPressListener();

    void unregisterBackPressListener();

}
