package com.ms.lib.employee.dto.respond;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ms.lib.employee.model.dto.EmployeeDto;
import com.ms.lib.employee.model.dto.PageableDto;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeSearchRespond {

    private PageableDto pageableDto;
    private List<EmployeeDto> result;
}
