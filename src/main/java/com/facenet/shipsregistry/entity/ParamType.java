package com.facenet.shipsregistry.entity;

import lombok.Getter;
import lombok.ToString;

import java.util.stream.Stream;

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

    public static ParamType of (int type) {
        return Stream.of(ParamType.values())
                .filter(p -> p.getType() == type)
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    public static ParamType copy(ParamType paramType) {
        return of(paramType.getType());
    }

    public String toString() {
        switch (paramType) {
            case 1:
                return "COMPANY_NAME";
            case 2:
                return "SURVEYOR";
            case 3:
                return "MEASUREMENT_EQUIPMENT";
            case 4:
                return "OPERATOR_NAME";
            case 5:
                return "QUALIFICATION_OF_OPERATOR";
            default:
                return "NONE";
        }
    }
}