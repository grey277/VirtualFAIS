package com.grey.virtualfais.services;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.grey.virtualfais.models.Level;
import com.qozix.tileview.graphics.BitmapProvider;
import com.qozix.tileview.tiles.Tile;

import java.io.InputStream;
import java.util.Locale;

public class MapProvider implements BitmapProvider {
    private static final BitmapFactory.Options OPTIONS = new BitmapFactory.Options();

    static {
        OPTIONS.inPreferredConfig = Bitmap.Config.RGB_565;
    }

    private String basePath;

    public MapProvider(Level level) {
        this.basePath = level.getPath();
    }

    public void setLevel(Level level){
        this.basePath = level.getPath();
    }

    @Override
    public Bitmap getBitmap(Tile tile, Context context) {
        Object data = tile.getData();
        if (data instanceof String) {
            String unformattedFileName = basePath + tile.getData();
            String formattedFileName = String.format(Locale.US, unformattedFileName, tile.getColumn(), tile.getRow());
            AssetManager assetManager = context.getAssets();
            try {
                InputStream inputStream = assetManager.open(formattedFileName);
                if (inputStream != null) {
                    try {
                        return BitmapFactory.decodeStream(inputStream, null, OPTIONS);
                    } catch (OutOfMemoryError | Exception e) {
                        // this is probably an out of memory error - you can try sleeping (this method won't be called in the UI thread) or try again (or give up)
                        Thread.sleep(100);
                    }
                }
            } catch (Exception e) {
                // this is probably an IOException, meaning the file can't be found
            }
        }
        return null;
    }
}