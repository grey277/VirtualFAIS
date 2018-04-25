package com.grey.virtualfais.daos;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.grey.virtualfais.models.Employee;
import com.grey.virtualfais.services.AppDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.grey.virtualfais.helpers.EmployeeFactory.createEmployee;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(AndroidJUnit4.class)
public class EmployeeDaoTest {
    private AppDatabase database;
    private EmployeeDao employeeDao;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        employeeDao = database.employeeDao();
    }

    @After
    public void closeDb() {
        database.close();
    }

    @Test
    public void shouldInsertEmployee() {
        // given
        Employee employee = createEmployee("Jan", "Kowalski");
        employeeDao.insert(employee);

        // when
        List<Employee> allEmployees = employeeDao.getAll();

        // then
        assertThat(allEmployees).containsOnly(employee);
    }

    @Test
    public void shouldInsertMultipleEmployees() {
        // given
        Employee employee = createEmployee("Jan", "Kowalski");
        Employee employee2 = createEmployee("Dawid", "Krawczyk");
        employeeDao.insert(employee, employee2);

        // when
        List<Employee> allEmployees = employeeDao.getAll();

        // then
        assertThat(allEmployees).containsOnly(employee, employee2);
    }

    @Test
    public void shouldSearchByName() {
        // given
        Employee jan = createEmployee("Jan", "Kowalski");
        Employee dawid = createEmployee("Dawid", "Krawczyk");
        employeeDao.insert(jan, dawid);

        // when
        List<Employee> employees = employeeDao.search("Ja");

        // then
        assertThat(employees).containsOnly(jan);
    }

    @Test
    public void shouldSearchBySurname() {
        // given
        Employee jan = createEmployee("Jan", "Kowalski");
        Employee janusz = createEmployee("Janusz", "Krawczyk");
        employeeDao.insert(jan, janusz);

        // when
        List<Employee> employees = employeeDao.search("Kra");

        // then
        assertThat(employees).containsOnly(janusz);
    }


}
