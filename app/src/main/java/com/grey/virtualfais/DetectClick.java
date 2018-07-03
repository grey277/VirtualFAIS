package com.grey.virtualfais;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

import com.grey.virtualfais.daos.RoomDao;
import com.grey.virtualfais.models.Level;
import com.grey.virtualfais.models.Room;
import com.grey.virtualfais.services.AppDatabase;

import java.io.IOException;
import java.io.InputStream;

public class DetectClick {

    private Bitmap mask;
    private Level level;

    private RoomDao roomDao;

    static private final int MASK_SPLIT_NUMBER = 256;
    static private final int MINIMUM_DIFFERENCE_IN_COLOR = 8;
    private AssetManager assetManager;

    DetectClick(Context context, Level level) {
        this.level = level;
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        roomDao = appDatabase.roomDao();
        assetManager = context.getAssets();
    }

    private void loadCorrectBitmap(int x, int y) {
        int xFloor = x / MASK_SPLIT_NUMBER;
        int multi = level.getPlanWidth() / MASK_SPLIT_NUMBER + 1; // images are from 0
        int yFloor = y / MASK_SPLIT_NUMBER;
        int bitmapNumber = (xFloor) + ((multi) *  yFloor);
        try {
            InputStream inputStream = assetManager.open("mask_floor_" + level.getId() + "/mask_" + bitmapNumber + ".png");
            mask = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            Log.d("DetectClick", "Cant find image: " + "mask_floor_" + level.getId() + "/mask_" + bitmapNumber + ".png");
        }
    }


    private int getColor(int x, int y) {
        loadCorrectBitmap(x, y);
        int xMod = x % MASK_SPLIT_NUMBER;
        int yMod = y % MASK_SPLIT_NUMBER;
        return mask.getPixel(xMod, yMod);
    }

    public Room getClosestRoom(int x, int y, int minDiff) {
        int color = getColor(x, y);
        Log.d("DetectClick", "Clicked on color: " + Color.red(color) + " " + Color.green(color) + " " + Color.blue(color) + " x/y: " + x + ", " + y);
        return roomDao.getByColor(Color.red(color), Color.green(color), Color.blue(color), minDiff);
    }

    public Room getClosestRoom(int x, int y, Level level) {
        this.level = level;
        return getClosestRoom(x, y, MINIMUM_DIFFERENCE_IN_COLOR);
    }

}
