package dsbmobile.com.app.retrowilf.persistance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import dsbmobile.com.app.retrowilf.model.Credencial;

/**
 * Created by giova on 11/16/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static  final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DSBMobile123";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TABLE_NAME = "CREDENCIALES";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USUARIO = "usuario";
    private static final String COLUMN_CONTRASENA = "contrasena";
    private static final String COLUMN_PIN = "pin";
    private static  final String[] COLUMNS= {COLUMN_ID, COLUMN_USUARIO, COLUMN_CONTRASENA, COLUMN_PIN};

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                          COLUMN_USUARIO + " TEXT, " +
                                                          COLUMN_CONTRASENA + " TEXT, " +
                                                          COLUMN_PIN + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        this.onCreate(db);
    }

    public void addUsuario(Credencial credencial){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USUARIO, credencial.getUsuario());
        values.put(COLUMN_CONTRASENA, credencial.getContrasena());
        values.put(COLUMN_PIN, credencial.getPin());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Credencial getCredencial(String usuario){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, COLUMNS,
                                 " usuario = ?",
                                 new String[]{usuario},
                                 null,
                                 null,
                                 null,
                                 null);

        if (cursor != null){
            cursor.moveToFirst();

            Credencial credencial = new Credencial();
            credencial.setId(Integer.parseInt(cursor.getString(0)));
            credencial.setUsuario(cursor.getString(1));
            credencial.setContrasena(cursor.getString(2));
            credencial.setPin(Integer.parseInt(cursor.getString(3)));

            return credencial;
        }
        return null;
    }

    public ArrayList<Credencial> getAllCredenciales(){
        ArrayList<Credencial> credencialArrayList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Credencial credencial = null;
        if (cursor.moveToFirst()){
            do{
                credencial = new Credencial();
                credencial.setId(Integer.parseInt(cursor.getString(0)));
                credencial.setUsuario(cursor.getString(1));
                credencial.setContrasena(cursor.getString(2));
                credencial.setPin(Integer.parseInt(cursor.getString(3)));

                credencialArrayList.add(credencial);

            }while(cursor.moveToNext());
        }
        return credencialArrayList;
    }

    public void deleteCredencial(Credencial credencial){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_USUARIO + " usuario = ?",
                                          new String[]{credencial.getUsuario()});
        db.close();
    }

    public void deleteTableRows(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
