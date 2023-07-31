package com.resa.restfulapi.repository;

import com.resa.restfulapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee e WHERE " +
            "(:companyName IS NULL OR e.company_id IN (SELECT c.id FROM company c WHERE c.name LIKE %:companyName%)) " +
            "AND (:employeeName IS NULL OR e.name LIKE %:employeeName%) " +
            "AND (:departmentName IS NULL OR e.department_id IN (SELECT d.id FROM department d WHERE d.name LIKE %:departmentName%))",
            nativeQuery = true)
    List<Employee> searchByFilters(
            String companyName,
            String employeeName,
            String departmentName
    );
}
