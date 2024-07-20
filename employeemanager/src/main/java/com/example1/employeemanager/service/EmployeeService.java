package com.example1.employeemanager.service;
//importation de la classe Employee
import com.example1.employeemanager.model.Employee;
//importation de l'interface Employee repo
import com.example1.employeemanager.repo.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

import java.util.List;
import com.example1.employeemanager.exception.UserNotFoundException;
@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService (EmployeeRepo employeeRepo){
        this.employeeRepo=employeeRepo;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id).
                orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }
    public List<Employee> findEmployeesByName(String name) {
        return employeeRepo.findByNameContainingIgnoreCase(name);
    }
    @Transactional
    public void deleteEmployee(Long id) {
        try {
            employeeRepo.deleteEmployeeById(id);
        } catch (Exception e) {
            // Log the exception (you can use a logging framework like SLF4J here)
            System.err.println("Error deleting employee with id " + id + ": " + e.getMessage());
            throw new RuntimeException("Error deleting employee", e);
        }
    }
}

