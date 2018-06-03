package com.grey.virtualfais;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grey.virtualfais.base.BaseFragment;
import com.qozix.tileview.TileView;

public class TileViewFragment extends BaseFragment {

    public TileView tileView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        tileView = new TileView(getContext());

        setupViews();

        /**
         * Set an id. This is necessary to enable the save state mechanism of Android.
         * It is retrieved from a resource value, but it can also be generated with
         * {@code View.generateViewId()}.
         */
        return tileView;
    }


    public void setupViews() {
        tileView.setId( R.id.tileview_id );
        tileView.setSaveEnabled( true );
    }

    @Override
    public void onPause() {
        super.onPause();
        tileView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        tileView.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tileView.destroy();
        tileView = null;
    }

    public TileView getTileView(){
        if (tileView != null) {
            return tileView;
        } else {
            tileView = new TileView(getContext());
            setupViews();
            return tileView;
        }
    }

    /**
     * This is a convenience method to scrollToAndCenter after layout (which won't happen if called directly in onCreate
     * see https://github.com/moagrius/TileView/wiki/FAQ
     */
    public void frameTo( final double x, final double y ) {
        getTileView().post(() -> getTileView().scrollToAndCenter( x, y ));
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSubtitle() {
        return null;
    }
}
