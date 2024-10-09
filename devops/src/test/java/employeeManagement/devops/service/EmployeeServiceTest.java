package employeeManagement.devops.service;

import employeeManagement.devops.data.EmployeeRepository;
import employeeManagement.devops.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private UUID employeeId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeId = UUID.randomUUID();
        employee = new Employee();
        employee.setId(employeeId);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john.doe@example.com");
        employee.setDepartment("Engineering");
        employee.setSalary(100000);
    }

    // Test for getAllEmployees()
    @Test
    public void testGetAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(employeeRepository.fetchAllEmployee()).thenReturn(employeeList);

        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        verify(employeeRepository, times(1)).fetchAllEmployee();
    }

    // Test for getEmployeeById()
    @Test
    public void testGetEmployeeById() {
        when(employeeRepository.findEmployeeById(employeeId)).thenReturn(employee);

        Employee result = employeeService.getEmployeeById(employeeId);
        assertNotNull(result);
        assertEquals(employeeId, result.getId());
        assertEquals("John", result.getFirstName());
        verify(employeeRepository, times(1)).findEmployeeById(employeeId);
    }

    // Test for createEmployee()
    @Test
    public void testCreateEmployee() {
        // Mocking insertEmployee to return 1 (indicating success)
        when(employeeRepository.insertEmployee(employee)).thenReturn(1);

        employeeService.createEmployee(employee);
        verify(employeeRepository, times(1)).insertEmployee(employee);
    }

    // Test for updateEmployee()
    @Test
    public void testUpdateEmployee() {
        // Mocking updateEmployee to return 1 (indicating success)
        when(employeeRepository.updateEmployee(employee)).thenReturn(employee);

        employeeService.updateEmployee(employee);
        verify(employeeRepository, times(1)).updateEmployee(employee);
    }

    // Test for deleteEmployee()
    @Test
    public void testDeleteEmployee() {
        Long employeeIdToDelete = 123L;
        // Mocking deleteEmployeeById to return 1 (indicating success)
        when(employeeRepository.deleteEmployeeById(employeeIdToDelete)).thenReturn(1);

        employeeService.deleteEmployee(employeeIdToDelete);
        verify(employeeRepository, times(1)).deleteEmployeeById(employeeIdToDelete);
    }
}
