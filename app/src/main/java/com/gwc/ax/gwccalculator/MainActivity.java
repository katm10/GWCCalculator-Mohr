package com.gwc.ax.gwccalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.EnumMap;


public class MainActivity extends AppCompatActivity {
    // IDs of all the numeric buttons
    private int[] numericButtons = {R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine};
    // TextView used to display the output
    private TextView txtScreen;
    // Are we populating the first or the second number?
    private boolean isPopulatingFirstNumber = true;
    // The string of the first number
    private String firstNumberString = "0";
    // The value of the first number
    private double firstNumber = 0;
    // The string of the second number
    private String secondNumberString = "0";
    // The value of the second number
    private double secondNumber = 0;
    // The string of the used operator
    private String operatorString;
    //number on button that was just pressed
    private double currentButton = 0;
    //answer
    private double answer = 0;
    private operatorEnum lastOp;

    private abstract class Operator {
        abstract public double operate(double a, double b);
        abstract public Button getButton();
    }

    private class OperatorAdd extends Operator{
       public double operate(double a, double b){
            return a + b;
        }
        public Button getButton(){
            return (Button) findViewById(R.id.btnAdd);
        }
    }

    private class OperatorSub extends Operator{
        public double operate(double a, double b){
            return a - b;
        }
        public Button getButton(){
            return (Button) findViewById(R.id.btnSubtract);
        }
    }
    private class OperatorMul extends Operator{
        public double operate(double a, double b){
            return a * b;
        }
        public Button getButton(){
            return (Button) findViewById(R.id.btnMultiply);
        }
    }
    private class OperatorDiv extends Operator{
        public double operate(double a, double b){
            return a / b;
        }
        public Button getButton(){
            return (Button) findViewById(R.id.btnDivide);
        }
    }

    enum operatorEnum {
        ADD, SUB, MUL, DIV
    }

    EnumMap<operatorEnum, Operator> opArray = new EnumMap<operatorEnum, Operator>(operatorEnum.class);

    public void setTxtScreen(double num) {
        if ((double) ((int)num) == num) {
            txtScreen.setText(Integer.toString((int) num));
        } else {
            txtScreen.setText(Double.toString(num));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find the TextView
        txtScreen = (TextView) findViewById(R.id.txtScreen);
        // At the beginning of times, the text shows "0"
        txtScreen.setText("0");
        OperatorButtonListener operatorButtonListener = new OperatorButtonListener();
        for(operatorEnum op: operatorEnum.values()){
            switch (op){
                case ADD:
                    opArray.put(op,new OperatorAdd());
                    break;
                case SUB:
                   opArray.put(op,new OperatorSub());
                    break;
                case MUL:
                    opArray.put(op, new OperatorMul());
                    break;
                case DIV:
                    opArray.put(op,new OperatorDiv());
                    break;
            }
            opArray.get(op).getButton().setOnClickListener(operatorButtonListener);
        }
        // All the numeric buttons use the NumericButtonListener listener
        NumericButtonListener numericButtonListener = new NumericButtonListener();
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(numericButtonListener);
        }

        // All the operator buttons use the OperatorButtonListener listener


        // The equal button uses the EqualButtonListener listener
        EqualButtonListener equalButtonListener = new EqualButtonListener();
        findViewById(R.id.btnEqual).setOnClickListener(equalButtonListener);
    }

    private class NumericButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (isPopulatingFirstNumber) {
                for (int i = 0; i < 10; i++) {
                    if (findViewById(numericButtons[i])== v) {
                        currentButton = i;
                    }
                }
                if (firstNumber == 0) {
                    firstNumber = currentButton;
                } else {
                    firstNumber = firstNumber * 10 + currentButton;
                }
                setTxtScreen(firstNumber);
            } else {
                for (int i = 0; i < 10; i++) {
                    if (findViewById(numericButtons[i]).isPressed()) {
                        currentButton = i;
                    }
                }
                if (secondNumber == 0) {
                    secondNumber = currentButton;
                } else {
                    secondNumber = secondNumber * 10 + currentButton;
                }
                setTxtScreen(secondNumber);
            }
        }
    }

    private class OperatorButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for (operatorEnum op: operatorEnum.values()){
               if (opArray.get(op).getButton().equals(v)){
                   lastOp = op;
                   break;
               }
            }

            isPopulatingFirstNumber = false;
        }
    }

    private class EqualButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            opArray.get(lastOp).operate(firstNumber,secondNumber);
            setTxtScreen(answer);
            firstNumber = 0;
            secondNumber = 0;
            isPopulatingFirstNumber = true;
        }
    }


}