 /***************************************************************************************
 *    Title: Date Picker source code
 *    Author: Code With Cal
 *    Date: December 19 2020
 *    Code version: 1.0
 *    Availability: https://github.com/codeWithCal/DatePickerTutorial
 *
 ***************************************************************************************/

package com.example.heroinedufaso;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class CyclesEngineActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private EditText editTextBloodDuration, editTextCycleDuration;
    private Button buttonCalcul, buttonLastPeriodDate;
    private TextView textViewFertilityDate, textViewOvulationDate, textViewNextPeriodDate;

    private CyclesEngine cycleEngineUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycles_engine2);

        editTextBloodDuration = findViewById(R.id.editTextNumberBloodDuration);
        editTextCycleDuration = findViewById(R.id.editTextNumberPeriodDuration);
        textViewFertilityDate = findViewById(R.id.textViewFertilityDate);
        textViewOvulationDate = findViewById(R.id.textViewOvulationDate);
        textViewNextPeriodDate = findViewById(R.id.textViewNextPeriodDate);
        buttonCalcul = (Button) findViewById(R.id.buttonCalcul);
        buttonLastPeriodDate = (Button) findViewById(R.id.buttonLastPeriodDate);
        initDatePicker();
        buttonLastPeriodDate.setText(getTodaysDate());

        buttonCalcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(editTextBloodDuration.getText().toString()) ||
                        TextUtils.isEmpty(editTextCycleDuration.getText().toString())) {

                    Toast.makeText(CyclesEngineActivity.this, "SVP Entrez toutes les données requises", Toast.LENGTH_SHORT).show();

                } else {

                    cycleEngineUser = new CyclesEngine(Integer.parseInt(editTextBloodDuration.getText().toString()),
                            LocalDate.parse(buttonLastPeriodDate.getText().toString()), Integer.parseInt(editTextCycleDuration.getText().toString()));

                    textViewFertilityDate.setText("La période de fertilité est: " + cycleEngineUser.getFertilizedPeriod()[0].toString() + " à " + cycleEngineUser.getFertilizedPeriod()[4].toString());
                    textViewNextPeriodDate.setText("La date du prochain saignement est: " + cycleEngineUser.getDayOfStartOfTheNextPeriod().toString());
                    textViewOvulationDate.setText("La date d'ovulation est le: " + cycleEngineUser.getDayOfOvulation().toString());

                    editTextBloodDuration.setText("");
                    editTextCycleDuration.setText("");


                }

            }
        });

    }


    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String date = makeDateString(day, month, year);
                buttonLastPeriodDate.setText(date);

            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    private String makeDateString(int day, int month, int year) {

        return year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);

    }

    private String getMonthFormat(int month) {

        if (month == 1) {
            return "JAN";
        }
        if (month == 2) {
            return "FEV";
        }
        if (month == 3) {
            return "MAR";
        }
        if (month == 4) {
            return "AVR";
        }
        if (month == 5) {
            return "MAI";
        }
        if (month == 6) {
            return "JUN";
        }
        if (month == 7) {
            return "JUL";
        }
        if (month == 8) {
            return "AOU";
        }
        if (month == 9) {
            return "SEP";
        }
        if (month == 10) {
            return "OCT";
        }
        if (month == 1) {
            return "NOV";
        }
        if (month == 1) {
            return "DEC";
        }

        return "JAN";

    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}