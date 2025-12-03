
package com.banking.system1.dto;

public class TransactionDTO {

    private String cardNumber;
    private String pin;
    private double amount;
    private String type;

    public TransactionDTO() {}

    public TransactionDTO(String cardNumber, String pin, double amount, String type) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.type = type;
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
