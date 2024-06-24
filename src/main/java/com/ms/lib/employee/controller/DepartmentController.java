package com.ms.lib.employee.controller;

import com.ms.lib.employee.dto.request.DepartmentCreateRequest;
import com.ms.lib.employee.dto.respond.DepartmentRespondDto;
import com.ms.lib.employee.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/v1")
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping(value="/department/create")
    public ResponseEntity<DepartmentRespondDto> create(
            @RequestBody DepartmentCreateRequest request
            ){
        return ResponseEntity.ok(departmentService.createDepartment(request));
    }
}
