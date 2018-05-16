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
    private float maskHeightScale = 1.f;
    private float maskWidthScale = 1.f;

    static private RoomDao roomDao;

    DetectClick(Resources res, Context context, int maskWidth, int maskHeight)
    {
        mask = decodeSampledBitmapFromResource(res, R.drawable.maska_parter, maskWidth, maskHeight);
        final int height = mask.getHeight();
        final int width = mask.getWidth();
        Log.d("DetectClick", "Height: " + height + " width: " + width);
        maskWidthScale = (float) width / (float) maskWidth;
        maskHeightScale = (float) height / (float) maskHeight;

        AppDatabase appDatabase = AppDatabase.getInstance(context);

        roomDao = appDatabase.roomDao();
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
