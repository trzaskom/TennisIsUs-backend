package com.trzaskom.utils;

public enum GenderEnum {
    WOMAN("kobieta", 1),
    MAN("mężczyzna", 2);

    private final String name;
    private final int code;

    GenderEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static String findGenderByCode(int code) {
        for (GenderEnum gender : GenderEnum.values()) {
            if (gender.getCode() == code) {
                return gender.getName();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
