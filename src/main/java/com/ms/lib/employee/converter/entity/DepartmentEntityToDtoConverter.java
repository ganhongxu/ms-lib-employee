package com.ms.lib.employee.converter.entity;


import com.ms.lib.employee.model.dto.DepartmentDto;
import com.ms.lib.employee.model.entity.Department;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DepartmentEntityToDtoConverter implements Converter<Department, DepartmentDto> {

    private ModelMapper modelMapper;

    public DepartmentDto convert(Department source){
        return modelMapper.map(source, DepartmentDto.class);
    }
}
