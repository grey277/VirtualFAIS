package com.grey.virtualfais;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.grey.virtualfais.models.Room;

import java.util.LinkedList;
import java.util.List;

public class DetectClik {

    private Bitmap mask;
    private List<Room> rooms = new LinkedList<>();

    //TODO set rooms list
    DetectClik(Resources res)
    {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inTargetDensity = 1;
        bitmapOptions.inDensity = 1;
        bitmapOptions.inDither = false;
        bitmapOptions.inScaled = false;
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        mask = BitmapFactory.decodeResource(res, R.drawable.maskaParter, bitmapOptions);
    }

    private int getColor(int x, int y) {
        return mask.getPixel(x, y);
    }

    private boolean isClose(int color1, int color2, int tolerance) {
        if(Math.abs(Color.red(color1) - Color.red(color2)) > tolerance)
            return false;
        if(Math.abs(Color.green(color1) - Color.green(color2)) > tolerance)
            return false;
        if(Math.abs(Color.blue(color1) - Color.blue(color2)) > tolerance)
            return false;

        return true;
    }

    /**
     * Returns ID of closest clicked room.
     *
     * @param x global from top-left x-position of click
     * @param y global from top-left y-position of click
     * @param minDiff minimal difference of color to treat as different(should be less then 14)
     *
     * @return closest Room ID to clicked position, or null if not found any.
     */
    public String getClosestRoomID(int x, int y, int minDiff) {
        int color = getColor(x, y);
        for(Room room : rooms) {
            if(isClose(color, room.getColor(), minDiff)) {
                return room.getId();
            }
        }
        return null;
    }

    /**
     * Returns ID of closest clicked room, default minDiff is set to 13.
     *
     * @param x global from top-left x-position of click
     * @param y global from top-left y-position of click
     *
     * @return closest Room ID to clicked position, or null if not found any.
     */
    public String getClosestRoomID(int x, int y)
    {
        return getClosestRoomID(x, y, 13);
    }

}
