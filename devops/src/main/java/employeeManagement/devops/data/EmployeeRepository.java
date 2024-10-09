package employeeManagement.devops.data;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import employeeManagement.devops.entity.Employee;

import java.util.List;
import java.util.UUID;

@Repository
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Fetch all employees
    public List<Employee> fetchAllEmployee() {
        String sql = "SELECT * FROM devops_employees";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class)); // Correct usage for multiple rows
    }

    // Save a new employee
    public int insertEmployee(Employee employee) {
        String sql = "INSERT INTO devops_employees (id, first_name, last_name, email, department, salary, created_datetime, modified_datetime) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        employee.setId(UUID.randomUUID()); // Generate and set UUID before saving
        return jdbcTemplate.update(sql, employee.getId(), employee.getFirstName(), employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment(), employee.getSalary(), employee.getCreatedDatetime(),
                employee.getModifiedDatetime());
    }

    // Find employee by ID
    public Employee findEmployeeById(UUID id) {
        String sql = "SELECT * FROM devops_employees WHERE id = ?";
        try {
            NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
            SqlParameterSource parameters = new MapSqlParameterSource().addValue("id",
                    id);
            return namedParameterJdbcTemplate.queryForObject(sql,
                    parameters,
                    BeanPropertyRowMapper.newInstance(Employee.class));
        } catch (Exception e) {
            throw e;
        }
    }

    // Update an existing employee
    public Employee updateEmployee(Employee employee) {
        String sql = "UPDATE devops_employees SET first_name = ?, last_name = ?, email = ?, department = ?, salary = ?, modified_datetime = ? WHERE id = ?";
        try {
            NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
            BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(employee);
            if (namedParameterJdbcTemplate.update(sql, parameterSource) > 0)
                return employee;
            else
                return null;
        } catch (Exception e) {
            throw e;
        }
    }

    // Delete employee by ID
    public int deleteEmployeeById(Long id) {
        String sql = "DELETE FROM devops_employees WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
