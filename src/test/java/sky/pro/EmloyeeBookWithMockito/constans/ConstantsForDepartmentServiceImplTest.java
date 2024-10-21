package sky.pro.EmloyeeBookWithMockito.constans;

import sky.pro.EmloyeeBookWithMockito.model.Employee;

import java.util.List;

import static sky.pro.EmloyeeBookWithMockito.constans.ConstantsForEmployeeServiceImpleTest.*;

public class ConstantsForDepartmentServiceImplTest {

    public static final Employee EMPLOYEE_1 = new Employee(VALID_FIRST_NAME_1, VALID_LAST_NAME_1, VALID_DEPARTMENT_1, VALID_SALARY_1);
    public static final Employee EMPLOYEE_2 = new Employee(VALID_FIRST_NAME_2, VALID_LAST_NAME_2, VALID_DEPARTMENT_2, VALID_SALARY_1);
    public static final Employee EMPLOYEE_3 = new Employee(VALID_FIRST_NAME_3, VALID_LAST_NAME_3, VALID_DEPARTMENT_1, VALID_SALARY_2);
    public static final Employee EMPLOYEE_4 = new Employee(VALID_FIRST_NAME_1, VALID_LAST_NAME_3, VALID_DEPARTMENT_2, VALID_SALARY_2);

    public static final List<Employee> LIST_EMPLOYEES = List.of(new Employee[]{EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4});
}
