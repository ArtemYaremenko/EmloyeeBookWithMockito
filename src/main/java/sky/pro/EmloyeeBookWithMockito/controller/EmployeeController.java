package sky.pro.EmloyeeBookWithMockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.EmloyeeBookWithMockito.model.Employee;
import sky.pro.EmloyeeBookWithMockito.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("passport number") int passportNumber,
                        @RequestParam("firstname") String firstName,
                        @RequestParam("lastname") String lastName,
                        @RequestParam("department") int department,
                        @RequestParam("salary") double salary) {
        return employeeService.addEmployee(passportNumber, firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("passport number") int passportNumber) {
        return employeeService.removeEmployee(passportNumber);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("passport number") int passportNumber) {
        return employeeService.findEmployee(passportNumber);
    }
}
