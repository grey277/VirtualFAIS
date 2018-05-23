package com.grey.virtualfais.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "lastupdate")
public class Lastupdate {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private long updateID;

    public Lastupdate(long updateID) {
        this.updateID = updateID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUpdateID() { return updateID; }

    public void setUpdateID(Long updateID) { this.updateID = updateID; }

    @Override
    public boolean equals(Object o) {
        Lastupdate lastupdate = (Lastupdate) o;
        return Objects.equals(updateID, lastupdate.updateID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(updateID);
    }

    @Override
    public String toString() {
        return String.valueOf(updateID);
    }
}
