package com.revature.models;

public class TransferInput {

    float amount;
    int fromAccountNum;
    int toAccountNum;


    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getFromAccountNum() {
        return fromAccountNum;
    }

    public void setFromAccountNum(int fromAccountNum) {
        this.fromAccountNum = fromAccountNum;
    }

    public int getToAccountNum() {
        return toAccountNum;
    }

    public void setToAccountNum(int toAccountNum) {
        this.toAccountNum = toAccountNum;
    }

}
