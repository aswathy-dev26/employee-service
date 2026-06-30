package com.employeemanagement.employeeservice.repo;

import com.employeemanagement.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
}
