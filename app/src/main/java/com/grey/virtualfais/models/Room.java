package com.grey.virtualfais.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(tableName = "rooms")
public class Room {
    @NonNull
    @PrimaryKey
    private String id;

    private int floor;

    public Room(String id, int floor) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return floor == room.floor &&
                Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, floor);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", floor=" + floor +
                '}';
    }
}
