package com.facenet.shipsregistry.entity;

import lombok.Getter;
import lombok.ToString;

import java.util.stream.Stream;

@Getter
public enum ParamType {

    NONE(0), COMPANY_NAME(1), SURVEYOR(2), MEASUREMENT_EQUIPMENT(3), OPERATOR(4),
    QUALIFICATION_OF_OPERATOR(5), TM3_VALUE(6), TM4_VALUE(7), TM5_VALUE(8), TM6_VALUE(9), TM7_VALUE(10);

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

    @Override
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
            case 6:
                return "TM3_VALUE";
            case 7:
                return "TM4_VALUE";
            case 8:
                return "TM5_VALUE";
            case 9:
                return "TM6_VALUE";
            case 10:
                return "TM7_VALUE";
            default:
                return "NONE";
        }
    }
}