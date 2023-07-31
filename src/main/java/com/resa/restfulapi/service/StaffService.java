package com.resa.restfulapi.service;

import com.resa.restfulapi.entity.Employee;
import com.resa.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Resa S.
 * Date: 31-07-2023
 * Created in IntelliJ IDEA.
 */
@Service
public class StaffService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getStaff(String companyName, String employeeName, String departmentName) {
        return employeeRepository.findAllByCompany_NameContainingIgnoreCaseAndNameContainingIgnoreCaseAndDepartment_NameContainingIgnoreCase(
                companyName, employeeName, departmentName
        );
    }

    public List<Employee> getAllStaff() {
        return employeeRepository.findAll();
    }

    public List<Employee> searchByFilters(String companyName, String employeeName, String departmentName) {
        return employeeRepository.searchByFilters(companyName, employeeName, departmentName);
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
