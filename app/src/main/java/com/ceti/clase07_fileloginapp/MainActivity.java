package com.ceti.clase07_fileloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DECLARANDO Y OBTENIENDO LOS COMPONENTES
        MaterialButton btn_iniciar_sesion = findViewById(R.id.main_btn_iniciar_sesion);
        MaterialButton btn_registrarse = findViewById(R.id.main_btn_registrarse);
        TextInputEditText npt_usuario = findViewById(R.id.main_input_usuario);
        TextInputEditText npt_contrasena = findViewById(R.id.main_input_contrasena);

        btn_iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String usuario, contrasena;
                    /* OBTENIENDO LOS VALORES */
                    usuario = String.valueOf(npt_usuario.getText());
                    contrasena = String.valueOf(npt_contrasena.getText());
                    /* INCIAR SESIÓN */
                    iniciarSesion(usuario, contrasena);
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error: Ingrese todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(i);
            }
        });

    }

    private void iniciarSesion (String usuario, String contrasena) {
        try {
            /* OBTENIENDO EL CONTENIDO DEL ARCHIVO */
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("user.txt")));
            String contenido = "";
            String textoBuffer;
            boolean userExists = false;
            while ((textoBuffer=reader.readLine())!=null) {
                contenido = textoBuffer;
            }
            reader.close();

            /* GESTIONANDO LA INFORMACIÓN */
            String [] usuarios = contenido.split("/");

            for (int i = 0; i < usuarios.length; i++) {
                String [] userDetails = null;
                userDetails = usuarios[i].split(",");

                if(usuario.toLowerCase().equalsIgnoreCase(userDetails[2].toLowerCase()) && contrasena.toLowerCase().equalsIgnoreCase(userDetails[3].toLowerCase())){
                    Toast.makeText(this, "Credenciales correctas", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ListaActivity.class);
                    startActivity(intent);
                    userExists = true;
                }
            }
            if(!userExists){
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "El archivo no existe", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al leer el archivo", Toast.LENGTH_SHORT).show();
        }

    }

}