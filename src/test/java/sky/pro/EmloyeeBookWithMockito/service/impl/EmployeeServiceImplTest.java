package sky.pro.EmloyeeBookWithMockito.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.params.provider.MethodSource;
import sky.pro.EmloyeeBookWithMockito.exceptions.EmployeeAlreadyAddedException;
import sky.pro.EmloyeeBookWithMockito.exceptions.EmployeeNotFoundException;
import sky.pro.EmloyeeBookWithMockito.exceptions.InvalidDataException;
import sky.pro.EmloyeeBookWithMockito.model.Employee;
import sky.pro.EmloyeeBookWithMockito.service.EmployeeService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.util.StringUtils.capitalize;
import static sky.pro.EmloyeeBookWithMockito.constans.ConstantsForEmployeeServiceImpleTest.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class EmployeeServiceImplTest {

    private final EmployeeService out = new EmployeeServiceImpl();

    @ParameterizedTest
    @MethodSource("validArgumentsForTest")
    void shouldAddEmployeeWhenCorrectArguments(int passportNumber, String firstName, String lastName, int department, double salary) {
        //test
        Employee actual = out.addEmployee(passportNumber, firstName, lastName, department, salary);
        //check
        assertEquals(firstName, actual.getFirstName());
        assertEquals(lastName, actual.getLastName());
        assertEquals(department, actual.getDepartment());
        assertEquals(salary, actual.getSalary());
    }

    @ParameterizedTest
    @MethodSource("validArgumentsForTest")
    void shouldAddEmployeeWhenStringArgumentsIsLowerThenCapitalize(int passportNumber, String firstName, String lastName, int department, double salary) {
        firstName = firstName.toLowerCase();
        lastName = lastName.toLowerCase();
        //test
        Employee actual = out.addEmployee(passportNumber, firstName, lastName, department, salary);
        //check
        assertEquals(actual.getFirstName(), capitalize(firstName));
        assertEquals(actual.getLastName(), capitalize(lastName));
    }


    @ParameterizedTest
    @MethodSource("inValidArgumentsForTest")
    void shouldAddEmployeeWhenIncorrectArgumentsThenThrowException(int passportNumber, String firstName, String lastName, int department, double salary) {
        //test & check
        assertThrows(InvalidDataException.class,
                () -> out.addEmployee(passportNumber, firstName, lastName, department, salary));

    }

    @ParameterizedTest
    @MethodSource("validArgumentsForTest")
    void shouldAddEmployeeWhenAddAlreadyAddedEmployeeThanThrowException(int passportNumber, String firstName, String lastName, int department, double salary) {

        out.addEmployee(passportNumber, firstName, lastName, department, salary);
        //test & check
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> out.addEmployee(passportNumber, firstName, lastName, department, salary));
    }

    @ParameterizedTest
    @MethodSource("validArgumentsForTest")
    void shouldRemoveEmployeeWhenCorrectParam(int passportNumber, String firstName, String lastName, int department, double salary) {

        Employee expected = out.addEmployee(passportNumber, firstName, lastName, department, salary);
        //test
        Employee actual = out.removeEmployee(passportNumber);
        //check
        assertEquals(actual, expected);
        Collection<Employee> actualEmployee = out.findAll();
        assertTrue(actualEmployee.isEmpty());
    }

    @Test
    void shouldRemoveEmployeeWhenIncorrectParamThenThrowException() {
        out.addEmployee(VALID_PASSPORT_NUMBER_1,
                VALID_FIRST_NAME_2,
                VALID_LAST_NAME_3,
                VALID_DEPARTMENT_2,
                VALID_SALARY_1);
        //test & check
        assertThrows(InvalidDataException.class,
                () -> out.removeEmployee(INVALID_PASSPORT_NUMBER_3));
    }

    @ParameterizedTest
    @MethodSource("validArgumentsForTest")
    void shouldRemoveEmployeeWhenBeingDeletedEmployeeNotFoundThenThrowException(int passport) {
        //check & test
        assertThrows(EmployeeNotFoundException.class,
                () -> out.removeEmployee(passport));
    }

    @ParameterizedTest
    @MethodSource("validArgumentsForTest")
    void shouldFindEmployeeWhenCorrectParam(int passportNumber, String firstName, String lastName, int department, double salary) {
        Employee expected = out.addEmployee(passportNumber, firstName, lastName, department, salary);
        //test
        Employee actual = out.findEmployee(passportNumber);
        //check
        assertEquals(actual, expected);
    }

    @Test
    void shouldFindEmployeeWhenIncorrectParamThenThrowException() {
        out.addEmployee(VALID_PASSPORT_NUMBER_2,
                VALID_FIRST_NAME_3,
                VALID_LAST_NAME_1,
                VALID_DEPARTMENT_1,
                VALID_SALARY_2);
        //test & check
        assertThrows(InvalidDataException.class,
                () -> out.findEmployee(INVALID_PASSPORT_NUMBER_2));
    }

    @ParameterizedTest
    @MethodSource("validArgumentsForTest")
    void shouldFindEmployeeWhenBeingDeletedEmployeeNotFoundThenThrowException(int passport) {
        //check & test
        assertThrows(EmployeeNotFoundException.class,
                () -> out.findEmployee(passport));
    }

    @Test
    void shouldFindAll() {
        List<Employee> expected = new ArrayList<>();
            expected.add(out.addEmployee(VALID_PASSPORT_NUMBER_1,
                    VALID_FIRST_NAME_1,
                    VALID_LAST_NAME_1,
                    VALID_DEPARTMENT_1,
                    VALID_SALARY_1));
            expected.add(out.addEmployee(VALID_PASSPORT_NUMBER_2,
                    VALID_FIRST_NAME_2,
                    VALID_LAST_NAME_2,
                    VALID_DEPARTMENT_2,
                    VALID_SALARY_2));
            expected.add(out.addEmployee(VALID_PASSPORT_NUMBER_3,
                    VALID_FIRST_NAME_3,
                    VALID_LAST_NAME_3,
                    VALID_DEPARTMENT_1,
                    VALID_SALARY_2));
        //test
        Collection<Employee> actual = out.findAll();
        //check
        assertTrue(actual.containsAll(expected));
    }

    private static Stream<Arguments> validArgumentsForTest() {
        return Stream.of(Arguments.of(VALID_PASSPORT_NUMBER_1, VALID_FIRST_NAME_1, VALID_LAST_NAME_1, VALID_DEPARTMENT_1, VALID_SALARY_1),
                Arguments.of(VALID_PASSPORT_NUMBER_2, VALID_FIRST_NAME_2, VALID_LAST_NAME_2, VALID_DEPARTMENT_2, VALID_SALARY_2),
                Arguments.of(VALID_PASSPORT_NUMBER_3, VALID_FIRST_NAME_3, VALID_LAST_NAME_3, VALID_DEPARTMENT_1, VALID_SALARY_2),
                Arguments.of(VALID_PASSPORT_NUMBER_1, VALID_FIRST_NAME_2, VALID_LAST_NAME_3, VALID_DEPARTMENT_2, VALID_SALARY_1));
    }

    private static Stream<Arguments> inValidArgumentsForTest() {
        return Stream.of(Arguments.of(INVALID_PASSPORT_NUMBER_1, VALID_FIRST_NAME_1, VALID_LAST_NAME_1, VALID_DEPARTMENT_1, VALID_SALARY_1),
                Arguments.of(VALID_PASSPORT_NUMBER_2, INVALID_FIRST_NAME_1, VALID_LAST_NAME_2, VALID_DEPARTMENT_2, VALID_SALARY_2),
                Arguments.of(VALID_PASSPORT_NUMBER_3, VALID_FIRST_NAME_3, INVALID_LAST_NAME_3, VALID_DEPARTMENT_1, VALID_SALARY_2),
                Arguments.of(VALID_PASSPORT_NUMBER_1, VALID_FIRST_NAME_2, VALID_LAST_NAME_3, INVALID_DEPARTMENT_2, VALID_SALARY_1),
                Arguments.of(VALID_PASSPORT_NUMBER_3, VALID_FIRST_NAME_2, VALID_LAST_NAME_3, VALID_DEPARTMENT_2, INVALID_SALARY_1),
                Arguments.of(INVALID_PASSPORT_NUMBER_1, INVALID_FIRST_NAME_2, VALID_LAST_NAME_3, VALID_DEPARTMENT_2, VALID_SALARY_1),
                Arguments.of(VALID_PASSPORT_NUMBER_1, VALID_FIRST_NAME_2, VALID_LAST_NAME_3, INVALID_DEPARTMENT_1, INVALID_SALARY_2),
                Arguments.of(VALID_PASSPORT_NUMBER_2, INVALID_FIRST_NAME_3, INVALID_LAST_NAME_1, VALID_DEPARTMENT_1, VALID_SALARY_2),
                Arguments.of(INVALID_PASSPORT_NUMBER_2, INVALID_FIRST_NAME_3, INVALID_LAST_NAME_2, INVALID_DEPARTMENT_1, INVALID_SALARY_2));
    }
}