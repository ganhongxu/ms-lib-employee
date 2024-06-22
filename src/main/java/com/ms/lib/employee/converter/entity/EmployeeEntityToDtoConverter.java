package com.ms.lib.employee.converter.entity;

import com.ms.lib.employee.model.dto.EmployeeDto;
import com.ms.lib.employee.model.entity.Employee;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeEntityToDtoConverter implements Converter<Employee, EmployeeDto> {

    private ModelMapper modelMapper;

    public EmployeeDto convert(Employee source){
        return modelMapper.map(source, EmployeeDto.class);
    }

}
