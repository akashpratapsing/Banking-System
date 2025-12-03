
package com.example.system1.dto;

public class ResponseDTO {

    private boolean success;
    private String message;
    private Double balance;

    public ResponseDTO() {}

    public ResponseDTO(boolean success, String message, Double balance) {
        this.success = success;
        this.message = message;
        this.balance = balance;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Double getBalance() {
        return balance;
    }
}
