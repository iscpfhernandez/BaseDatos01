package mx.edu.cetis125.basedatos01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import static mx.edu.cetis125.basedatos01.BaseDatosAlumno.BaseDatosNombre;


public class Altas extends AppCompatActivity {
    // declara los objetos de las vistas
    Button btnAltRegresar, btnGrabar;
    EditText edNombres, edApellidos, edDia, edMes, edAnyo, edCalificacion;
    Switch swActivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);
        //inicializa los objetos de las vistas con las vistas del layout
        btnAltRegresar = (Button) findViewById(R.id.btnAltRegresar);
        edNombres = (EditText) findViewById(R.id.ed_altas_nombres);
        edApellidos = (EditText) findViewById(R.id.ed_altas_apellidos);
        edDia = (EditText) findViewById(R.id.ed_altas_fecha_dia);
        edMes = (EditText) findViewById(R.id.ed_altas_fecha_mes);
        edAnyo = (EditText) findViewById(R.id.ed_altas_fecha_anyo);
        edCalificacion = (EditText) findViewById(R.id.ed_altas_calificacion);
        swActivo = (Switch) findViewById(R.id.sw_altas_activo);
        btnGrabar = (Button) findViewById(R.id.btn_altas_grabar);
        // para el botón regresar, abre el Activity Menu
        btnAltRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Altas.this, MenuBotones.class);
                startActivity(i);
                finish();
            }
        });
        // al presionar el botón grabar crea un nuevo registro
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pendiente realizar la validades de datos de entrada: texto y número
                // en un bloque Try el cual controlará los errores generados
                try{
                    // convierte el valor de la vista tipo Switch a entero, para el campo activo
                    int activo = swActivo.isChecked() ? 1 : 0 ;
                    // convierte el valor del EditText para el campo calificacion
                    double calificacion = Double.parseDouble(edCalificacion.getText().toString());
                    // crea un objeto de tipo BaseDatosAlumno, para realizar la operación de alta
                    BaseDatosAlumno bdalumno = new BaseDatosAlumno(Altas.this, BaseDatosAlumno.BaseDatosNombre, null, 1);
                    // crea un objeto de tipo Alumno y lo inicializa con los valores de las vistas
                    Alumno a = new Alumno(0, edNombres.getText().toString().trim()
                            , edApellidos.getText().toString().trim()
                            , (edAnyo.getText().toString().trim() + "-" + edMes.getText().toString().trim() + "-" + edDia.getText().toString().trim())
                            , calificacion
                            , activo);
                    // con el objeto de BaseDatosAlumno, llama a su método Alta para crear un nuevo registro con los valores del alumno
                    if (bdalumno.Altas(a)){
                        // todo correcto, limpia las vistas
                        edNombres.setText("");
                        edApellidos.setText("");
                        edDia.setText("");
                        edMes.setText("");
                        edAnyo.setText("");
                        edCalificacion.setText("");
                        swActivo.setChecked(true);
                        // muestra un mensaje de almacenamiento correcto
                        Toast toast1 = Toast.makeText(getApplicationContext(),"Almacenamiento correcto", Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                    else {
                        // hubo un error al crear el registro, muestra un mensaje en pantalla
                        Toast toast1 = Toast.makeText(getApplicationContext(),"Error al grabar el registro", Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                }
                catch (Exception e) {
                    // en caso de algún error, se indica un mensaje
                    Toast t = Toast.makeText(getApplicationContext(), "Error al grabar el registro.", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }
}
