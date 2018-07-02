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

    private final int maskSplitNumber = 256;
    private final int minimumDifferenceInColor = 8;
    private AssetManager assetManager;

    private int levelValue = 1;

    DetectClick(Resources res, Context context, Level level) {
        this.level = level;
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        roomDao = appDatabase.roomDao();
        assetManager = context.getAssets();
    }

    private void loadCorrectBitmap(int x, int y) {
        int xFloor = (int) Math.floor(x / maskSplitNumber);
        int multi = (int) Math.ceil(level.getPlanWidth() / maskSplitNumber) + 1; // images are from 0
        int yFloor = (int) Math.floor(y / maskSplitNumber);
        int bitmapNumber = (xFloor) + ((multi) *  yFloor);
        try {
            InputStream inputStream = assetManager.open("mask_floor_" + (levelValue - 1) + "/mask_"+ levelValue+"_" + bitmapNumber + ".png");
            mask = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {}
    }


    private int getColor(int x, int y) {
        loadCorrectBitmap(x, y);
        int xMod = x % maskSplitNumber;
        int yMod = y % maskSplitNumber;
        return mask.getPixel(xMod, yMod);
    }

    private void switchLevel(Level level) {
        switch(level.getId()) {
            case 0: levelValue = 1; break;
            case 1: levelValue = 2; break;
            case 2: levelValue = 3; break;
            default: levelValue = 1; break;

        }
        this.level = level;
    }

    public Room getClosestRoom(int x, int y, int minDiff) {
        int color = getColor(x, y);
        Log.d("DetectClick", "Clicked on color: " + Color.red(color) + " " + Color.green(color) + " " + Color.blue(color) + " x/y: " + x + ", " + y);
        return roomDao.getByColor(Color.red(color), Color.green(color), Color.blue(color), minDiff);
    }

    public Room getClosestRoom(int x, int y, Level level) {
        switchLevel(level);
        return getClosestRoom(x, y, minimumDifferenceInColor);
    }

}
