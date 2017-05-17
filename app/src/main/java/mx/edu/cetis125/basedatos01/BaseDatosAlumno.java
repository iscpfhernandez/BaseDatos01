package mx.edu.cetis125.basedatos01;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by phernandez on 16/05/2017.
 */

public class BaseDatosAlumno extends SQLiteOpenHelper {

    final static String BaseDatosNombre = "Alumnos";
    String sqlAlumnos = "CREATE TABLE Alumnos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombres TEXT, apellidos TEXT, fechaNacimiento TEXT, calificacion REAL, activo INTEGER)";

    public BaseDatosAlumno(Context contexto, String nombre,
                           SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlAlumnos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS Alumnos");
        db.execSQL(sqlAlumnos);
    }

    public boolean Altas (Alumno alumno){
        SQLiteDatabase db = this.getWritableDatabase();
        if(db != null) {
            db.execSQL("INSERT INTO Alumnos (nombres, apellidos, fechaNacimiento, calificacion, activo) " +
                        "VALUES ('" + alumno.getNombres() +"', '" +
                        alumno.getApellidos() +"', '" + alumno.getFechaNacimiento() +"', '" +
                        alumno.getCalificacion() +"', '" + alumno.getActivo() +"')");
            db.close();
            return true;
        }
        else {
            return  false;
        }
    }

    public ArrayList<Alumno> Consultas (){
        ArrayList<Alumno> registros = new ArrayList<Alumno>();
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT id, nombres, apellidos, fechaNacimiento, calificacion, activo FROM Alumnos ", null);
        if (c.moveToFirst()) {
            do {
                long id = c.getLong(0);
                String nombres = c.getString(1);
                String apellidos = c.getString(2);
                String fechaNacimiento = c.getString(3);
                double calificacion = c.getDouble(4);
                int activo = c.getInt(5);
                registros.add(new Alumno(id, nombres, apellidos, fechaNacimiento, calificacion, activo));
            } while(c.moveToNext());
        }
        db.close();
        return registros;
    }

    public Alumno Consultas (long id){
        Alumno a = new Alumno(0, "", "", "", 0, 0);
        String[] campos = new String[] {"id", "nombres", "apellidos", "fechaNacimiento", "calificacion", "activo" };
        String[] args = new String[] {""+ id};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("Alumnos", campos, "id=?", args, null, null, null);
        if (c.moveToFirst()) {
            a.setId(c.getLong(0));
            a.setNombres(c.getString(1));
            a.setApellidos(c.getString(2));
            a.setFechaNacimiento(c.getString(3));
            a.setCalificacion(c.getDouble(4));
            a.setActivo(c.getInt(5));
        }
        db.close();
        return a;
    }

    public boolean Bajas (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        if(db != null) {
            db.execSQL("DELETE FROM  Alumnos where id='" + id + "'");
            db.close();
            return true;
        }
        else {
            return  false;
        }
    }

    public boolean Modificaciones (Alumno a){
        SQLiteDatabase db = this.getWritableDatabase();
        if(db != null) {
            db.execSQL("UPDATE Alumnos SET nombres='" + a.getNombres().toString() +
                    "', apellidos='" + a.getApellidos().toString() +
                    "', fechaNacimiento='"+ a.getFechaNacimiento().toString() +
                    "', calificacion='" + a.getCalificacion() +
                    "', activo='" + a.getActivo() +
                    "' where id='" + a.getId() + "'");
            db.close();
            return true;
        }
        else {
            return  false;
        }
    }

}
