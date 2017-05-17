package mx.edu.cetis125.basedatos01;

import java.util.Date;

/**
 * Created by phernandez on 16/05/2017.
 */

public class Alumno {
    // se crea el modelo con los atributos del alumno,
    private long id;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private double calificacion;
    private int activo;
    // el constructor de la clase alumno, recibe los valores de todos sus atributos
    public Alumno(long id, String nombres, String apellidos,
                  String fechaNacimiento, double calificacion, int activo) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.calificacion = calificacion;
        this.activo = activo;
    }
    // getter y setter de cada atributo de la clase
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
