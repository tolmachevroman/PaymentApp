package com.tolmachevroman.paymentapp.datasources;

/**
 * Created by romantolmachev on 12/12/2017.
 */

public class Error {

    private int code;

    private String message;

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
