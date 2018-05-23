package com.grey.virtualfais.daos;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.grey.virtualfais.models.Lastupdate;
import com.grey.virtualfais.services.AppDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(AndroidJUnit4.class)
public class LastupdateDaoTest {
    private AppDatabase database;
    private LastupdateDao lastupdateDao;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        lastupdateDao = database.lastupdateDao();
    }

    @After
    public void closeDb() {
        database.close();
    }

    @Test
    public void shouldInsertLastupdate() {
        // given
        Long updateID = 123321123L;
        Lastupdate update = new Lastupdate(updateID);
        lastupdateDao.insert(update);

        // when
        Lastupdate updateGet = lastupdateDao.get();

        // then
        assertThat(updateGet).isEqualTo(update);
    }



}
