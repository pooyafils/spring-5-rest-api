package com.devup.exceptions;

public class ErrorHandling {
    private int status;
    private String error;
    private long Timestames;

    public ErrorHandling(int status, String error, long timestames) {
        this.status = status;
        this.error = error;
        Timestames = timestames;
    }

    public ErrorHandling() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getTimestames() {
        return Timestames;
    }

    public void setTimestames(long timestames) {
        Timestames = timestames;
    }
}

