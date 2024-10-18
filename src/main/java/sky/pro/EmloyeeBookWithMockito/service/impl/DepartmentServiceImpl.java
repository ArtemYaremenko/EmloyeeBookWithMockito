package sky.pro.EmloyeeBookWithMockito.service.impl;

import org.springframework.stereotype.Service;
import sky.pro.EmloyeeBookWithMockito.exceptions.EmployeeNotFoundException;
import sky.pro.EmloyeeBookWithMockito.model.Employee;
import sky.pro.EmloyeeBookWithMockito.service.DepartmentService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> findAllEmployeesFromDepartment(int department) {
        return employeeService.findAll()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .toList();
    }

    @Override
    public double findSumSalaryInDepartment(int department) {
        return employeeService.findAll()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    @Override
    public double findMaxSalaryInDepartment(int department) {
        Employee maxSalary = employeeService.findAll()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
        return maxSalary.getSalary();
    }

    @Override
    public double findMinSalaryInDepartment(int department) {
        Employee minSalary = employeeService.findAll()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
        return minSalary.getSalary();
    }

    @Override
    public Map<Integer, List<Employee>> findDepartmentsAndEmployees() {
        return employeeService.findAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
