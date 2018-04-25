package com.grey.virtualfais.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.grey.virtualfais.models.Room;

import java.util.List;

@Dao
public interface RoomDao {
    @Insert
    void insert(Room... obj);

    @Insert
    void insert(Room obj);

    @Query("SELECT * FROM rooms")
    List<Room> getAll();

    @Query("SELECT rooms.id, rooms.floor FROM rooms INNER JOIN employees " +
            "ON rooms.id = employees.roomId " +
            "WHERE rooms.id = employees.roomId " +
            "AND employees.id = :employeeId " +
            "LIMIT 1")
    Room getByEmployeeId(long employeeId);

    @Query("SELECT * FROM rooms WHERE rooms.id =:id ")
    Room get(String id);
}
