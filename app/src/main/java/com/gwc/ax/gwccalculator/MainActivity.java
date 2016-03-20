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
    private String currentOperation = null;

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
                for (int i = 0; i < 10; i++){
                    if (findViewById(numericButtons[i]).isPressed()){
                        currentButton = i;
                    }
                }
                if (firstNumberString == "0") {
                    firstNumberString = Integer.toString(currentButton);
                } else {
                    firstNumberString += Integer.toString(currentButton);
                }
                txtScreen.setText(firstNumberString);
            }else{
                for (int i = 0; i < 10; i++){
                    if (findViewById(numericButtons[i]).isPressed()){
                        currentButton = i;
                    }
                }
                if (secondNumberString == "0") {
                    secondNumberString = Integer.toString(currentButton);
                } else {
                    secondNumberString += Integer.toString(currentButton);
                }
                txtScreen.setText(secondNumberString);
            }
        }
    }

    private class OperatorButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
           /* for (int i = 0; i < 10; i++){
                if (findViewById(operatorButtons[i]).isPressed()){
                    switch (i){
                        case 0: currentOperation = "add";
                            break;
                        case 1: currentOperation = "subtract";
                            break;
                        case 2: currentOperation = "multiply";
                            break;
                        case 3: currentOperation = "divide";
                            break;
                    }

                }
            }*/
            isPopulatingFirstNumber = false;
        }
    }

    private class EqualButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO:  what should happen when one clicks on the equal button?
        }
    }
}