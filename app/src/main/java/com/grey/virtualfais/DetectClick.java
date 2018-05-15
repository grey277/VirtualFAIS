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

    public Room getClosestRoom(int x, int y, int minDiff) {
        int color = getColor(x, y);
        Log.d("DetectClick", "Clicked on color: " + Color.red(color) + " " + Color.green(color) + " " + Color.blue(color) + " x/y: " + x + " " + y);
        return roomDao.getByColor(Color.red(color), Color.green(color), Color.blue(color), minDiff);
    }

    public Room getClosestRoom(int x, int y)
    {
        final int minimumDifferenceInColor = 13;
        return getClosestRoom(x, y, minimumDifferenceInColor);
    }

}
