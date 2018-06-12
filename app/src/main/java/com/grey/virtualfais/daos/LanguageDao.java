package com.grey.virtualfais.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.grey.virtualfais.models.Language;

@Dao
public interface LanguageDao {

    @Insert
    long insert(Language obj);

    @Query("SELECT * FROM languages LIMIT 1")
    Language getLanguage();


    @Query("DELETE FROM languages")
    void deleteAllRecords();
}
