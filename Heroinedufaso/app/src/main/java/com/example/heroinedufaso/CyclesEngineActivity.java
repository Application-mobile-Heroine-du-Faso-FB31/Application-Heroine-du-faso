package com.example.heroinedufaso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class CyclesEngineActivity extends AppCompatActivity {

    private Switch switchCycleRegulier;
    private EditText editTextDays;
    private Button buttonCalcul;
    private TextView dureeCycle, debutCycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycles_engine2);
    }
}