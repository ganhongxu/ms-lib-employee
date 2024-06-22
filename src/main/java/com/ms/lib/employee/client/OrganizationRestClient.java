package com.ms.lib.employee.client;

import com.ms.lib.employee.dto.respond.OrganizationRespond;
import com.ms.lib.employee.model.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Slf4j
public class OrganizationRestClient {

    private RestTemplate restTemplate;
    private ClientProperties clientProperties;

    public OrganizationRespond exchange(EmployeeDto employeeDto){
        HttpEntity<EmployeeDto> request = new HttpEntity<>(employeeDto);
        log.info("url {}, method {}", clientProperties.getOrganizationUrl(), clientProperties.getOrganizationHttpMethod());
        ResponseEntity<OrganizationRespond> respond = restTemplate.exchange(clientProperties.getOrganizationUrl(),
                HttpMethod.valueOf(clientProperties.getOrganizationHttpMethod()), request, OrganizationRespond.class);
        return respond.getBody();
    }
}
