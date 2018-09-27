package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos extends SQLiteOpenHelper
{
    private static final String DB_NAME = "ST455_GARRA";
    private static final int SCHENME_VERSION = 1;
    public final SQLiteDatabase db;

    public BaseDeDatos(Context context)
    {
        super(context, DB_NAME, null, SCHENME_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DBdatos.TAB_DATOS);
        db.execSQL(DBcorreccion.TAB_CORRECCION);
        db.execSQL(DBpesadas.TAB_PESADAS);
        db.execSQL(DBproductos.TAB_PRODUCTOS);
        db.execSQL(DBcero.TAB_CERO);
        db.execSQL(DBcalibracion.TAB_CALIBRACION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXIXTS" + DBdatos.TABLE_NAME_DATOS);
        db.execSQL("DROP TABLE IF EXIXTS" + DBcorreccion.TABLE_NAME_CORRECCION);
        db.execSQL("DROP TABLE IF EXIXTS" + DBpesadas.TABLE_NAME_PESADAS);
        db.execSQL("DROP TABLE IF EXIXTS" + DBproductos.TABLE_NAME_PRODUCTOS);
        db.execSQL("DROP TABLE IF EXIXTS" + DBcero.TABLE_NAME_CERO);
        db.execSQL("DROP TABLE IF EXIXTS" + DBcalibracion.TABLE_NAME_CALIBRACION);
        onCreate(db);
    }

    private ContentValues generarPesadas(DBpesadas pesadas)
    {
        ContentValues valores = new ContentValues();
        valores.put(DBpesadas.FPES_ID,pesadas.getIdPesada());
        valores.put(DBpesadas.FPES_FECHA, pesadas.getFecha());
        valores.put(DBpesadas.FPES_HORA, pesadas.getHora());
        valores.put(DBpesadas.FPES_CARGIO, pesadas.getCargio());
        valores.put(DBpesadas.FPES_PRODUCTO, pesadas.getProducto());
        valores.put(DBpesadas.FPES_PATENTE, pesadas.getPatente());
        valores.put(DBpesadas.FPES_TARA, pesadas.getTara());
        valores.put(DBpesadas.FPES_VOLUMEN, pesadas.getVolumen());
        valores.put(DBpesadas.FPES_CODIGO, pesadas.getCodigo());

        return valores;
    }
    public void InsertarPesadas(DBpesadas pesadas)
    {
        db.insert(DBpesadas.TABLE_NAME_PESADAS, null, generarPesadas(pesadas));
    }
    private ContentValues generarDatos(DBdatos datos)
    {
        ContentValues valores = new ContentValues();
        valores.put(DBdatos.FDAT_PRODUCTO, datos.getProducto());
        valores.put(DBdatos.FDAT_PATENTE, datos.getPatente());
        valores.put(DBdatos.FDAT_TARA, datos.getTara());
        valores.put(DBdatos.FDAT_VOLUMEN, datos.getVolumen());
        valores.put(DBdatos.FDAT_CODIGO, datos.getCodigo());
        return valores;
    }
    private void InsetarDatos(DBdatos datos){
        db.insert(DBdatos.TABLE_NAME_DATOS,null, generarDatos(datos));
    }


    private ContentValues generarProductos(DBproductos productos)
    {
        ContentValues valores = new ContentValues();
        valores.put(DBproductos.FPRO_ID_NUMERO, productos.getNumero());
        valores.put(DBproductos.FPRO_ID_NOMBRE, productos.getNombre());

        return valores;
    }
    public void actualizarProductos(DBproductos productos, String id)
    {
        db.update(DBproductos.TABLE_NAME_PRODUCTOS, generarProductos(productos), DBproductos.FPRO_ID_NUMERO+ "=?", new String[]{id});
    }

    public List<String> getAllProductos()
    {
        List<String> productos = new ArrayList<String>();
        String selectQuery = "SELECT _id, nombre FROM " + DBproductos.TABLE_NAME_PRODUCTOS +" ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
        {
            do
            {
                productos.add(cursor.getString(1));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return  productos;
    }


    /**
     * @param calibracion
     * @return
     */

    private ContentValues generarCalibracion(DBcalibracion calibracion)
    {
        ContentValues valores = new ContentValues();
        valores.put(DBcalibracion.FCAL_CAPACIDAD, calibracion.getCapacidad());
        valores.put(DBcalibracion.FCAL_CELDAS, calibracion.getCeldas());
        valores.put(DBcalibracion.FCAL_DIVISION, calibracion.getDivision());
        valores.put(DBcalibracion.FCAL_SENSIBILIDAD, calibracion.getSensibilidad());
        valores.put(DBcalibracion.FCAL_VENTANA, calibracion.getVentana());
        valores.put(DBcalibracion.FCAL_KGFILTRO, calibracion.getKgfiltro());
        valores.put(DBcalibracion.FCAL_CONVERSIONES, calibracion.getConversiones());
        valores.put(DBcalibracion.FCAL_RECORTES, calibracion.getRecortes());

        return valores;
    }
    public void actualizarCalibracion(DBcalibracion calibracion, String id)
    {
        db.update(DBcalibracion.TABLE_NAME_CALIBRACION, generarCalibracion(calibracion), DBcalibracion.FCAL_ID+ "=?", new String[]{id});
    }
    private void InsetarCalibracion(DBcalibracion calibracion){
        db.insert(DBcalibracion.TABLE_NAME_CALIBRACION,null, generarCalibracion(calibracion));
    }

    /**
     *
     * @param cero
     * @return
     */
    private ContentValues generarCero(DBcero cero)
    {
        ContentValues valores = new ContentValues();
        valores.put(DBcero.FCERO_ID, cero.getIdCero());
        valores.put(DBcero.FCERO_CERO, cero.getCero());

        return valores;
    }
    public void actualizarCero(DBcero cero, String id)
    {
        db.update(DBcero.TABLE_NAME_CERO, generarCero(cero), DBcalibracion.FCAL_ID+ "=?", new String[]{id});
    }
    private void InsetarCero(DBcero cero){
        db.insert(DBcero.TABLE_NAME_CERO,null, generarCero(cero));
    }

}



