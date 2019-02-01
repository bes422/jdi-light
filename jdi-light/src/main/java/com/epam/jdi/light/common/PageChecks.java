package com.epam.jdi.light.common;

public enum PageChecks {
    NONE(0), NEW_PAGE(1), EVERY_PAGE(2);
    int value;
    public boolean is(PageChecks check) {
        return this.value >= check.value;
    }
    public static PageChecks parse(String value) {
        switch (value) {
            case "NONE": return NONE;
            case "NEW_PAGE": return NEW_PAGE;
            case "EVERY_PAGE": return EVERY_PAGE;
            default: return NONE;
        }
    }
    PageChecks(int value) {
        this.value = value;
    }
}
