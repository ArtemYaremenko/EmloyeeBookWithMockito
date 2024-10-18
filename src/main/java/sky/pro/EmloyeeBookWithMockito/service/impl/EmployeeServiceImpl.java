package sky.pro.EmloyeeBookWithMockito.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import sky.pro.EmloyeeBookWithMockito.exceptions.EmployeeAlreadyAddedException;
import sky.pro.EmloyeeBookWithMockito.exceptions.EmployeeNotFoundException;
import sky.pro.EmloyeeBookWithMockito.exceptions.InvalidCharactersException;
import sky.pro.EmloyeeBookWithMockito.model.Employee;
import sky.pro.EmloyeeBookWithMockito.model.Passport;
import sky.pro.EmloyeeBookWithMockito.service.EmployeeService;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public HashMap<Passport, Employee> employeeBook;

    public EmployeeServiceImpl() {
        this.employeeBook = new HashMap<>();
    }

    public String checkString(String string) {
        if (!StringUtils.isAlpha(string)) {
            throw new InvalidCharactersException();
        }
        return string;
    }
    private String validateString(String string) {
        return StringUtils.capitalize(string);
    }

    @Override
    public Employee addEmployee(int passportNumber,
                                String firstName,
                                String lastName,
                                int department,
                                double salary) {
        firstName = validateString(checkString(firstName));
        lastName = validateString(checkString(lastName));
        Passport newPassport = new Passport(passportNumber);
        Employee newEmployee = new Employee(firstName, lastName, department, salary);
        if (employeeBook.containsKey(newPassport)) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeBook.put(newPassport, newEmployee);
        return newEmployee;
    }

    @Override
    public Employee removeEmployee(int passportNumber) {
        Passport passport = new Passport(passportNumber);
        if (!employeeBook.containsKey(passport)) {
            throw new EmployeeNotFoundException();
        }
        Employee removedEmployee = employeeBook.get(passport);
        employeeBook.remove(passport);
        return removedEmployee;
    }

    @Override
    public Employee findEmployee(int passportNumber) {
        Passport passport = new Passport(passportNumber);
        if (!employeeBook.containsKey(passport)) {
            throw new EmployeeNotFoundException();
        }
        return employeeBook.get(passport);
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employeeBook.values());
    }

}
