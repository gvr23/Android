package dsbmobile.com.app.practicadatabase.Persistent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.DropBoxManager;

import java.util.ArrayList;

import dsbmobile.com.app.practicadatabase.Model.Credencial;

/**
 * Created by giova on 11/12/2017.
 */

public class MySQLLiteHelper extends SQLiteOpenHelper{
    //need the database version and the database name
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DSBMobileDatabase";

    public MySQLLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //variables to create table name and its columns
    private static final String TABLE_NAME = "CREDENCIAL";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_USUARIO = "USUARIO";
    private static final String COLUMN_PASSWORD = "CONTRASENA";

    ///for getter
    private static final String[] COLUMNS = {COLUMN_ID, COLUMN_USUARIO, COLUMN_PASSWORD};

    @Override
    public void onCreate(SQLiteDatabase db) {
        //table creation
        String CREATE_CREDENCIAL_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                                          COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                          COLUMN_USUARIO + " TEXT, " +
                                          COLUMN_PASSWORD + " TEXT);";
        db.execSQL(CREATE_CREDENCIAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        this.onCreate(db);
    }

    //support methods=========================================================================================
    public void addUsuario(Credencial credencial){
        SQLiteDatabase db = this.getWritableDatabase();
        //1. content value to add key and value using ContentValue class
        ContentValues values = new ContentValues();
        values.put(COLUMN_USUARIO, credencial.getUsuario());
        values.put(COLUMN_PASSWORD, credencial.getContrasena());

        //2. insert
        db.insert(TABLE_NAME,  null, values);
        db.close();
    }

    public Credencial getCredencial(String usuario){
        //1. stablish readable connection
        SQLiteDatabase db = this.getReadableDatabase();

        //2. build query
        Cursor cursor = db.query(TABLE_NAME, COLUMNS,
                                 " USUARIO = ?",
                                 new String[]{usuario},
                                 null,
                                 null,
                                 null,
                                 null);

        //3. if we got a result get the first one
        if (cursor != null){
            cursor.moveToFirst();

            //4. build credencial object
            Credencial credencial = new Credencial();
            credencial.setId(Integer.parseInt(cursor.getString(0)));
            credencial.setUsuario(cursor.getString(1));
            credencial.setContrasena(cursor.getString(2));

            return credencial;
        }
        return null;
    }

    public ArrayList<Credencial> getCredenciales(){
        ArrayList<Credencial> credencialArrayList = new ArrayList<>();

        //1. build query
        String query = "SELECT * FROM " + TABLE_NAME;

        //2. establish readable connection
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //3. go over each row and build the credentials objects
        Credencial credencial = null;
        if (cursor.moveToFirst()){
            do{
                credencial = new Credencial();
                credencial.setId(Integer.parseInt(cursor.getString(0)));
                credencial.setUsuario(cursor.getString(1));
                credencial.setContrasena(cursor.getString(2));

                credencialArrayList.add(credencial);
            } while (cursor.moveToNext());
        }

        return credencialArrayList;
    }

    public void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM CREDENCIAL");
    }
}
