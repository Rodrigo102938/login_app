package com.ceti.clase07_fileloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class RegistroActivity extends AppCompatActivity {

    MaterialButton btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        /* DECLARANDO Y OBTENIENDO LOS COMPONENTES */
        TextInputEditText npt_nombres = findViewById(R.id.registro_input_nombre);
        TextInputEditText npt_apellidos = findViewById(R.id.registro_input_apellido);
        TextInputEditText npt_usuario = findViewById(R.id.registro_input_usuario);
        TextInputEditText npt_contrasena1 = findViewById(R.id.registro_input_contrasena1);
        TextInputEditText npt_contrasena2 = findViewById(R.id.registro_input_contrasena2);
        btn_registrar = findViewById(R.id.registro_btn_registrarse);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nombres, apellidos, usuario, contrasena1, contrasena2;
                    /* OBTENIENDO LOS VALORES */
                    nombres = String.valueOf(npt_nombres.getText());
                    apellidos = String.valueOf(npt_apellidos.getText());
                    usuario = String.valueOf(npt_usuario.getText());
                    contrasena1 = String.valueOf(npt_contrasena1.getText());
                    contrasena2 = String.valueOf(npt_contrasena2.getText());
                    if (contrasena1.toLowerCase().equalsIgnoreCase(contrasena2.toLowerCase())){
                        registrarUsuario(nombres, apellidos, usuario, contrasena1, view);
                    } else {
                        Toast.makeText(RegistroActivity.this, "Atención: Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(RegistroActivity.this, "Error: Ingrese todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registrarUsuario(String nombres, String apellidos, String usuario, String contrasena, View view){

        /* ESCRIBIENDO DATOS EN EL ARCHIVO */
        try {
            OutputStreamWriter out = new OutputStreamWriter((openFileOutput("user.txt", Context.MODE_APPEND)));
            out.write(nombres+",");
            out.write(apellidos+",");
            out.write(usuario+",");
            out.write(contrasena+"/");
            out.close();
//            Snackbar snackbar = Snackbar.make(view, "Mensaje a mostrar", Snackbar.LENGTH_SHORT);
//            View snackbarView = snackbar.getView();
//            snackbarView.setBackgroundColor(Color.GREEN); // Cambiar color de fondo
//            TextView textView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
//            textView.setTextColor(Color.WHITE); // Cambiar color del texto
//            snackbar.show();
            Toast.makeText(RegistroActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
            btn_registrar.setEnabled(false);
            btn_registrar.setEnabled(true);
            Intent i = new Intent(RegistroActivity.this, MainActivity.class);
            startActivity(i);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "El archivo no existe", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al escribir en el archivo", Toast.LENGTH_SHORT).show();
        }

    }
}