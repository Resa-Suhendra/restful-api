package com.resa.restfulapi.controller;

import com.resa.restfulapi.entity.Employee;
import com.resa.restfulapi.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Resa S.
 * Date: 31-07-2023
 * Created in IntelliJ IDEA.
 */
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @GetMapping
    public ResponseEntity<List<Employee>> getStaff(
            @RequestParam(required = false) String company_name,
            @RequestParam(required = false) String employee_name,
            @RequestParam(required = false) String department_name
    ) {

        List<Employee> staff;
        System.out.println("company_name = " + company_name);
        System.out.println("employee_name = " + employee_name);
        System.out.println("department_name = " + department_name);

        if (StringUtils.hasText(company_name) || StringUtils.hasText(employee_name) || StringUtils.hasText(department_name)) {
            staff = staffService.searchByFilters(company_name, employee_name, department_name);
            System.out.println("staff = " + staff);
        } else {
            staff = staffService.getAllStaff(); // Modify this method in the service to fetch all staff
        }

        return ResponseEntity.ok(staff);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long employeeId) {
        Employee employee = staffService.getEmployeeById(employeeId);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = staffService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId, @RequestBody Employee updatedEmployee) {
        Employee existingEmployee = staffService.getEmployeeById(employeeId);
        if (existingEmployee != null) {
            updatedEmployee.setId(employeeId);
            Employee savedEmployee = staffService.updateEmployee(updatedEmployee);
            return ResponseEntity.ok(savedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
