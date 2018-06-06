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

    private String phoneNumber;

    private int colorRed;
    private int colorGreen;
    private int colorBlue;

    public Room(String id, int floor, String phoneNumber, int colorRed, int colorGreen, int colorBlue) {
        this.id = id;
        this.floor = floor;
        this.phoneNumber = phoneNumber;
        this.colorRed = colorRed;
        this.colorGreen = colorGreen;
        this.colorBlue = colorBlue;
    }

    public String getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getColorRed() { return colorRed; }

    public int getColorGreen() { return colorGreen; }

    public int getColorBlue() { return  colorBlue; }

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
                ", phoneNumber='" + phoneNumber + '\'' +
                ", colorRed=" + colorRed +
                ", colorGreen=" + colorGreen +
                ", colorBlue=" + colorBlue +
                '}';
    }
}
