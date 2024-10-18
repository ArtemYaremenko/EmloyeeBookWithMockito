package sky.pro.EmloyeeBookWithMockito.service;

import sky.pro.EmloyeeBookWithMockito.model.Employee;

import java.util.Collection;

public interface EmployeeService {

    Employee addEmployee(int passportNumber,
                         String firstName,
                         String lastName,
                         int depart,
                         double salary);

    Employee removeEmployee(int passportNumber);

    Employee findEmployee(int passportNumber);

    Collection<Employee> findAll();
}
