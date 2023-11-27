package com.ceti.clase07_fileloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        /* DECLARANDO Y OBTENIENDO COMPONENTES */
        TextView txt_lista_usuarios = findViewById(R.id.lista_txt_lista_usuarios);


        listarUsuarios(txt_lista_usuarios);


    }

    private void listarUsuarios(TextView txt_lista_usuarios){
        try {
            /* OBTENIENDO EL CONTENIDO DEL ARCHIVO */
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("user.txt")));
            String contenido = "";
            String textoBuffer;
            while ((textoBuffer=reader.readLine())!=null) {
                contenido = textoBuffer;
            }
            reader.close();

            /* GESTIONANDO LA INFORMACIÓN */
            String [] usuarios = contenido.split("/");

            for (int i = 0; i < usuarios.length; i++) {
                String [] userDetails = null;
                userDetails = usuarios[i].split(",");
                txt_lista_usuarios.append("Persona " + (i+1) + "=> " + "Nombres:" + userDetails[0]+ " Apelllidos:" + userDetails[1] + " Usuario: " + userDetails[2] + " Contraseña: " + userDetails[3] +"\n");
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