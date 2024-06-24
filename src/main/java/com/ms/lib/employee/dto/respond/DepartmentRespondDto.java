package com.ms.lib.employee.dto.respond;

import com.ms.lib.employee.model.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRespondDto {

    private DepartmentDto departmentDto;

    private String status;

    private String errorCode;

    private String description;
}
