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
    private int currentLayer = 0;
    private float maskHeightScale = 1.f;
    private float maskWidthScale = 1.f;

    private RoomDao roomDao;
    private Resources res;
    private int mapWidth;
    private int mapHeight;

    DetectClick(Resources res, Context context, int mapWidth, int mapHeight)
    {
        this.res = res;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        roomDao = appDatabase.roomDao();
        loadMask(R.drawable.maska_parter);
    }


    private Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, resId, options);
    }

    private int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private int getColor(int x, int y) {
        int rescaledX = Math.round(x * maskWidthScale);
        int rescaledY = Math.round(y * maskHeightScale);
        Log.d("getColor", "Rescaled X/Y: " + rescaledX + " " + rescaledY + " Scales X/Y: " + maskWidthScale + " " + maskHeightScale);
        return mask.getPixel(rescaledX, rescaledY);
    }

    private void loadMask(int drawableID) {
        mask = decodeSampledBitmapFromResource(res, drawableID, mapWidth / 2, mapHeight / 2);
        final int height = mask.getHeight();
        final int width = mask.getWidth();
        Log.d("DetectClick", "Height: " + height + " width: " + width);
        maskWidthScale = (float) width / (float) mapWidth;
        maskHeightScale = (float) height / (float) mapHeight;

    }

    private void changeLayerIfNeeded(int layer) {
        switch(layer){
            case 0: if(currentLayer != 0) {
                mask.recycle();
                loadMask(R.drawable.maska_parter);
                currentLayer = 0;
            } break;
            case 1: if(currentLayer != 1) {
                mask.recycle();
                loadMask(R.drawable.pietro_1_maska);
                currentLayer = 1;
            } break;
            case 2: if(currentLayer != 2) {
                mask.recycle();
                loadMask(R.drawable.pietro_2_maska);
                currentLayer = 2;
            }
        }
    }

    public Room getClosestRoom(int x, int y, int minDiff, int layer) {
        int color = getColor(x, y);
        Log.d("DetectClick", "Clicked on color: " + Color.red(color) + " " + Color.green(color) + " " + Color.blue(color) + " x/y: " + x + " " + y);
        return roomDao.getByColor(Color.red(color), Color.green(color), Color.blue(color), minDiff);
    }

    public Room getClosestRoom(int x, int y, int layer)
    {
        changeLayerIfNeeded(layer);
        final int minimumDifferenceInColor = 8;
        return getClosestRoom(x, y, minimumDifferenceInColor, layer);
    }

}
