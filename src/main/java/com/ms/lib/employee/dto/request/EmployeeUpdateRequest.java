package com.ms.lib.employee.dto.request;

import com.ms.lib.employee.model.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateRequest {

    private EmployeeDto employeeDto;
}
