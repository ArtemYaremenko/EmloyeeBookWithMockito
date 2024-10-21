package sky.pro.EmloyeeBookWithMockito.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.EmloyeeBookWithMockito.exceptions.EmployeeNotFoundException;
import sky.pro.EmloyeeBookWithMockito.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_METHOD;
import static org.mockito.Mockito.*;
import static sky.pro.EmloyeeBookWithMockito.constans.ConstantsForDepartmentServiceImplTest.*;

@TestInstance(PER_METHOD)
@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl out;

    @ParameterizedTest
    @MethodSource("departmentNumbers")
    void shouldReturnListEmployeeByDepartment(int department) {
        when(employeeServiceMock.findAll()).thenReturn(LIST_EMPLOYEES);
        List<Employee> expected = new ArrayList<>();
        for (Employee employee : LIST_EMPLOYEES) {
            if (employee.getDepartment() == department) {
                expected.add(employee);
            }
        }
        //test
        List<Employee> actual = out.findAllEmployeesFromDepartment(department);
        //check
        assertIterableEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyListWhenFindEmployeesInDoesNotExistDepartment() {
        when(employeeServiceMock.findAll()).thenReturn(LIST_EMPLOYEES);
        //test
        List<Employee> actual = out.findAllEmployeesFromDepartment(999_999);
        //check
        assertTrue(actual.isEmpty());

        verify(employeeServiceMock, only()).findAll();
    }

    @ParameterizedTest
    @MethodSource("departmentNumbers")
    void shouldReturnSumSalaryInDepartment(int departmentNumber) {
        when(employeeServiceMock.findAll()).thenReturn(LIST_EMPLOYEES);
        double expected = 0;
        for (Employee employee : LIST_EMPLOYEES ) {
            if (employee.getDepartment() == departmentNumber) {
                expected += employee.getSalary();
            }
        }
        //test
        double actual = out.findSumSalaryInDepartment(departmentNumber);
        //check
        assertEquals(expected, actual);

        verify(employeeServiceMock, only()).findAll();
    }

    @ParameterizedTest
    @MethodSource("departmentNumbers")
    void shouldReturnZeroWhenSumEmptyList(int departmentNumber) {
        when(employeeServiceMock.findAll()).thenReturn(new ArrayList<>());
        //test
        double actual = out.findSumSalaryInDepartment(departmentNumber);
        //check
        assertEquals(0.0, actual);

        verify(employeeServiceMock, only()).findAll();
    }

    @ParameterizedTest
    @MethodSource("departmentNumbers")
    void shouldReturnMaxSalaryInDepartment(int departmentNumber) {
        when(employeeServiceMock.findAll()).thenReturn(LIST_EMPLOYEES);
        double expected = 0;
        for (Employee employee : LIST_EMPLOYEES ) {
            if (employee.getDepartment() == departmentNumber && employee.getSalary() > expected) {
                expected = employee.getSalary();
            }
        }
        //test
        double actual = out.findMaxSalaryInDepartment(departmentNumber);
        //check
        assertEquals(expected, actual);

        verify(employeeServiceMock, only()).findAll();
    }

    @ParameterizedTest
    @MethodSource("departmentNumbers")
    void shouldThrowExceptionWhenMaxSalaryInEmptyList(int departmentNumber) {
        when(employeeServiceMock.findAll()).thenReturn(new ArrayList<>());
        //test $ check
        assertThrows(EmployeeNotFoundException.class,
                () -> out.findMaxSalaryInDepartment(departmentNumber));

        verify(employeeServiceMock, only()).findAll();
    }

    @Test
    void shouldThrowExceptionWhenMaxSalaryInDoesNotExistDepartment() {
        when(employeeServiceMock.findAll()).thenReturn(LIST_EMPLOYEES);
        //test $ check
        assertThrows(EmployeeNotFoundException.class,
                () -> out.findMaxSalaryInDepartment(999_999));

        verify(employeeServiceMock, only()).findAll();
    }

    @ParameterizedTest
    @MethodSource("departmentNumbers")
    void shouldReturnMinSalaryInDepartment(int departmentNumber) {
        when(employeeServiceMock.findAll()).thenReturn(LIST_EMPLOYEES);
        double expected = 999_999_999;
        for (Employee employee : LIST_EMPLOYEES ) {
            if (employee.getDepartment() == departmentNumber && employee.getSalary() < expected) {
                expected = employee.getSalary();
            }
        }
        //test
        double actual = out.findMinSalaryInDepartment(departmentNumber);
        //check
        assertEquals(expected, actual);

        verify(employeeServiceMock, only()).findAll();
    }

    @ParameterizedTest
    @MethodSource("departmentNumbers")
    void shouldThrowExceptionWhenMinSalaryInEmptyList(int departmentNumber) {
        when(employeeServiceMock.findAll()).thenReturn(new ArrayList<>());
        //test $ check
        assertThrows(EmployeeNotFoundException.class,
                () -> out.findMinSalaryInDepartment(departmentNumber));

        verify(employeeServiceMock, only()).findAll();
    }

    @Test
    void shouldThrowExceptionWhenMinSalaryInDoesNotExistDepartment() {
        when(employeeServiceMock.findAll()).thenReturn(LIST_EMPLOYEES);
        //test $ check
        assertThrows(EmployeeNotFoundException.class,
                () -> out.findMinSalaryInDepartment(999_999));

        verify(employeeServiceMock, only()).findAll();
    }

    @Test
    void shouldReturnMapDepartmentWithEmployee() {
        when(employeeServiceMock.findAll()).thenReturn(LIST_EMPLOYEES);
        Map<Integer, List<Employee>> expected = LIST_EMPLOYEES.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        //test
        Map<Integer, List<Employee>> actual = out.findDepartmentsAndEmployees();
        //check
        for (Map.Entry<Integer, List<Employee>> expectedEntry : expected.entrySet()) {
            assertIterableEquals(expectedEntry.getValue(), actual.get(expectedEntry.getKey()));
        }

        verify(employeeServiceMock, only()).findAll();
    }

    @Test
    void shouldReturnEmptyMapWhenEmployeeListIsEmpty() {
        when(employeeServiceMock.findAll()).thenReturn(new ArrayList<>());
        //test
        Map<Integer, List<Employee>> actual = out.findDepartmentsAndEmployees();
        //check
       assertTrue(actual.isEmpty());

        verify(employeeServiceMock, only()).findAll();
    }

    public static Stream<Arguments> departmentNumbers() {
        return Stream.of(Arguments.of(1),
                Arguments.of(12));
    }
}