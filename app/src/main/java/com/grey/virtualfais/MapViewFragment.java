package com.grey.virtualfais;

import android.content.Intent;
import android.graphics.Rect;
import android.util.Log;

import com.grey.virtualfais.models.Room;
import com.qozix.tileview.TileView;
import com.qozix.tileview.hotspots.HotSpot;

public class MapViewFragment extends TileViewFragment {

    @Override
    public void setupViews() {
        super.setupViews();
        UpdateDatabase updateDatabase = new UpdateDatabase(getActivity().getApplicationContext());
        updateDatabase.testInsertRoom();
        DetectClick detectClick = new DetectClick(getResources(), getActivity().getApplicationContext());
        // multiple references
        TileView tileView = getTileView();
        HotSpot hotSpot = new HotSpot();
        hotSpot.setTag( "test" );
        hotSpot.set( new Rect( 0, 0, 9963, 6409 ) );
        hotSpot.setHotSpotTapListener(new HotSpot.HotSpotTapListener(){
            @Override
            public void onHotSpotTap(HotSpot hotSpot, int x, int y) {
                int scaledX = (int) (x / tileView.getScale());
                int scaledY = (int) (y / tileView.getScale());
                Room r = detectClick.getClosestRoom(scaledX, scaledY);
                startActivity(new Intent(getActivity(), PopupActivity.class));

                if(r != null)
                    Log.d( "HotSpotTapped", "With access through the tag API to the Activity " + r.getId() );
            }
        });
        tileView.addHotSpot(hotSpot);
        // let the image explode
        tileView.setScaleLimits( 0, 2 );

        // size of original image at 100% mScale
        tileView.setSize( 9963, 6409 );

        // detail levels
        tileView.addDetailLevel( 1.000f, "tiles/image/1000/%d_%d.jpg");
        tileView.addDetailLevel( 0.500f, "tiles/image/500/%d_%d.jpg");
        tileView.addDetailLevel( 0.250f, "tiles/image/250/%d_%d.jpg");
        tileView.addDetailLevel( 0.125f, "tiles/image/125/%d_%d.jpg");

        // set mScale to 0, but keep scaleToFit true, so it'll be as small as possible but still match the container
        tileView.setScale( 0 );


        // let's use 0-1 positioning...
        tileView.defineBounds( 0, 0, 1, 1 );

        // frame to center
        frameTo( 0.5, 0.5 );

        // render while panning
        tileView.setShouldRenderWhilePanning( true );

        // disallow going back to minimum scale while double-taping at maximum scale (for demo purpose)
        tileView.setShouldLoopScale( false );

    }
}
