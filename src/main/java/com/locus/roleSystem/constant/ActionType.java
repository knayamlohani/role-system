package com.locus.roleSystem.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@AllArgsConstructor
public enum ActionType implements Serializable {
    READ,
    WRITE,
    DELETE
}
