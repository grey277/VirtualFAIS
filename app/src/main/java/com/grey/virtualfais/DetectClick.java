package com.grey.virtualfais;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

import com.grey.virtualfais.daos.RoomDao;
import com.grey.virtualfais.models.Level;
import com.grey.virtualfais.models.Room;
import com.grey.virtualfais.services.AppDatabase;

public class DetectClick {

    private Bitmap mask;
    private Level level;
    private float maskHeightScale = 1.f;
    private float maskWidthScale = 1.f;

    private RoomDao roomDao;
    private Resources res;

    DetectClick(Resources res, Context context, Level level) {
        this.res = res;
        this.level = level;
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        roomDao = appDatabase.roomDao();
        loadMask(level.getMaskId());
    }

    private Bitmap decodeSampledBitmapFromResource(Resources res, int resId) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = 8;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDither = true;

        return BitmapFactory.decodeResource(res, resId, options);
    }


    private int getColor(int x, int y) {
        int rescaledX = Math.round(x * maskWidthScale);
        int rescaledY = Math.round(y * maskHeightScale);
        Log.d("getColor", "Rescaled X/Y: " + rescaledX + " " + rescaledY + " Scales X/Y: " + maskWidthScale + " " + maskHeightScale);
        return mask.getPixel(rescaledX, rescaledY);
    }

    private void loadMask(int drawableID) {
        mask = decodeSampledBitmapFromResource(res, drawableID);
        final int height = mask.getHeight();
        final int width = mask.getWidth();
        Log.d("DetectClick", "Height: " + height + " width: " + width);
        maskWidthScale = (float) width / (float) level.getPlanWidth();
        maskHeightScale = (float) height / (float) level.getPlanHeight();

    }

    private void changeLayerIfNeeded(Level level) {
        if (!this.level.equals(level)) {
            mask.recycle();
            loadMask(level.getMaskId());
            this.level = level;
        }
    }

    public Room getClosestRoom(int x, int y, int minDiff) {
        int color = getColor(x, y);
        Log.d("DetectClick", "Clicked on color: " + Color.red(color) + " " + Color.green(color) + " " + Color.blue(color) + " x/y: " + x + ", " + y);
        return roomDao.getByColor(Color.red(color), Color.green(color), Color.blue(color), minDiff);
    }

    public Room getClosestRoom(int x, int y, Level level) {
        changeLayerIfNeeded(level);
        final int minimumDifferenceInColor = 8;
        return getClosestRoom(x, y, minimumDifferenceInColor);
    }

}
