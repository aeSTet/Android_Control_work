package com.example.overwork;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.DTDHandler;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button input, data_input;
    EditText down, sit;
    String[] sex = {"м","ж"};
    TextView date;
    String current_sex;
    String result;
    Calendar Date= Calendar.getInstance();
    int current_year = Date.get(Calendar.YEAR);
    double percent;
    Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = findViewById(R.id.date);
        down = findViewById(R.id.down);
        sit = findViewById(R.id.sit);
        input = findViewById(R.id.input);
        input.setOnClickListener(this);
        data_input = findViewById(R.id.input_data);
        data_input.setOnClickListener(this);
        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sex);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.male);
        spinner.setAdapter(adapter);
        // выделяем элемент
        spinner.setSelection(0);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                current_sex = sex[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        setInitialDate();

    }


    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d= new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Date.set(Calendar.YEAR, year);
            Date.set(Calendar.MONTH, monthOfYear);
            Date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDate();
        }
    };



    @SuppressLint("RtlHardcoded")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.input:
                int year = Integer.parseInt(String.valueOf(Date.get(Calendar.YEAR)));
                if (down.getText().toString().equals("") || sit.getText().toString().equals("")) {
                    Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(this, ResultActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.input_data:
                // отображаем диалоговое окно для выбора даты
                new DatePickerDialog(MainActivity.this, d,
                        Date.get(Calendar.YEAR),
                        Date.get(Calendar.MONTH),
                        Date.get(Calendar.DAY_OF_MONTH))
                        .show();
                break;
        }
    }


    // установка начальной даты
    private void setInitialDate() {
        date.setText(DateUtils.formatDateTime(this,
                Date.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }
}