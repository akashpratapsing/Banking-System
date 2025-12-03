
package com.example.system2.dto;

public class ResponseDTO {
    private boolean success;
    private String message;
    private Double balance;

    public ResponseDTO() {}
    public ResponseDTO(boolean success, String message, Double balance) {
        this.success = success; this.message = message; this.balance = balance;
    }
    public static ResponseDTO fail(String msg) { return new ResponseDTO(false, msg, null); }
    public static ResponseDTO ok(String msg, Double balance) { return new ResponseDTO(true, msg, balance); }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Double getBalance() { return balance; }
}
