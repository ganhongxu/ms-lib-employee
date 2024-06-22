package com.ms.lib.employee.service;

import com.ms.lib.employee.client.OrganizationRestClient;
import com.ms.lib.employee.dto.request.EmployeeUpdateRequest;
import com.ms.lib.employee.dto.respond.EmployeeRespondDto;
import com.ms.lib.employee.dto.respond.EmployeeSearchRespond;
import com.ms.lib.employee.dto.respond.OrganizationRespond;
import com.ms.lib.employee.enum2.RestEnum;
import com.ms.lib.employee.model.dto.EmployeeDto;
import com.ms.lib.employee.model.dto.PageableDto;
import com.ms.lib.employee.model.entity.Department;
import com.ms.lib.employee.model.entity.Employee;
import com.ms.lib.employee.service.command.EmployeeCommandService;
import com.ms.lib.employee.service.query.DepartmentQueryService;
import com.ms.lib.employee.service.query.EmployeeQueryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeService {

    private EmployeeCommandService employeeCommandService;
    private EmployeeQueryService employeeQueryService;
    private OrganizationRestClient organizationRestClient;
    private DepartmentQueryService departmentQueryService;


    public EmployeeRespondDto createEmployee(EmployeeDto employeeDto){

        String name = employeeDto.getName();
        Employee employee = employeeQueryService.findEmployeeByName(name);
        EmployeeRespondDto respondDto = new EmployeeRespondDto();

        //check employee name duplicated
        if(employee != null){
            log.error("Duplicated Employee Name = {}", employee.getName());
            return respondDto.builder()
                    .status(RestEnum.STATUS_FAILED.getValue())
                    .errorCode("10000")
                    .description("Duplicated Employee Name = "+employee.getName())
                    .build();

        }
        //check department exist
        UUID departmentId = employeeDto.getDepartment().getId();
        Department department = departmentQueryService.findDepartmentById(departmentId);
        if(department == null){
            log.error("Department does not exist = {}", department.getDepartmentName());
            return respondDto.builder()
                    .status(RestEnum.STATUS_FAILED.getValue())
                    .errorCode("10001")
                    .description("Department does not exist = "+ department.getDepartmentName())
                    .build();

        }

        employeeDto = employeeCommandService.createNewEmployee(employeeDto);

        return respondDto.builder()
                .status(RestEnum.STATUS_SUCCESS.getValue())
                .employeeDto(employeeDto)
                .build();
    }

    public EmployeeSearchRespond retrieveEmployees(String name,Pageable pageable){

        Page<EmployeeDto> employeeDtoPage = employeeQueryService.retrieveEmployeeByDepartment(name, pageable);
        EmployeeSearchRespond searchRespond = new EmployeeSearchRespond();
        int currentPage = employeeDtoPage.getNumber() + 1;
        PageableDto pageableDto = PageableDto.builder()
                .offset(employeeDtoPage.getPageable().getOffset())
                .page(currentPage)
                .size(employeeDtoPage.getContent().size())
                .totalElements(employeeDtoPage.getTotalElements())
                .build();
        searchRespond.setResult(employeeDtoPage.getContent());
        searchRespond.setPageableDto(pageableDto);
        return searchRespond;
    }

    public OrganizationRespond exchange(EmployeeDto employeeDto){
        return organizationRestClient.exchange(employeeDto);
    }

    public EmployeeRespondDto updateEmployee(EmployeeUpdateRequest request){
        UUID employeeId = request.getEmployeeDto().getId();
        EmployeeDto employeeDto = employeeQueryService.retrieveEmployeeById(employeeId);
        EmployeeRespondDto respondDto = new EmployeeRespondDto();

        //check employee name duplicated
        if(employeeDto == null){
            log.error("Employee not found = {}", employeeDto.getName());
            return respondDto.builder()
                    .status(RestEnum.STATUS_FAILED.getValue())
                    .errorCode("10000")
                    .description("Employee not found = "+employeeDto.getName())
                    .build();
        }

        EmployeeDto newEmployeeDto = request.getEmployeeDto();
        Department department = departmentQueryService.findDepartmentById(newEmployeeDto.getId());
        if(department == null){
            log.error("Department not found = {}", employeeDto.getName());
            return respondDto.builder()
                    .status(RestEnum.STATUS_FAILED.getValue())
                    .errorCode("10000")
                    .description("Department not found = "+employeeDto.getName())
                    .build();
        }

        newEmployeeDto = employeeCommandService.updateEmployeeInfo(newEmployeeDto, department);

        return respondDto.builder()
                .status(RestEnum.STATUS_SUCCESS.getValue())
                .employeeDto(newEmployeeDto)
                .build();

    }


}
