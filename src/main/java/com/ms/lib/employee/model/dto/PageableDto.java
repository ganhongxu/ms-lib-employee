package com.ms.lib.employee.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageableDto {

    private long offset;
    private int page;
    private int size;
    private long totalElements;
}
