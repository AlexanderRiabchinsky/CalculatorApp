    package com.example.myapplication;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

    public class MainActivity extends AppCompatActivity implements View.OnClickListener{
        private EditText etNum1, etNum2, etOperation;
        private TextView tvResultText;
        private Button btnCalculateResult;
        private Toast toastError;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            etNum1=findViewById(R.id.num1_edit_text);
            etNum2=findViewById(R.id.num2_edit_text);
            etOperation=findViewById(R.id.operation_edit_text);
            tvResultText=findViewById(R.id.result_text_view);
            btnCalculateResult=findViewById(R.id.calculate_result_button);
            btnCalculateResult.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            float num1, num2, result=0;
            boolean correctOperation = true;
            String operation = "";

            try {

                num1 = Float.parseFloat(etNum1.getText().toString());
                num2 = Float.parseFloat(etNum2.getText().toString());
                operation = etOperation.getText().toString();
                switch (operation) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if(num2==0) throw new ArithmeticException();
                        result = num1 / num2;
                        break;
                    default:
                        correctOperation = false;
                        break;
                }
            }catch (ArithmeticException e){
                int duration = Toast.LENGTH_SHORT;
                if(toastError != null){toastError.cancel();}
                toastError = Toast.makeText(this, R.string.divide_zero, duration);
                toastError.show();
                return;
            }catch (NullPointerException e){
                int duration = Toast.LENGTH_SHORT;
                if(toastError != null){toastError.cancel();}
                toastError = Toast.makeText(this, R.string.null_data, duration);
                toastError.show();
                return;
            }catch (NumberFormatException e){
                int duration = Toast.LENGTH_SHORT;
                if(toastError != null){toastError.cancel();}
                toastError = Toast.makeText(this, R.string.wrong_format, duration);
                toastError.show();
                return;
            }
            if(correctOperation){
            tvResultText.setText(num1+" "+operation+" "+num2+" = "+result);}
            else { int duration = Toast.LENGTH_SHORT;
                if(toastError != null){toastError.cancel();}
                toastError = Toast.makeText(this, R.string.wrong_operation, duration);
                toastError.show();
                return;}

        }
    }