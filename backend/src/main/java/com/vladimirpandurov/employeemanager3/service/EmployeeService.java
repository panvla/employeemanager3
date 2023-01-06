package com.vladimirpandurov.employeemanager3.service;


import com.vladimirpandurov.employeemanager3.exception.UserNotFoundException;
import com.vladimirpandurov.employeemanager3.model.Employee;
import com.vladimirpandurov.employeemanager3.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return this.employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee){
        return this.employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long id){
        return this.employeeRepository.findEmployeeById(id).orElseThrow(()-> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public List<Employee> findAllEmployees(){
        return this.employeeRepository.findAll();
    }

    public void deleteEmployee(Long id){
        this.employeeRepository.deleteEmployeeById(id);
    }
}
