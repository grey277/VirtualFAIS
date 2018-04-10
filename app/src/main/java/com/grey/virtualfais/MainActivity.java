package com.grey.virtualfais;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ImageView image;
    private ImageView image_mask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image);
        image_mask = findViewById(R.id.image_areas);
        image.setOnTouchListener(onTouchListener());
    }

    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        int x = (int) motionEvent.getX();
                        int y = (int) motionEvent.getY();
                        int touchColor = getColorXY(x, y);
                        if(closeMatch(Color.rgb(255, 0,0), touchColor, 25)) {
                            Toast.makeText(MainActivity.this, "RED!", Toast.LENGTH_SHORT).show();
                        }
                }
                return true;
            }
        };
    }

    private int getColorXY(int x, int y)
    {
        Bitmap viewBitmap = ((BitmapDrawable) image_mask.getDrawable()).getBitmap();
        return viewBitmap.getPixel(x, y);
    }

    private boolean closeMatch(int color1, int color2, int tolerance) {
        if(Math.abs(Color.red(color1) - Color.red(color2)) > tolerance)
            return false;
        if(Math.abs(Color.green(color1) - Color.green(color2)) > tolerance)
            return false;
        if(Math.abs(Color.blue(color1) - Color.blue(color2)) > tolerance)
            return false;

        return true;
    }
}