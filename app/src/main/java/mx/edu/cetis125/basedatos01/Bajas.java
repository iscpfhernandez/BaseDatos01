package mx.edu.cetis125.basedatos01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Bajas extends AppCompatActivity {
    // declara los objetos de las vistas
    Button btnBajRegresar, btnBuscar, btnBorrar;
    EditText edBuscarid, edNombres, edApellidos, edDia, edMes, edAnyo, edCalificacion;
    Switch swActivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);
        //inicializa los objetos de las vistas con las vistas del layout
        btnBajRegresar = (Button) findViewById(R.id.btnBajRegresar);
        btnBuscar = (Button) findViewById(R.id.btn_bajas_buscar);
        btnBorrar = (Button) findViewById(R.id.btn_bajas_borrar);
        edBuscarid = (EditText) findViewById(R.id.ed_bajas_buscarid);
        edNombres = (EditText) findViewById(R.id.ed_bajas_nombres);
        edApellidos = (EditText) findViewById(R.id.ed_bajas_apellidos);
        edDia = (EditText) findViewById(R.id.ed_bajas_fecha_dia);
        edMes = (EditText) findViewById(R.id.ed_bajas_fecha_mes);
        edAnyo = (EditText) findViewById(R.id.ed_bajas_fecha_anyo);
        edCalificacion = (EditText) findViewById(R.id.ed_bajas_calificacion);
        swActivo = (Switch) findViewById(R.id.sw_bajas_activo);
        // para el botón regresar, abre el Activity Menu
        btnBajRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Bajas.this, MenuBotones.class);
                startActivity(i);
                finish();
            }
        });
        // para el botón Buscar, busca por id del alumno
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // bloque Try para captar los errores
                try {
                    // toma el valor del ID desde la vista
                    long clave = Long.parseLong(edBuscarid.getText().toString());
                    // se declara un objeto de tipo BaseDatosAlumno, y se inicializa
                    BaseDatosAlumno bdalumno = new BaseDatosAlumno(Bajas.this, BaseDatosAlumno.BaseDatosNombre, null, 1);
                    // por medio del método Consultas (id) del objeto BaseDatosAlumno se busca el registro
                    Alumno a =  bdalumno.Consultas(clave);
                    // si el registro se encuentra, se colocan los valores en las vistas correspondientes
                    if (a.getId() > 0){
                        // se procesa la fecha de nacimiento, debe tener el formato "YY-mm-dd", dividiéndola en tres campos
                        String f = a.getFechaNacimiento();
                        String[] fecha = f.split("-");
                        // se colocan los valores en las vistas
                        // se utiliza la propiedad Tag de la vista nombre para almacenar el id del registro
                        edNombres.setTag(clave);
                        edNombres.setText(a.getNombres());
                        edApellidos.setText(a.getApellidos());
                        edDia.setText(fecha[2]);
                        edMes.setText(fecha[1]);
                        edAnyo.setText(fecha[0]);
                        edCalificacion.setText("" + a.getCalificacion());
                        swActivo.setChecked( (a.getActivo()==1) ? true : false );
                    }
                    else {
                        // en caso que no se encuentre el registro, se limpian los campos
                        limpiaCampos();
                        //se muestra un mensaje de no se encontró el registro
                        Toast toast1 = Toast.makeText(getApplicationContext(),"No se encontró el registro.", Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                }
                catch (Exception e)  {
                    // se muestra un mensaje de error
                    Toast t = Toast.makeText(getApplicationContext(),"Debe escribir un id numérico.", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

        // se presiona el botón borrar
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // toma el valor del id del registro de la propiedad Tag de la vista nombre
                String clave = edNombres.getTag().toString();
                // si la clave NO está definida
                if (clave == "") {
                    // indica un mensaje que no se encuentra el registro
                    Toast t = Toast.makeText(getApplicationContext(),"Registro no reconocido", Toast.LENGTH_SHORT);
                    t.show();
                }
                else {
                    //la clave si está definida
                    // define un objeto de tipo BaseDatosAlumno, y lo inicializa
                    BaseDatosAlumno bdalumno = new BaseDatosAlumno(Bajas.this, BaseDatosAlumno.BaseDatosNombre, null, 1);
                    // elimina el registro con valor ID clave,
                    if (bdalumno.Bajas(clave)){
                        // todo correcto en la eliminación, limpia los campos
                        limpiaCampos();
                        // muestra un mensaje de eliminación correcto
                        Toast t = Toast.makeText(getApplicationContext(),"Eliminación correcta", Toast.LENGTH_SHORT);
                        t.show();
                    }
                    else {
                        // muestra un mensaje de error en la eliminación
                        Toast t = Toast.makeText(getApplicationContext(),"Error al eliminar el registro", Toast.LENGTH_SHORT);
                        t.show();
                    }
                }
            }
        });
    }

    // Método para limpar las vistas del layour
    private void limpiaCampos()
    {
        // borra el contenido de la propidad Tag y las demás vistas
        edNombres.setTag("");
        edNombres.setText("");
        edApellidos.setText("");
        edDia.setText("");
        edMes.setText("");
        edAnyo.setText("");
        edCalificacion.setText("");
        swActivo.setChecked( true );
    }
}
