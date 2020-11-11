package com.locus.roleSystem.constant;

import lombok.ToString;

import java.io.Serializable;

@ToString
public enum Role implements Serializable  {
    READ_ROLE,
    WRITE_ROLE,
    DELETE_ROLE
}
