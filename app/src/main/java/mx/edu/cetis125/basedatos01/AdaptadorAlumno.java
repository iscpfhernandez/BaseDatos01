package mx.edu.cetis125.basedatos01;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mx.edu.cetis125.basedatos01.Alumno;
import mx.edu.cetis125.basedatos01.R;

/**
 * Created by phernandez on 17/05/2017.
 */

// se crea una clase de tipo ArrayAdapter de tipo Alumno, para la clase Alumno
public class AdaptadorAlumno extends ArrayAdapter<Alumno> {
    // para el listado de registros de tipo alumno se define un arreglo de tipo Alumno
    ArrayList<Alumno> datos;
    // el constructor del adaptador, recibe el contexto y el listado de registros
    public AdaptadorAlumno(@NonNull Context context, ArrayList<Alumno> datos) {
        super(context, R.layout.listitem_alumnos, datos);
        this.datos = datos;
    }

    // el método getView coloca los valores del registro en las vistas correspondientes del layout,
    // que será agregado a la vista ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // se definie el objeto de tipo LayoutInflater, para inflar, inicializar el layout
        LayoutInflater inflater = LayoutInflater.from(getContext());
        // se relaciona la vista con el layout "listitem_alumnos"
        View item = inflater.inflate(R.layout.listitem_alumnos, null);
        // se toma el registro de la posición indicada
        Alumno a = datos.get(position);
        // se coloca el nombre y apellidos en la vista del nombre
        TextView tvNombre = (TextView)item.findViewById(R.id.cmpNombre);
        tvNombre.setText(a.getNombres() + " " + a.getApellidos());
        // se coloca el ide en la vista de id
        TextView tvId = (TextView)item.findViewById(R.id.cmpId);
        tvId.setText("id: " + a.getId());
        // se coloca la fecha de nacimiento en la vista de fecha de nacimiento
        TextView tvFechaNacimiento = (TextView)item.findViewById(R.id.cmpFechaNacimiento);
        tvFechaNacimiento.setText("Fecha nacimiento: " + a.getFechaNacimiento());
        // se coloca la calificación en la vista de calificación
        TextView tvCalificacion = (TextView)item.findViewById(R.id.cmpCalificacion);
        tvCalificacion.setText("Calificación: " + a.getCalificacion());
        // se coloca el valor de activo en la vista de activo
        TextView tvActivo = (TextView)item.findViewById(R.id.cmpActivo);
        tvActivo.setText("Activo: " + ((a.getActivo()==1) ? "Si" : "No"));
        // regresa la vista con los datos del registro
        return(item);
    }

}
