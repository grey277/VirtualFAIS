package com.grey.virtualfais.helpers;

import com.grey.virtualfais.models.Department;
import com.grey.virtualfais.models.Employee;
import com.grey.virtualfais.models.Name;

public class EmployeeFactory {
    public static Employee createEmployee(String firstName, String lastName) {
        return createEmployee(firstName, lastName, "TEST_ROOM_ID");
    }

    public static Employee createEmployee(String firstName, String lastName, String roomId) {
        return new Employee(new Name("dr", firstName, lastName), new Department("ZTG", "Zakład Technologii Gier"), roomId);
    }
}
