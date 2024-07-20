package com.example1.employeemanager.repo;

import com.example1.employeemanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    void deleteEmployeeById(Long id);
    Optional<Employee> findEmployeeById(Long id);
    //neww
    List<Employee> findByNameContainingIgnoreCase(String name);
}

