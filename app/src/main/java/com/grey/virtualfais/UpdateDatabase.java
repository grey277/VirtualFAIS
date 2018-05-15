package com.grey.virtualfais;

import android.content.Context;
import android.graphics.Color;

import com.grey.virtualfais.daos.RoomDao;
import com.grey.virtualfais.models.Room;
import com.grey.virtualfais.services.AppDatabase;

public class UpdateDatabase {

    static private RoomDao roomDao;

    UpdateDatabase(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);

        roomDao = appDatabase.roomDao();
    }

    public void testInsertRoom() {
        int color = Color.parseColor("#000014");
        if(roomDao.getByRoomId("J-0-17") == null)
            roomDao.insert(new Room("J-0-17", 0, Color.red(color), Color.green(color), Color.blue(color)));
    }
}
