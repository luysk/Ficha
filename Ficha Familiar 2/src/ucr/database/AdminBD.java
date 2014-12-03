package ucr.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

//Esta clase se usa para definir la estructura de la base de datos
//Ficha Familiar
public class AdminBD extends SQLiteOpenHelper {

    private DDL_SQL sql;
    private SQLiteAdapter adapter;
    private View view;

    public AdminBD(Context context, String name, CursorFactory factory,
                   int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        sql = new DDL_SQL();
    }

    public AdminBD(Context context, String databaseName, Object object, int databaseVersion) {
        super(context, databaseName, (CursorFactory) object, databaseVersion);
        sql = new DDL_SQL();
    }

    @Override
    //Este metodo se ejecuta cuando la base de datos es creada
    //o se indica expresamente que hay una modificacion
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(sql.getSQLConfiguracion());
            db.execSQL(sql.getSQLProvincia());
            db.execSQL(sql.getSQLCanton());
            db.execSQL(sql.getSQLDistrito());
            db.execSQL(sql.getSQLBarrio());
            db.execSQL(sql.getSQLUbicacion());
            db.execSQL(sql.getSQLRegionSalud());

            /*****************************************************************/
            /*    Se crean las tablas con sus respectivas llaves foraneas    */
            /*****************************************************************/
            db.execSQL(sql.getSQLAreaSalud());
            db.execSQL(sql.getSQLEBAIS());
            db.execSQL(sql.getSQLCondSocioeconomica());
            db.execSQL(sql.getSQLDenuncia());
            db.execSQL(sql.getSQLNacion());
            db.execSQL(sql.getTipoAsegurado());
            db.execSQL(sql.getSQLOcupacion());
            db.execSQL(sql.getSQLVacunas());
            db.execSQL(sql.getSQLVivienda());
            db.execSQL(sql.getSQLPersona());
            db.execSQL(sql.getSQLCondSalud());
            db.execSQL(sql.getSQLCondSaludM());
            db.execSQL(sql.getSQLControlVacunas());
            db.execSQL(sql.getSQLPadron());
            db.execSQL(sql.getSQLVideofoto());
            db.execSQL(sql.getSQLFamilia());
            db.execSQL(sql.getSQLVisita());
            db.execSQL(sql.getSQLAgenda());

            insertarProvincias(db);
            insertarDatosPruebaCanton(db);
            insertarDatosPruebaDistrito(db);
            insertarDatosPruebaBarrio(db);
            insertarDatosPruebaAreaSalud(db);
            insertarRegion(db);
            insertarEbais(db);
            insertarDatosPruebaConfiguracion(db);



        } catch (SQLiteException SQLEx) {
            Log.v("Create table exception esto es un error", SQLEx.getMessage());
        }
    }


    public int insertarProvincias(SQLiteDatabase db) {

        ContentValues tuplas = new ContentValues();
        int exito = 0;
        String provincias[] = new String[8];
        provincias[0] = "NINGUNO";
        provincias[1] = "SAN JOSE";
        provincias[2] = "ALAJUELA";
        provincias[3] = "CARTAGO";
        provincias[4] = "HEREDIA";
        provincias[5] = "GUANACASTE";
        provincias[6] = "PUNTARENAS";
        provincias[7] = "LIMON";

        for (int i = 0; i < provincias.length; i++) {

            tuplas.clear();
            tuplas.put("CodProvincia", i);
            tuplas.put("Nombre_Provincia", provincias[i]);
            db.insert("provincia", null, tuplas);
        }
        return exito;
    }

    @Override
    //Para cambios en el script de la base de datos
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ArrayList<String> lista_tablas = new ArrayList<String>(sql.getListaTablas());

        for (int i = 0; i < lista_tablas.size(); ++i) {
            db.execSQL("DROP TABLE IF EXISTS " + lista_tablas.get(i));
            //Eliminacion de triggers requiere ser añadida.
        }
        onCreate(db);
    }

    private void insertarDatosPruebaCanton(SQLiteDatabase db) {
        ContentValues valores_insertar = new ContentValues();

        valores_insertar.put("codProvincia", 1);
        valores_insertar.put("codCanton", 0);
        valores_insertar.put("Nombre_Canton", "NINGUNO");
        db.insert("canton", null, valores_insertar);

        valores_insertar.clear();
        valores_insertar.put("codProvincia", 1);
        valores_insertar.put("codCanton", 1);
        valores_insertar.put("Nombre_Canton", "SAN JOSE");
        db.insert("canton", null, valores_insertar);
    }


    private void insertarDatosPruebaDistrito(SQLiteDatabase db) {
        ContentValues valores_insertar = new ContentValues();

        valores_insertar.put("codprovincia", 1);
        valores_insertar.put("codcanton", 1);
        valores_insertar.put("coddistrito", 0);
        valores_insertar.put("Nombre_Distrito", "NINGUNO");
        db.insert("distrito", null, valores_insertar);

        valores_insertar.clear();
        valores_insertar.put("codprovincia", 1);
        valores_insertar.put("codcanton", 1);
        valores_insertar.put("coddistrito", 1);
        valores_insertar.put("Nombre_Distrito", "CARMEN");
        db.insert("distrito", null, valores_insertar);

        valores_insertar.clear();
        valores_insertar.put("codprovincia", 1);
        valores_insertar.put("codcanton", 1);
        valores_insertar.put("coddistrito", 2);
        valores_insertar.put("Nombre_Distrito", "MERCED");
        db.insert("distrito",null,valores_insertar);

    }

    private void insertarDatosPruebaBarrio(SQLiteDatabase db) {
        ContentValues valores_insertar = new ContentValues();
        valores_insertar.put("codProvincia", 1);
        valores_insertar.put("codCanton", 1);
        valores_insertar.put("codDistrito", 1);
        valores_insertar.put("codBarrio", 0);
        valores_insertar.put("Nombre_Barrio", "NINGUNO");
        db.insert("barrio", null, valores_insertar);

        valores_insertar.clear();
        valores_insertar.put("codProvincia", 1);
        valores_insertar.put("codCanton", 1);
        valores_insertar.put("codDistrito", 1);
        valores_insertar.put("codBarrio", 101);
        valores_insertar.put("Nombre_Barrio", "AMON");
        db.insert("barrio", null, valores_insertar);

        valores_insertar.clear();
        valores_insertar.put("codProvincia", 1);
        valores_insertar.put("codCanton", 1);
        valores_insertar.put("codDistrito", 1);
        valores_insertar.put("codBarrio", 102);
        valores_insertar.put("Nombre_Barrio", "ARANJUEZ");
    }

    private void insertarRegion(SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put("CODREGION",1);
        valores.put("NOMBREREGION","SJCENTRO");
        db.insert("regionsalud",null,valores);
    }

    private void insertarDatosPruebaConfiguracion(SQLiteDatabase db) {
        ContentValues valores_insertar = new ContentValues();
        valores_insertar.put("version", 1);
        valores_insertar.put("codProvincia", 1);
        valores_insertar.put("codCanton", 1);
        valores_insertar.put("codRegion", 1);
        valores_insertar.put("codAS", 2215);
        valores_insertar.put("FechaAct", 10 / 10 / 2014);
        valores_insertar.put("Email", "email@gmail.com");
        valores_insertar.put("UltCodViv", 5);
        db.insert("configuracion", null, valores_insertar);

    }

    private void insertarDatosPruebaAreaSalud(SQLiteDatabase db) {
        ContentValues valores_insertar = new ContentValues();
        valores_insertar.put("ID", 2215);
        valores_insertar.put("NOMBRE", "AREA DE SALUD ABANGARES");
        valores_insertar.put("TEL", 88888888);
        valores_insertar.put("NOMJEFEAS", "PRUEBAS");
        db.insert("areasalud", null, valores_insertar);
    }


    private void insertarEbais(SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put("CODEBAIS",1);
        valores.put("NOMBRE","SAN JERONIMO");
        valores.put("ID_AREASALUD",2215);
        db.insert("ebais",null,valores);
    }

}