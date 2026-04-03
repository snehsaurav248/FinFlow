package com.finance.dashboard.enums;

public enum RecordType {
    INCOME,
    EXPENSE;

    public boolean isIncome() {
        return this == INCOME;
    }
}