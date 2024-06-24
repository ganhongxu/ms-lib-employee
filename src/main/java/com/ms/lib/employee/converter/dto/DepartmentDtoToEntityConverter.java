package com.ms.lib.employee.converter.dto;


import com.ms.lib.employee.model.dto.DepartmentDto;
import com.ms.lib.employee.model.entity.Department;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DepartmentDtoToEntityConverter implements Converter<DepartmentDto, Department> {

    private ModelMapper modelMapper;

    public Department convert(DepartmentDto source){
        return modelMapper.map(source, Department.class);
    }
}
