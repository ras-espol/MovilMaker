package com.example.a3dprint_control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PrintersAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printers_admin);
    }

    public void printer1admin(View view) {
        Intent i = new Intent(this, ListaPrinter1.class );
        Bundle datos=getIntent().getExtras();
        String correo=datos.getString("Correo");
        i.putExtra("Correo",correo);
        startActivity(i);
    }
}
