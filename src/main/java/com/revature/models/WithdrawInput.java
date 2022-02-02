package com.revature.models;

public class WithdrawInput {

    float amount;
    int fromAccountNum;

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
}
