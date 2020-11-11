package com.locus.roleSystem.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@AllArgsConstructor
public enum Status implements Serializable {
    SUCCESS("success"),
    FAILURE("failure");

    private final String code;
}
