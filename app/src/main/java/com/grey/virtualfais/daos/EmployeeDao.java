package com.grey.virtualfais.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.grey.virtualfais.models.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert
    long[] insert(Employee... obj);

    @Insert
    long insert(Employee obj);

    @Query("SELECT * FROM Employees")
    List<Employee> getAll();


    // select employee in specific room
    @Query("SELECT * FROM employees WHERE employees.roomId =:id ")
    List<Employee> getEmployeesByRoomId(String id);


    @Query("SELECT * FROM employees " +
            "WHERE employees.firstName || ' ' || employees.lastName LIKE :query " +
            "OR employees.lastName || ' ' || employees.firstName LIKE :query")
    List<Employee> searchSqlQuery(String query);

    default List<Employee> search(String query) {
        return searchSqlQuery(query + "%");
    }
}
