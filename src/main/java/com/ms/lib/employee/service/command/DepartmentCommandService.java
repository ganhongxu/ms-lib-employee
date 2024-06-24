package com.ms.lib.employee.service.command;

import com.ms.lib.employee.converter.dto.DepartmentDtoToEntityConverter;
import com.ms.lib.employee.converter.entity.DepartmentEntityToDtoConverter;
import com.ms.lib.employee.model.dto.DepartmentDto;
import com.ms.lib.employee.model.entity.Department;
import com.ms.lib.employee.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class DepartmentCommandService {

    private DepartmentRepository departmentRepository;
    private DepartmentDtoToEntityConverter departmentDtoToEntityConverter;
    private DepartmentEntityToDtoConverter departmentEntityToDtoConverter;

    public DepartmentDto createDepartment(DepartmentDto departmentDto){
        Department department= departmentDtoToEntityConverter.convert(departmentDto);
        assert department!=null;
        department = departmentRepository.saveAndFlush(department);
        return departmentEntityToDtoConverter.convert(department);
    }

}
