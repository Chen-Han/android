package com.example.han.demoapp;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DemoMain extends AppCompatActivity {

    public static final String A="A",B="B",C="C";

    private double a,b,c;
    EditText aET, bET,cET; //to get text from textfields
    TextView result;
    Button calcButton;
    private double parseDb(String str){
        double parsed = 0.0;
        try{
            parsed = Double.parseDouble(str);
        }catch (NumberFormatException e){
            System.out.println("not valid number");
        }
        return parsed;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_main);

        if(savedInstanceState == null){
            a  = 0.0;
            b = 0.0;
            c = 0.0;
            //initialize all private variables
        }else {
            a = savedInstanceState.getDouble(A);
            b = savedInstanceState.getDouble(B);
            c = savedInstanceState.getDouble(C);
        }
        //initialize textfield objects

        aET = (EditText) findViewById(R.id.aTextField);
        bET = (EditText) findViewById(R.id.bTextField);
        cET = (EditText) findViewById(R.id.cTextField);
        result = (TextView) findViewById(R.id.resultTextView);
        calcButton = (Button) findViewById(R.id.calculateButton);
        calcButton.setOnClickListener(onCalcButtonClicked);
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putDouble(A,a);
        outState.putDouble(B,b);
        outState.putDouble(C,c);
    }

    private void calculateRoot(){
        double a = parseDb(aET.getText().toString());
        double b = parseDb(bET.getText().toString());
        double c = parseDb(cET.getText().toString());
        double det = b*b - 4*a*c;
        double x1 = (-b - Math.sqrt(det)) / 2 / a;
        double x2 = (-b + Math.sqrt(det)) / 2 / a;
        //but x1 and x2 might not be an actual number..
        result.setText("Root 1 is " + x1 + " Root 2 is " + x2);
    }

    private View.OnClickListener onCalcButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //logic when button clicked
            calculateRoot();
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
