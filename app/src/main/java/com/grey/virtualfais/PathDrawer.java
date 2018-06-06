package com.grey.virtualfais;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.qozix.tileview.TileView;
import com.qozix.tileview.paths.CompositePathView;

public class PathDrawer {

    private TileView tileView;
    private CompositePathView.DrawablePath hotSpotPath;
    private Path path;

    PathDrawer(TileView tileView) {
        this.tileView = tileView;

        hotSpotPath = new CompositePathView.DrawablePath();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLUE);
        hotSpotPath.paint = paint;
    }

    public void newPath(int x, int y) {
        path = new Path();
        path.moveTo(x, y);
    }

    public void nextPoint(int x, int y) {
        path.lineTo(x, y);
    }

    public void endPath() {
        hotSpotPath.path = path;
        tileView.drawPath(hotSpotPath);
    }

    public void clearPath() {
        tileView.removePath(hotSpotPath);
    }

}
