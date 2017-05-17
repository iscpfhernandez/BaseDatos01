package mx.edu.cetis125.basedatos01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Modificaciones extends AppCompatActivity {
    // declara los objetos de las vistas
    Button btnModRegresar, btnBuscar, btnEditar;
    EditText edBuscarid, edNombres, edApellidos, edDia, edMes, edAnyo, edCalificacion;
    Switch swActivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificaciones);
        //inicializa los objetos de las vistas con las vistas del layout
        btnModRegresar = (Button) findViewById(R.id.btnModRegresar);
        btnBuscar = (Button) findViewById(R.id.btn_modificaciones_buscar);
        btnEditar = (Button) findViewById(R.id.btn_modificaciones_editar);
        edBuscarid = (EditText) findViewById(R.id.ed_modificaciones_buscarid);
        edNombres = (EditText) findViewById(R.id.ed_modificaciones_nombres);
        edApellidos = (EditText) findViewById(R.id.ed_modificaciones_apellidos);
        edDia = (EditText) findViewById(R.id.ed_modificaciones_fecha_dia);
        edMes = (EditText) findViewById(R.id.ed_modificaciones_fecha_mes);
        edAnyo = (EditText) findViewById(R.id.ed_modificaciones_fecha_anyo);
        edCalificacion = (EditText) findViewById(R.id.ed_modificaciones_calificacion);
        swActivo = (Switch) findViewById(R.id.sw_modificaciones_activo);
        // para el botón regresar, abre el Activity Menu
        btnModRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Modificaciones.this, MenuBotones.class);
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
                    BaseDatosAlumno bdalumno = new BaseDatosAlumno(Modificaciones.this, BaseDatosAlumno.BaseDatosNombre, null, 1);
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

        // se presiona el botón Editar
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // bloque Try para captar los errores
                try {
                    // toma el valor del id del registro de la propiedad Tag de la vista nombre
                    long id = Long.parseLong(edNombres.getTag().toString());
                    // convierte el valor de la vista Switch a entero, para el campo activo
                    int activo = swActivo.isChecked() ? 1 : 0 ;
                    // convierte el valor de la vista EditText de la calificación
                    double calificacion = Double.parseDouble(edCalificacion.getText().toString());
                    // se crea un objeto de tipo Alumno y se inicializa con los datos de la vista
                    Alumno a = new Alumno(id, edNombres.getText().toString().trim()
                            , edApellidos.getText().toString().trim()
                            , (edAnyo.getText().toString().trim() + "-" + edMes.getText().toString().trim() + "-" + edDia.getText().toString().trim())
                            , calificacion
                            , activo);
                    // se crea un objeto de tipo BaseDatosAlumno, y se inicializa
                    BaseDatosAlumno bdalumno = new BaseDatosAlumno(Modificaciones.this, BaseDatosAlumno.BaseDatosNombre, null, 1);
                    // se llama al métodod Modificaciones con el parámetro "a" de tipo Alumno, el cual se inicializó con la nueva información del registro
                    if (bdalumno.Modificaciones(a)) {
                        // todo correcto, se indica un mensaje de actualización correcta
                        Toast t = Toast.makeText(getApplicationContext(), "Actualización correcta", Toast.LENGTH_SHORT);
                        t.show();
                    }
                    else {
                        // errores al actualizar , se indica un mensaje de error.
                        Toast t = Toast.makeText(getApplicationContext(), "Error al actualizar el registro", Toast.LENGTH_SHORT);
                        t.show();
                    }
                }
                catch (Exception e)  {
                    // muestra un mensaje de error en la edición
                    Toast t = Toast.makeText(getApplicationContext(), "Error al actualizar el registro", Toast.LENGTH_SHORT);
                    t.show();
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
