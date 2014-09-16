package com.runmit.uc.core.domain;

/**
 * Created by gg on 2014/8/21.
 */
public enum CurrentStatus {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    LOGICAL_DELETED("LOGICAL_DELETED");
    private final String status;

    private CurrentStatus(String status) {
        this.status = status;
    }

    public String value() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}