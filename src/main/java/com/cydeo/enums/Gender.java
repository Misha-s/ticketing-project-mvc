package com.cydeo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {

    MALE("Male"),FEMALE("Female");

    private final String value;

}
