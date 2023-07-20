package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainScreen extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solutionTv;
    MaterialButton buttonper,buttonOpenBrack,buttonCloseBrack;
    MaterialButton buttonDivide,buttonMultiply,buttonAdd,buttonSub,buttonEquals;
    MaterialButton button1,button6,button2,button3,button4,button5,button7,button8,button9,button0;
    MaterialButton buttonAC,buttonDot;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

       resultTv=findViewById(R.id.result_tv);
       solutionTv=findViewById(R.id.solution_tv);

        assignId(buttonper,R.id.button_per);
        assignId(buttonOpenBrack,R.id.button_open_bracket);
        assignId(buttonCloseBrack,R.id.button_close_bracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonAdd,R.id.button_add);
        assignId(buttonSub,R.id.button_sub);
        assignId(buttonEquals,R.id.button_equals);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button0,R.id.button_0);
        assignId(buttonDot,R.id.button_point);
        assignId(buttonAC,R.id.button_AC);


    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button=(MaterialButton) v;
        String buttonText=button.getText().toString();
        String dataToCalculate=solutionTv.getText().toString();

        if (buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }

            dataToCalculate=dataToCalculate+buttonText;

        solutionTv.setText(dataToCalculate);
        String finalResult=getresult(dataToCalculate);
        if (!finalResult.equals("Error")){
            resultTv.setText(finalResult);
        }
    }

    String getresult(String data){
       try {
           Context context=Context.enter();
           context.setOptimizationLevel(-1);
           Scriptable scriptable=context.initStandardObjects();
           String finalResult= context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           if (finalResult.endsWith(".0")){
               finalResult=finalResult.replace(".0","");
           }
            return finalResult;
       }
       catch (Exception e){
           return "Error";
       }
    }
}