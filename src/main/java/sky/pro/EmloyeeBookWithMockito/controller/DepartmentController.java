package sky.pro.EmloyeeBookWithMockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.EmloyeeBookWithMockito.model.Employee;
import sky.pro.EmloyeeBookWithMockito.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{departmentId}/employees")
    public List<Employee> employeesInDepartment(@PathVariable int department) {
        return departmentService.findAllEmployeesFromDepartment(department);
    }

    @GetMapping("/{departmentId}/sum")
    public double sumSalary(@PathVariable int department) {
        return departmentService.findSumSalaryInDepartment(department);
    }

    @GetMapping("/{departmentId}/max")
    public double maxSalary(@PathVariable int department) {
        return departmentService.findMaxSalaryInDepartment(department);
    }

    @GetMapping("/{departmentId}/min")
    public double minSalary(@PathVariable int department) {
        return departmentService.findMinSalaryInDepartment(department);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> departmentsAndEmployees() {
        return departmentService.findDepartmentsAndEmployees();
    }
}
