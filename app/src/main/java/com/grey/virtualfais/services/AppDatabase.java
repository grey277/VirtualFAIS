package com.grey.virtualfais.services;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.grey.virtualfais.daos.EmployeeDao;
import com.grey.virtualfais.daos.LastupdateDao;
import com.grey.virtualfais.daos.RoomDao;
import com.grey.virtualfais.models.Employee;
import com.grey.virtualfais.models.Lastupdate;
import com.grey.virtualfais.models.Room;

import static android.arch.persistence.room.Room.databaseBuilder;

@Database(entities = {Employee.class, Room.class, Lastupdate.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "virtualfais.db";
    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(Context context) {
        return databaseBuilder(context, AppDatabase.class, DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public abstract EmployeeDao employeeDao();

    public abstract RoomDao roomDao();

    public abstract LastupdateDao lastupdateDao();
}
