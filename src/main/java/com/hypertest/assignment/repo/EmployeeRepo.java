package com.hypertest.assignment.repo;

import com.hypertest.assignment.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
}
