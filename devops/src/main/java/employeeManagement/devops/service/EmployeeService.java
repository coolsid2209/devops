package employeeManagement.devops.service;

import org.springframework.stereotype.Service;

import employeeManagement.devops.data.EmployeeRepository;
import employeeManagement.devops.entity.Employee;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.fetchAllEmployee();
    }

    public Employee getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        return employee;
    }

    public void createEmployee(Employee employee) {
        employeeRepository.insertEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.updateEmployee(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteEmployeeById(id);
    }
}
