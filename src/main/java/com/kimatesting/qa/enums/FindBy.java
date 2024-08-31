package com.kimatesting.qa.enums;

public enum FindBy {
    xpath("xpath"),
    css("css");

    private String value;

    FindBy(String pValue) {
        this.value = pValue;
    }

    public String getValue() {
        return value;
    }
}
