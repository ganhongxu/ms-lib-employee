package com.ms.lib.employee.service.query;

import com.ms.lib.employee.converter.entity.EmployeeEntityToDtoConverter;
import com.ms.lib.employee.model.dto.EmployeeDto;
import com.ms.lib.employee.model.entity.Department;
import com.ms.lib.employee.model.entity.Employee;
import com.ms.lib.employee.repository.DepartmentRepository;
import com.ms.lib.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class EmployeeQueryService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private EmployeeEntityToDtoConverter employeeEntityToDtoConverter;

    public Employee findEmployeeByName(String name){
        Optional<Employee> employee = employeeRepository.findByName(name);
        return employee.orElse(null);
    }

    //1
    //page : 1
    //size : 10
    //total e: 10
    //
    //2
    //page : 2
    //size : 1
    //total e: 12
    public Page<EmployeeDto> retrieveEmployeeByDepartment(String departmentName, Pageable pageable){
        List<Employee> employeeList = employeeRepository.findByDepartment_DepartmentName(departmentName);

        List<EmployeeDto> employeeDtoList = employeeList.stream().map(e -> employeeEntityToDtoConverter.convert(e)).toList();

        employeeDtoList = constructListData(employeeDtoList, pageable);

        return new PageImpl<>(employeeDtoList, pageable, employeeDtoList.size());
    }

    private List<EmployeeDto> constructListData(List<EmployeeDto>employeeDtoList, Pageable pageable){
        int startIndex = pageable.getPageNumber() * pageable.getPageSize();
        int endIndex = startIndex + pageable.getPageSize();

        if(employeeDtoList.size() < endIndex)
            endIndex = employeeDtoList.size();
        if(employeeDtoList.size() >= startIndex + 1)
            return employeeDtoList.subList(startIndex, endIndex);
        else
            return new ArrayList<>();
    }

    //1
    //page : 1
    //size : 10
    //total e: 10
    //
    //2
    //page : 1
    //size : 1
    //total e: 1
    public Page<EmployeeDto> retrieveEmployeePageByDepartment(String departmentName, Pageable pageable){
        Optional<Department>department = departmentRepository.findByDepartmentName(departmentName);

        Page<Employee> employeePage = new PageImpl<>(new ArrayList<>());
        if(department.isPresent())
            employeePage =  employeeRepository.findByDepartment(department.get(), pageable);

        return new PageImpl<>(employeePage.stream().map(e -> employeeEntityToDtoConverter.convert(e)).toList());
    }

    public EmployeeDto retrieveEmployeeById(UUID employeeId){
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent())
            return employeeEntityToDtoConverter.convert(employee.get());
        else
            return null;
    }


}
