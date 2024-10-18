package sky.pro.EmloyeeBookWithMockito.service;

import sky.pro.EmloyeeBookWithMockito.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<Employee> findAllEmployeesFromDepartment(int department);

    double findSumSalaryInDepartment(int department);

    double findMaxSalaryInDepartment(int department);

    double findMinSalaryInDepartment(int department);

    Map<Integer, List<Employee>> findDepartmentsAndEmployees();
}
