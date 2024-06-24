package com.ms.lib.employee.service;

import com.ms.lib.employee.dto.request.DepartmentCreateRequest;
import com.ms.lib.employee.dto.respond.DepartmentRespondDto;
import com.ms.lib.employee.enum2.RestEnum;
import com.ms.lib.employee.model.dto.DepartmentDto;
import com.ms.lib.employee.service.command.DepartmentCommandService;
import com.ms.lib.employee.service.query.DepartmentQueryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class DepartmentService {

    private DepartmentQueryService departmentQueryService;
    private DepartmentCommandService departmentCommandService;

    public DepartmentRespondDto createDepartment(DepartmentCreateRequest request){

        String departmentName = request.getName();
        if(departmentName == null){
            return DepartmentRespondDto.builder()
                    .status(RestEnum.STATUS_FAILED.getValue())
                    .errorCode("10002")
                    .description("Missing required department name")
                    .build();
        }

        DepartmentDto departmentDto = departmentQueryService.findByDepartmentName(departmentName);
        if(departmentDto != null){
            return DepartmentRespondDto.builder()
                    .status(RestEnum.STATUS_FAILED.getValue())
                    .errorCode("10002")
                    .description("Duplicated department name "+ departmentName)
                    .build();
        }

        departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName(departmentName);
        departmentDto = departmentCommandService.createDepartment(departmentDto);

        return DepartmentRespondDto.builder()
                .departmentDto(departmentDto)
                .status(RestEnum.STATUS_SUCCESS.getValue())
                .description("Department was created successfully")
                .build();
    }
}
