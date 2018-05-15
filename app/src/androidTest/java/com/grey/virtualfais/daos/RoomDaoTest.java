package com.grey.virtualfais.daos;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.grey.virtualfais.helpers.EmployeeFactory;
import com.grey.virtualfais.models.Department;
import com.grey.virtualfais.models.Employee;
import com.grey.virtualfais.models.Name;
import com.grey.virtualfais.models.Room;
import com.grey.virtualfais.services.AppDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(AndroidJUnit4.class)
public class RoomDaoTest {
    private AppDatabase database;
    private RoomDao roomDao;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        database = android.arch.persistence.room.Room
                .inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        roomDao = database.roomDao();
    }

    @After
    public void closeDb() throws IOException {
        database.close();
    }

    @Test
    public void shouldInsertRoom() {
        // given
        Room A_1_06 = new Room("A-1-06", 1);
        roomDao.insert(A_1_06);

        // when
        List<Room> allRooms = roomDao.getAll();
        Room room = allRooms.get(0);

        // then
        assertThat(allRooms).containsOnly(A_1_06);
        assertThat(room.getId()).isEqualTo("A-1-06");
        assertThat(room.getFloor()).isEqualTo(1);
    }

    @Test
    public void shouldInsertMultipleRooms() {
        // given
        Room A_1_06 = new Room("A-1-06", 1);
        Room H_2_22 = new Room("H-2-22", 2);

        roomDao.insert(A_1_06, H_2_22);

        // when
        List<Room> allRooms = roomDao.getAll();

        // then
        assertThat(allRooms).containsOnly(A_1_06, H_2_22);
    }

    @Test
    public void shouldSearchByEmployeeId() {
        // given
        Room A_1_06 = new Room("A-1-06", 1);
        Employee A_1_06_employee = EmployeeFactory.createEmployee("Jan", "Kowalski", A_1_06.getId());

        roomDao.insert(A_1_06);
        long employeeId = database.employeeDao().insert(A_1_06_employee);

        // when
        Room room = roomDao.getByEmployeeId(employeeId);

        // then
        assertThat(room).isEqualTo(A_1_06);
    }
}
