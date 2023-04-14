package com.facenet.shipsregistry.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ParamType {

    NONE(0), COMPANY_NAME(1), SURVEYOR(2), MEASUREMENT_EQUIPMENT(3), OPERATOR_NAME(4),
    QUALIFICATION_OF_OPERATOR(5);

    private Integer paramType;

    ParamType(Integer paramType) {
        this.paramType = paramType;
    }

    public int getType() {
        return paramType;
    }
}