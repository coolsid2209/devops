package employeeManagement.devops.entity;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private Integer salary;
    private Timestamp createdDatetime;
    private Timestamp modifiedDatetime;
}