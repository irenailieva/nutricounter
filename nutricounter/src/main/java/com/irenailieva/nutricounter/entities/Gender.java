package com.irenailieva.nutricounter.entities;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender getEnumValue(String gender) {
        switch (gender) {
            case "Male": return MALE;
            case "Female": return FEMALE;
            default: return null;
        }
    }

    public static String getNameValue(Gender gender) {
        switch (gender) {
            case MALE: return "Male";
            case FEMALE: return "Female";
            default: return null;
        }
    }
}
