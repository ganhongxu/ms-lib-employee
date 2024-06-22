package com.ms.lib.employee.enum2;

import lombok.Getter;

@Getter
public enum RestEnum {

    STATUS_SUCCESS("SUCCESS"),
    STATUS_FAILED("FAILED");

    private final String value;

    RestEnum(final String value) {this.value = value;}
}
