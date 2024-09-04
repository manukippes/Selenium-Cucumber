package com.kimatesting.qa.enums;

public enum Env {
    QA("qa"),
    DEV("dev"),
    PROD("prod");

    private final String value;

    Env(String pValue) {
        this.value = pValue;
    }

    public String get() {
        return value;
    }
}
