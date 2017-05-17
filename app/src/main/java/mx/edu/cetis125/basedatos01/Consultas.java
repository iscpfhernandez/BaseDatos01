package mx.edu.cetis125.basedatos01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Consultas extends AppCompatActivity {
    // declara los objetos de las vistas
    Button btnConRegresar;
    ListView lvAlumnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);
        //inicializa los objetos de las vistas con las vistas del layout
        btnConRegresar = (Button) findViewById(R.id.btnConRegresar);
        lvAlumnos = (ListView) findViewById(R.id.lv_consultas_registros);
        // para el botón regresar, abre el Activity Menu
        btnConRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Consultas.this, MenuBotones.class);
                startActivity(i);
                finish();
            }
        });
        //genera el listado
        // se define un objeto de tipo BaseDatosAlumno, y se inicializa
        BaseDatosAlumno bdalumno = new BaseDatosAlumno(Consultas.this, BaseDatosAlumno.BaseDatosNombre, null, 1);
        // se declara un arreglo de tipo Alumno, el cual contendrá el resultado de la consulta de todos los registros
        ArrayList<Alumno> alumnos =  bdalumno.Consultas();
        // utilizando el adaptador, coloca los registros del arreglo alumnos a la vista ListView
        AdaptadorAlumno adaptador =  new AdaptadorAlumno(this, alumnos);
        lvAlumnos.setAdapter(adaptador);
    }
}
