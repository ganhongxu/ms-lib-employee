package com.ms.lib.employee.service.query;

import com.ms.lib.employee.model.entity.Department;
import com.ms.lib.employee.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DepartmentQueryService {

    private DepartmentRepository departmentRepository;

    public Department findDepartmentById(UUID departmentId){
        Optional<Department> department =  departmentRepository.findById(departmentId);
        return department.orElse(null);
    }
}
