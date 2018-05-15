package com.grey.virtualfais;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

import com.grey.virtualfais.daos.RoomDao;
import com.grey.virtualfais.models.Room;
import com.grey.virtualfais.services.AppDatabase;

public class DetectClick {

    private Bitmap mask;
    static private RoomDao roomDao;

    //TODO set rooms list
    DetectClick(Resources res, Context context)//getApplicationContext() //getResources()
    {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inTargetDensity = 1;
        bitmapOptions.inDensity = 1;
        bitmapOptions.inDither = false;
        bitmapOptions.inScaled = false;
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        mask = BitmapFactory.decodeResource(res, R.drawable.maska_parter, bitmapOptions);
        AppDatabase appDatabase = AppDatabase.getInstance(context);

        roomDao = appDatabase.roomDao();
    }

    private int getColor(int x, int y) {
        return mask.getPixel(x, y);
    }

    /**
     * Returns Room of closest clicked room.
     *
     * @param x global from top-left x-position of click
     * @param y global from top-left y-position of click
     * @param minDiff minimal difference of color to treat as different(should be less then 14)
     *
     * @return closest Room ID to clicked position, or null if not found any.
     */
    public Room getClosestRoom(int x, int y, int minDiff) {
        int color = getColor(x, y);
        Log.d("DetectClick", "Clicked on color: " + Color.red(color) + " " + Color.green(color) + " " + Color.blue(color) + " x/y: " + x + " " + y);
        return roomDao.getClosestByColor(Color.red(color), Color.green(color), Color.blue(color), minDiff);
    }

    /**
     * Returns Room of closest clicked room, default minDiff is set to 13.
     *
     * @param x global from top-left x-position of click
     * @param y global from top-left y-position of click
     *
     * @return closest Room ID to clicked position, or null if not found any.
     */
    public Room getClosestRoom(int x, int y)
    {
        return getClosestRoom(x, y, 13);
    }

}
