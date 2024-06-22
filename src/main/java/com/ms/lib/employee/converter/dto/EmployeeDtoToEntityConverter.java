package com.ms.lib.employee.converter.dto;

import com.ms.lib.employee.model.dto.EmployeeDto;
import com.ms.lib.employee.model.entity.Employee;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeDtoToEntityConverter implements Converter<EmployeeDto, Employee> {

    private ModelMapper modelMapper;

    public Employee convert(EmployeeDto source){
        return modelMapper.map(source, Employee.class);
    }
}
