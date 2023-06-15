package com.ifsc.leticia.agendadecontatos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //definição das constantes usadas na transição das telas
    public static final String CONTACT_ID = "CONTACT_ID";
    public static final int NEW_CONTACT = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}