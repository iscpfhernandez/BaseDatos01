package mx.edu.cetis125.basedatos01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuBotones extends AppCompatActivity {
    // declara los objetos de las vistas
    Button btnAltas, btnBajas, btnModificaciones, btnConsultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_botones);
        //inicializa los objetos de las vistas con las vistas del layout
        btnAltas = (Button) findViewById(R.id.btnAltas);
        btnBajas = (Button) findViewById(R.id.btnBajas);
        btnModificaciones = (Button) findViewById(R.id.btnModificaciones);
        btnConsultas = (Button) findViewById(R.id.btnConsultas);
        //envía a Activity Altas
        btnAltas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuBotones.this, Altas.class);
                startActivity(i);
                finish();
            }
        });
        //envía a Activity Bajas
        btnBajas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuBotones.this, Bajas.class);
                startActivity(i);
                finish();
            }
        });
        //envía a Activity Modificaciones
        btnModificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuBotones.this, Modificaciones.class);
                startActivity(i);
                finish();
            }
        });
        //envía a Activity Consultas
        btnConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuBotones.this, Consultas.class);
                startActivity(i);
                finish();
            }
        });

    }
}
