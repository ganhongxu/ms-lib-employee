package com.ms.lib.employee.controller;

import com.ms.lib.employee.dto.respond.EmployeeRespondDto;
import com.ms.lib.employee.dto.respond.EmployeeSearchRespond;
import com.ms.lib.employee.dto.respond.OrganizationRespond;
import com.ms.lib.employee.model.dto.EmployeeDto;
import com.ms.lib.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@Controller
@AllArgsConstructor
@RequestMapping("/v1")
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping(value="/create")
    public ResponseEntity<EmployeeRespondDto> create(
            @RequestBody EmployeeDto employeeDto
    ){
        return ResponseEntity.ok(employeeService.createEmployee(employeeDto));

    }

    @GetMapping(value="/retrieve-employee")
    public ResponseEntity<EmployeeSearchRespond> retrieve(
            @Min(1) @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @Min(1) @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "name", defaultValue = "Marketing") String departmentName
    ){
        Pageable pageAble = PageRequest.of(page - 1, size, Sort.by("name"));
        return ResponseEntity.ok(employeeService.retrieveEmployees(departmentName, pageAble));
    }

    @PostMapping(value="/exchange")
    public OrganizationRespond exchange(
            @RequestBody EmployeeDto employeeDto
    ){
        return employeeService.exchange(employeeDto);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<EmployeeRespondDto> update(
            @RequestBody EmployeeDto employeeDto
    ){
        return ResponseEntity.ok(employeeService.createEmployee(employeeDto));

    }
}
