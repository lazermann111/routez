package com.routez.routez.enums;

public enum RequestStatus {
    ERROR(true),
    STARTED(false),
    COMPLETED(true),
    IN_PROGRESS(false);

    boolean isTerminal;
    RequestStatus(boolean isTerminal) {
        this.isTerminal = isTerminal;
    }
}