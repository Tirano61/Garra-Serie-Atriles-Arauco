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
        db.execSQL(DBcabecera.TAB_CABECERA);
        db.execSQL(DBcelda.TAB_CELDA);
        db.execSQL(DBoperadores.TAB_OPERADORES);
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
        db.execSQL("DROP TABLE IF EXIXTS" + DBcabecera.TABLE_NAME_CABECERA);
        db.execSQL("DROP TABLE IF EXIXTS" + DBcelda.TABLE_NAME_CELDA);
        db.execSQL("DROP TABLE IF EXIXTS" + DBoperadores.TABLE_NAME_OPERADORES);
        onCreate(db);
    }

    private ContentValues generarOperadores(DBoperadores operadores){
        ContentValues values  = new ContentValues();
        values.put(DBoperadores.FOP_CODIGO, operadores.getCod_operador());
        values.put(DBoperadores.FOP_NOMBRE, operadores.getNombre());
        return values;
    }
    public void InsertarOperadores(DBoperadores operadores)
    {
        db.insert(DBoperadores.TABLE_NAME_OPERADORES, null, generarOperadores(operadores));
    }
    private ContentValues generarCorreccion(DBcorreccion correccion)
    {
        ContentValues values  = new ContentValues();
        values.put(DBcorreccion.FCO_PORC, correccion.getPorcentaje());
        return values;
    }

    public void actualizarCorreccion(DBcorreccion correccion, String id)
    {

        db.update(DBcorreccion.TABLE_NAME_CORRECCION, generarCorreccion(correccion), DBcorreccion.FCO_ID+ "=?", new String[]{id});
    }

    private ContentValues generarCabecera(DBcabecera cabecera)
    {
        //falta el cliente
        ContentValues valores = new ContentValues();
        valores.put(DBcabecera.FCAB_UNO, cabecera.getUno());
        valores.put(DBcabecera.FCAB_DOS, cabecera.getDos());
        valores.put(DBcabecera.FCAB_TRES, cabecera.getTres());
        valores.put(DBcabecera.FCAB_CUATRO, cabecera.getCuatro());

        return valores;
    }
    public void InsertarCabecera(DBcabecera cabecera)
    {
        db.insert(DBcabecera.TABLE_NAME_CABECERA, null, generarCabecera(cabecera));
    }
    public void actualizarCabecera(DBcabecera cabecera, String id)
    {
        db.update(DBcabecera.TABLE_NAME_CABECERA, generarCabecera(cabecera), DBcabecera.FCAB_ID+ "=?", new String[]{id});
    }
    private ContentValues generarPesadas(DBpesadas pesadas)
    {
        ContentValues valores = new ContentValues();
        valores.put(DBpesadas.FPES_FECHA, pesadas.getFecha());
        valores.put(DBpesadas.FPES_HORA, pesadas.getHora());
        valores.put(DBpesadas.FPES_ID_GRUA, pesadas.getIdgrua());
        valores.put(DBpesadas.FPES_CHASIS, pesadas.getChasis());
        valores.put(DBpesadas.FPES_ACOPLADO, pesadas.getAcoplado());
        valores.put(DBpesadas.FPES_REMITO, pesadas.getRemito());
        valores.put(DBpesadas.FPES_DESTINO, pesadas.getDestino());
        valores.put(DBpesadas.FPES_PRODUCTO, pesadas.getProducto());
        valores.put(DBpesadas.FPES_MEDIDA_ASERRABLE, pesadas.getMedida_aserrable());
        valores.put(DBpesadas.FPES_RODAL, pesadas.getRodal());
        valores.put(DBpesadas.FPES_FECHA_CORTE, pesadas.getFecha_corte());
        valores.put(DBpesadas.FPES_OPERADOR, pesadas.getOperador());
        valores.put(DBpesadas.FPES_ACTA_INTERVENCION, pesadas.getActa_intervencion());
        valores.put(DBpesadas.FPES_ITPO_INTERVENCION, pesadas.getTipo_intervencion());
        valores.put(DBpesadas.FPES_PREDIO, pesadas.getPredio());
        valores.put(DBpesadas.FPES_UMF, pesadas.getUmf());
        valores.put(DBpesadas.FPES_PROVEEDOR_ELAVORACION, pesadas.getProveedor_elavoracion());
        valores.put(DBpesadas.FPES_PROVEEDOR_CARGA, pesadas.getProveedor_carga());
        valores.put(DBpesadas.FPES_RAIZ_REMITO, pesadas.getRaiz_remito());
        valores.put(DBpesadas.FPES_BANCOS, pesadas.getBancos());
        valores.put(DBpesadas.FPES_BANCO1, pesadas.getBanco1());
        valores.put(DBpesadas.FPES_BANCO2, pesadas.getBanco2());
        valores.put(DBpesadas.FPES_BANCO3, pesadas.getBanco3());
        valores.put(DBpesadas.FPES_BANCO4, pesadas.getBanco4());
        valores.put(DBpesadas.FPES_BANCO5, pesadas.getBanco5());
        valores.put(DBpesadas.FPES_BANCO6, pesadas.getBanco6());
        valores.put(DBpesadas.FPES_BANCO7, pesadas.getBanco7());
        valores.put(DBpesadas.FPES_BANCO8, pesadas.getBanco8());
        valores.put(DBpesadas.FPES_BANCO9, pesadas.getBanco9());
        valores.put(DBpesadas.FPES_CARGAS, pesadas.getCargas());
        valores.put(DBpesadas.FPES_BRUTO, pesadas.getBruto());
        valores.put(DBpesadas.FPES_TARA, pesadas.getTara());
        valores.put(DBpesadas.FPES_NETO, pesadas.getNeto());
        valores.put(DBpesadas.FPES_TIEMPO_CARGA, pesadas.getTiempo_carga());
        return valores;
    }
    public void InsertarPesadas(DBpesadas pesadas)
    {
        db.insert(DBpesadas.TABLE_NAME_PESADAS, null, generarPesadas(pesadas));
    }
    public void borrarPesadas()
    {
        db.execSQL("DELETE FROM " + DBpesadas.TABLE_NAME_PESADAS);
    }

    private ContentValues generarDatos(DBdatos datos)
    {
        //falta el cliente
        ContentValues valores = new ContentValues();
        valores.put(DBdatos.FDAT_ID_GRUA, datos.getId_grua());
        valores.put(DBdatos.FDAT_REMITO, datos.getRemito());
        valores.put(DBdatos.FDAT_DESTINO, datos.getDestino());
        valores.put(DBdatos.FDAT_PRODUCTO, datos.getProducto());
        valores.put(DBdatos.FDAT_MEDIDA, datos.getMedida_aserrable());
        valores.put(DBdatos.FDAT_RODAL, datos.getOperador());
        valores.put(DBdatos.FDAT_FECHA_CORTE, datos.getFecha_corte());
        valores.put(DBdatos.FDAT_OPERADOR, datos.getOperador());
        valores.put(DBdatos.FDAT_ACTA_INTERVENCION, datos.getActa_intervencion());
        valores.put(DBdatos.FDAT_TIPO_INTERVENCION, datos.getTipo_intervencion());
        valores.put(DBdatos.FDAT_PREDIO, datos.getPredio());
        valores.put(DBdatos.FDAT_UMF, datos.getUmf());
        valores.put(DBdatos.FDAT_PROVEEDOR_ELAVORACION, datos.getProveedor_elavoracion());
        valores.put(DBdatos.FDAT_PROVEEDOR_CARGA, datos.getProveedor_carga());
        valores.put(DBdatos.FDAT_RAIZ_REMITO, datos.getRaiz_remito());

        return valores;
    }
    private void InsetarDatos(DBdatos datos){
        db.insert(DBdatos.TABLE_NAME_DATOS,null, generarDatos(datos));
    }
    public void actualizarDatos(DBdatos datos, String id)
    {
        db.update(DBdatos.TABLE_NAME_DATOS, generarDatos(datos), DBdatos.FDAT_ID+ "=?", new String[]{id});
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
            productos.add("");
            do
            {
                productos.add(cursor.getString(1));
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        return  productos;
    }
    public List<String> getAllOperadores()
    {
        List<String> operadores = new ArrayList<String>();

        String selectQuery = "SELECT cod_operador, nombre FROM " + DBoperadores.TABLE_NAME_OPERADORES +" ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
        {
            operadores.add("");
            operadores.add("");
            do
            {
                operadores.add(cursor.getString(1));
            }
            while (cursor.moveToNext());
        }else{
            operadores.add("");
            operadores.add("");
        }
        cursor.close();
        return  operadores;
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
        valores.put(DBcalibracion.FCAL_LOGICA, calibracion.getLogica());
        valores.put(DBcalibracion.FCAL_TICKET, calibracion.getTicket());
        valores.put(DBcalibracion.FCAL_SEMI, calibracion.getSemiaut());
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

    private ContentValues generarCelda(DBcelda celda)
    {
        ContentValues valores = new ContentValues();
        valores.put(DBcelda.FCELDA_CELDA, celda.getCelda());

        return valores;
    }
    public void actualizarCelda(DBcelda celda, String id)
    {
        db.update(DBcelda.TABLE_NAME_CELDA, generarCelda(celda), DBcelda.FCELDA_ID+ "=?", new String[]{id});
    }
    private void InsetarCelda(DBcelda celda){
        db.insert(DBcelda.TABLE_NAME_CELDA,null, generarCelda(celda));
    }
}



