package com.ms.lib.employee.repository;

import com.ms.lib.employee.model.entity.Department;
import com.ms.lib.employee.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByName(String name);

    List<Employee> findByDepartment(Department department);

    //return totalElements not accurate for that page
    Page<Employee> findByDepartment(Department department, Pageable pageable);

}
