package com.grey.virtualfais.models;

import java.util.Objects;

public class Department {
    private String departmentShortName;

    private String departmentLongName;

    public Department(String departmentShortName, String departmentLongName) {
        this.departmentShortName = departmentShortName;
        this.departmentLongName = departmentLongName;
    }

    public String getDepartmentShortName() {
        return departmentShortName;
    }

    public void setDepartmentShortName(String departmentShortName) {
        this.departmentShortName = departmentShortName;
    }

    public String getDepartmentLongName() {
        return departmentLongName;
    }

    public void setDepartmentLongName(String departmentLongName) {
        this.departmentLongName = departmentLongName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(departmentShortName, that.departmentShortName) &&
                Objects.equals(departmentLongName, that.departmentLongName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(departmentShortName, departmentLongName);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentShortName='" + departmentShortName + '\'' +
                ", departmentLongName='" + departmentLongName + '\'' +
                '}';
    }
}
