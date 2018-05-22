package com.grey.virtualfais.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.grey.virtualfais.models.Lastupdate;

@Dao
public interface LastupdateDao {
    @Insert
    long[] insert(Lastupdate... obj);

    @Insert
    long insert(Lastupdate obj);

    @Query("SELECT * FROM Lastupdate LIMIT 1")
    Lastupdate get();
}
