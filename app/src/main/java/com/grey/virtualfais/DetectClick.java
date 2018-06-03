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
        mask = decodeSampledBitmapFromResource(res, drawableID, level.getPlanWidth() / 2, level.getPlanHeight() / 2);
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
        Log.d("DetectClick", "Clicked on color: " + Color.red(color) + " " + Color.green(color) + " " + Color.blue(color) + " x/y: " + x + " " + y);
        return roomDao.getByColor(Color.red(color), Color.green(color), Color.blue(color), minDiff);
    }

    public Room getClosestRoom(int x, int y, Level level) {
        changeLayerIfNeeded(level);
        final int minimumDifferenceInColor = 8;
        return getClosestRoom(x, y, minimumDifferenceInColor);
    }

}
