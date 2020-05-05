package com.example.a3dprint_control;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Ingresar extends Activity {

    private String serverIP = "remotemysql.com";
    private String port = "3306";
    private String userMySQL = "cD2kkGazJC";
    private String pwdMySQL = "34WoCvLgEW";
    private String database = "cD2kkGazJC";
    private String[] datosConexion = null;
    private EditText corr;
    private EditText contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);
        corr=(EditText)findViewById(R.id.correo);
        contra=(EditText)findViewById(R.id.contraseña);
    }

    public void registrar(View view) {
        corr.setText("");
        contra.setText("");
        Intent i = new Intent(this, Registrar.class );
        startActivity(i);
    }

    public void verificarUsuario(View view)
    {
        String[] resultadoSQL = null;
        try{
            datosConexion = new String[]{
                    serverIP,
                    port,
                    database,
                    userMySQL,
                    pwdMySQL,
                    "SELECT * FROM Usuario;"
            };
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            resultadoSQL = new AsyncQuery().execute(datosConexion).get();
            Toast.makeText(Ingresar.this,"Conexión Establecida", Toast.LENGTH_LONG).show();
            String resultadoConsulta = resultadoSQL[0];
            String[] lista=resultadoConsulta.split("\n");
            lista[0]="Nombre,Contraseña,Correo,User_name";
            int contador=1;
            boolean veridico=false;
            for(String dato: lista){
                if(contador!=1){
                    String[] listaCompleta= dato.split(",");
                    if ((listaCompleta[2].equals(corr.getText().toString()))&&(listaCompleta[1].equals(contra.getText().toString()))&&(!(corr.getText().toString()).equals(""))&&(!(contra.getText().toString()).equals(""))){
                        veridico=true;
                    }
                }else{
                    contador++;
                }
            }
            if(veridico){
                Toast.makeText(this,"Si existe el usuario", Toast.LENGTH_LONG).show();
                Intent i4 = new Intent(this, Printers.class);
                i4.putExtra("Correo",corr.getText().toString());
                corr.setText("");
                contra.setText("");
                startActivity(i4);
            }else{
                Toast.makeText(this,"Usuario no valido", Toast.LENGTH_LONG).show();
            }
        }catch(Exception ex)
        {
            Toast.makeText(this, "Error al obtener resultados de la consulta Transact-SQL: "
                    + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
