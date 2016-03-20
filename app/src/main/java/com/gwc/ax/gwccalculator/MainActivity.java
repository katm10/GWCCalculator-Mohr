package com.gwc.ax.gwccalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    // IDs of all the numeric buttons
    private int[] numericButtons = {R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine};
    // IDs of all the operator buttons
    private int[] operatorButtons = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};
    // TextView used to display the output
    private TextView txtScreen;
    // Are we populating the first or the second number?
    private boolean isPopulatingFirstNumber = true;
    // The string of the first number
    private String firstNumberString = "0";
    // The value of the first number
    private int firstNumber = 0;
    // The string of the second number
    private String secondNumberString = "0";
    // The value of the second number
    private int secondNumber = 0;
    // The string of the used operator
    private String operatorString;
    //number on button that was just pressed
    private int currentButton = 0;
    //operation to be used
    private int currentOperation = 0;

    public String add(int a, int b){
        int answer = a + b;
        return Integer.toString(answer);
    }

    public String subtract(int a, int b) {
        int answer = a - b;
        return Integer.toString(answer);
    }

    public String multiply(int a, int b){
        int answer = a * b;
        return Integer.toString(answer);
    }

    public String divide(int a, int b){
        int answer = a / b;
        return Integer.toString(answer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find the TextView
        txtScreen = (TextView) findViewById(R.id.txtScreen);
        // At the beginning of times, the text shows "0"
        txtScreen.setText("0");

        // All the numeric buttons use the NumericButtonListener listener
        NumericButtonListener numericButtonListener = new NumericButtonListener();
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(numericButtonListener);
        }

        // All the operator buttons use the OperatorButtonListener listener
        OperatorButtonListener operatorButtonListener = new OperatorButtonListener();
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(operatorButtonListener);
        }

        // The equal button uses the EqualButtonListener listener
        EqualButtonListener equalButtonListener = new EqualButtonListener();
        findViewById(R.id.btnEqual).setOnClickListener(equalButtonListener);
    }

    private class NumericButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (isPopulatingFirstNumber) {
                for (int i = 0; i < 10; i++) {
                    if (findViewById(numericButtons[i]).isPressed()) {
                        currentButton = i;
                    }
                }
                if (firstNumber == 0) {
                    firstNumber = currentButton;
                } else {
                    firstNumber = firstNumber*10 + currentButton;
                }
                txtScreen.setText(Integer.toString(firstNumber));
            } else {
                for (int i = 0; i < 10; i++) {
                    if (findViewById(numericButtons[i]).isPressed()) {
                        currentButton = i;
                    }
                }
                if (secondNumber == 0) {
                    secondNumber = currentButton;
                } else {
                    secondNumber = secondNumber*10+ currentButton;
                }
                txtScreen.setText(Integer.toString(secondNumber));
            }
        }
    }

    private class OperatorButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < 4; i++) {
                if (findViewById(operatorButtons[i]).isPressed()) {
                    currentOperation = i;
                }
            }

        isPopulatingFirstNumber=false;
    }
}

private class EqualButtonListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        switch (currentOperation) {
            case 0:
                txtScreen.setText(add(firstNumber,secondNumber));
                break;
            case 1:
                txtScreen.setText(subtract(firstNumber,secondNumber));
                break;
            case 2:
                txtScreen.setText(multiply(firstNumber,secondNumber));
                break;
            case 3:
                txtScreen.setText(divide(firstNumber,secondNumber));
                break;
        }
        firstNumber = 0;
        secondNumber = 0;
        isPopulatingFirstNumber = true;
    }
}
}