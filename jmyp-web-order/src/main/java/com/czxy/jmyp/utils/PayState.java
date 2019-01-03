package com.czxy.jmyp.utils;

/**
 * Created by: YunYeoHyang
 * Created on: 2019/1/3 14:50.
 */

public enum PayState {
    NOT_PAY(0),SUCCESS(1),FAIL(2);

    PayState(int value) {
        this.value = value;
    }

    int value;

    public int getValue() {
        return value;
    }
}