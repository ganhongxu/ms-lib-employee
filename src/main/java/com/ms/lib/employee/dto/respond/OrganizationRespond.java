package com.ms.lib.employee.dto.respond;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationRespond {

    private UUID orgId;
}
