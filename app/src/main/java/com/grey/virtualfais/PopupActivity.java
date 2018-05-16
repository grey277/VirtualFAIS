package com.grey.virtualfais;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import com.grey.virtualfais.daos.RoomDao;
import com.grey.virtualfais.models.Room;
import com.grey.virtualfais.services.AppDatabase;

public class PopupActivity extends Activity {

    static private RoomDao roomDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup);

        // setting popup window size according to device screen size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.75), (int)(height*.75));

        String room_id = getIntent().getStringExtra("room_id");
        AppDatabase appDatabase = AppDatabase.getInstance(this);

        roomDao = appDatabase.roomDao();
        Room room = roomDao.getByRoomId(room_id);
    }
}
