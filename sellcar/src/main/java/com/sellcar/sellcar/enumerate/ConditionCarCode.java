package com.sellcar.sellcar.enumerate;

import lombok.Getter;

@Getter
public enum ConditionCarCode {
    NEW("mới"),
    OLD("cũ");

    private final String value;

    ConditionCarCode(String value){
        this.value = value;
    }
}