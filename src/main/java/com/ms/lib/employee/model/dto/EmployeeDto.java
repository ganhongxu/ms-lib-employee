package com.ms.lib.employee.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {

    private UUID id;

    private String name;

    private String country;

    private int age;

    private DepartmentDto department;
}
