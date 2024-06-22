package com.ms.lib.employee.service.command;

import com.ms.lib.employee.converter.dto.EmployeeDtoToEntityConverter;
import com.ms.lib.employee.converter.entity.EmployeeEntityToDtoConverter;
import com.ms.lib.employee.model.dto.EmployeeDto;
import com.ms.lib.employee.model.entity.Department;
import com.ms.lib.employee.model.entity.Employee;
import com.ms.lib.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class EmployeeCommandService {

    private EmployeeRepository employeeRepository;
    private EmployeeDtoToEntityConverter employeeDtoToEntityConverter;
    private EmployeeEntityToDtoConverter employeeEntityToDtoConverter;

    public EmployeeDto createNewEmployee(EmployeeDto employeeDto){
        Employee employee = employeeDtoToEntityConverter.convert(employeeDto);
        assert employee != null;
        employee = employeeRepository.saveAndFlush(employee);
        return employeeEntityToDtoConverter.convert(employee);
    }

    public EmployeeDto updateEmployeeInfo(EmployeeDto newEmployeeDto, Department department){
        Employee employee =  Employee.builder()
                .name(newEmployeeDto.getName())
                .age(newEmployeeDto.getAge())
                .country(newEmployeeDto.getCountry())
                .department(department)
                .build();
        employee = employeeRepository.saveAndFlush(employee);
        return employeeEntityToDtoConverter.convert(employee);
    }

}
