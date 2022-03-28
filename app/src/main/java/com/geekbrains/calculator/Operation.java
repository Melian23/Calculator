package com.geekbrains.calculator;

public class Operation {

    private State state;

    public Operation() {
        state = State.inputFirstArg;
    }

    public void onPressButton() {

    }

    public void onPressActions() {

    }

    public String getResult() {
        return null;
    }

    enum State {
        inputFirstArg, inputSecondArg, selectingOperation, resultShow
    }
}
