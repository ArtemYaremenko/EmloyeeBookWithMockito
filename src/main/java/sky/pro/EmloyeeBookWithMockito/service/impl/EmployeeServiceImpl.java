package sky.pro.EmloyeeBookWithMockito.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import sky.pro.EmloyeeBookWithMockito.exceptions.EmployeeAlreadyAddedException;
import sky.pro.EmloyeeBookWithMockito.exceptions.EmployeeNotFoundException;
import sky.pro.EmloyeeBookWithMockito.exceptions.InvalidDataException;
import sky.pro.EmloyeeBookWithMockito.model.Employee;
import sky.pro.EmloyeeBookWithMockito.model.Passport;
import sky.pro.EmloyeeBookWithMockito.service.EmployeeService;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import static org.springframework.util.StringUtils.capitalize;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public HashMap<Passport, Employee> employeeBook;

    public EmployeeServiceImpl() {
        this.employeeBook = new HashMap<>();
    }

    public String checkString(String string) {
        if (!StringUtils.isAlpha(string)) {
            throw new InvalidDataException();
        }
        return string;
    }

    @Override
    public Employee addEmployee(int passportNumber,
                                String firstName,
                                String lastName,
                                int department,
                                double salary) {
        String validFirstName = capitalize(checkString(firstName));
        String validLastName = capitalize(checkString(lastName));
        if (passportNumber <= 0 || department <= 0 || salary <= 0) {
            throw new InvalidDataException();
        }
        Passport newPassport = new Passport(passportNumber);
        Employee newEmployee = new Employee(validFirstName, validLastName, department, salary);
        if (employeeBook.containsKey(newPassport)) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeBook.put(newPassport, newEmployee);
        return newEmployee;
    }

    @Override
    public Employee removeEmployee(int passportNumber) {
        if (passportNumber <= 0) {
            throw new InvalidDataException();
        }
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
        if (passportNumber <= 0) {
            throw new InvalidDataException();
        }
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
