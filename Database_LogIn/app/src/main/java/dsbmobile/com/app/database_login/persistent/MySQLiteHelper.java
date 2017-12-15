package dsbmobile.com.app.database_login.persistent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import dsbmobile.com.app.database_login.model.Credenciales;

/**
 * Created by giova on 11/10/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SQLDSBMobile";

    public MySQLiteHelper(Context context) {
//        public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TABLE_NAME = "CREDENCIALES";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_USUARIO = "USUARIO";
    private static final String COLUMN_PASSWORD = "PASSWORD";
    private static final String COLUMN_PIN = "PIN";

    //FOR GETTER
    private static final String[] COLUMNS = {COLUMN_ID, COLUMN_USUARIO, COLUMN_PASSWORD, COLUMN_PIN};


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                          COLUMN_USUARIO + " TEXT, " +
                                                          COLUMN_PASSWORD + " TEXT, " +
                                                          COLUMN_PIN + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CREDENCIALES");

        //create fresh credenciales table
        this.onCreate(db);
    }

    public void addUsuario(Credenciales credencial){
        //1. get reference and permits to write in the database
        SQLiteDatabase db = this.getWritableDatabase();

        //2. Create content values to add key "column"/value using ContentValues Class
        ContentValues values = new ContentValues();
        values.put(COLUMN_USUARIO, credencial.getUsuario());
        values.put(COLUMN_PASSWORD, credencial.getContrasena());
        values.put(COLUMN_PIN, credencial.getPin());

        //3. Insert
        db.insert(TABLE_NAME, null, values);

        //4. close
        db.close();

    }

    public Credenciales getCredencial(String usuario){
        //1. get reference to readable database
        SQLiteDatabase db = this.getReadableDatabase();

        //2. build query
        Cursor cursor = db.query(TABLE_NAME, COLUMNS,
                                 " USUARIO = ?", //selections
                                 new String[]{usuario}, //selection args
                                 null, //groupBy
                                  null, //having
                                 null, //orderBy
                                    null); //limit

        //3. if we got results get the first one
        if (cursor != null && cursor.moveToFirst() != false){
            cursor.moveToFirst();
            //4. build crenciales object
            Credenciales credencial = new Credenciales();
            credencial.setId(Integer.parseInt(cursor.getString(0)));
            credencial.setUsuario(cursor.getString(1));
            credencial.setContrasena(cursor.getString(2));
            credencial.setPin(cursor.getString(3));

            return  credencial;
        }

        return null;
    }

    public ArrayList<Credenciales> getAllCredenciales(){
        ArrayList<Credenciales> credenciales = new ArrayList<>();

        //1. build the query
        String query = "SELECT * FROM " + TABLE_NAME;

        //2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //3. go over each row, build credenciales and add it to the list
        Credenciales credencial = null;
        if (cursor.moveToFirst()){
            do{
                credencial = new Credenciales();
                credencial.setId(Integer.parseInt(cursor.getString(0)));
                credencial.setUsuario(cursor.getString(1));
                credencial.setContrasena(cursor.getString(2));
                credencial.setPin(cursor.getString(3));

                credenciales.add(credencial);
            } while (cursor.moveToNext());
        }

        return credenciales;
    }

    public int updateBook(Credenciales credencial){
        //1. get refernce and permit to write to the db
        SQLiteDatabase db = this.getWritableDatabase();

        //2. create contentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_USUARIO, credencial.getUsuario());
        values.put(COLUMN_PASSWORD, credencial.getContrasena());
        values.put(COLUMN_PIN, credencial.getPin());

        //3. updating row
        int i = db.update(TABLE_NAME,
                         values,
                         COLUMN_ID + " = ?", //where clause
                         new String[]{String.valueOf(credencial.getId())}); //select the credencial by id

        //4. close
        db.close();

        //row that was updated
        return i;
    }

    public void deleteCredencial(Credenciales credenciales){
        //1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //2. delete row
        db.delete(TABLE_NAME,
                  COLUMN_ID + " = ?", //where clause
                  new String[]{String.valueOf(credenciales.getId())});

        //3. close
        db.close();
    }

    public void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM CREDENCIALES");
    }
}
