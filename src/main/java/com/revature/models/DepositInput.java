package com.revature.models;

public class DepositInput {

    float amount;
    int toAccountNum;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getToAccountNum() {
        return toAccountNum;
    }

    public void setToAccountNum(int toAccountNum) {
        this.toAccountNum = toAccountNum;
    }
}
