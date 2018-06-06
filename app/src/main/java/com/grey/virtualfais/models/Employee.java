package com.grey.virtualfais.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "employees")
public class Employee {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @Embedded
    private Name name;

    @Embedded
    private Department department;

    @ForeignKey(entity = Room.class, parentColumns = "id", childColumns = "roomId")
    private String roomId;

    private String telephone;

    public Employee(Name name, Department department, String roomId, String telephone) {
        this.name = name;
        this.department = department;
        this.roomId = roomId;
        this.telephone = telephone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public Name getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public String getTelephone() { return telephone; }

    public void setTelephone(String telephone) { this.telephone = telephone; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(department, employee.department) &&
                Objects.equals(roomId, employee.roomId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, department, roomId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name=" + name +
                ", department=" + department +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
