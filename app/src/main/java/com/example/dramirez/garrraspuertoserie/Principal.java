package com.example.dramirez.garrraspuertoserie;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dramirez.garrraspuertoserie.Base_de_Datos.BaseDeDatos;
import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBcalibracion;
import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBcero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Principal extends AppCompatActivity {

    managerPort Port = null;


    TextView tv;
    BaseDeDatos db;
    String[] numeros = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    String[] nombres = {"Pino", "Eucaliptus","Producto 1","Producto 2","Producto 3","Producto 4","Producto 5",
            "Producto 6","Producto 7","Producto 8","Producto 9","Producto 10"};

    int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;

    TextView txtPesoGarra,txt_Peso_Acumulado;
    Variables var = new Variables();
    EditText edt_patente, edt_codigo, edt_producto, edt_cliente, edt_tara, edt_volumen;

    String TAG = "PRUEBA DE CARAGA CALIBRACION";
    ImageButton btnCero;
    LinearLayout Layout_Total_Cargado;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);

        txtPesoGarra = (TextView)findViewById(R.id.txtPesoGarra);
        btnCero = (ImageButton) findViewById(R.id.btnCero);

        txt_Peso_Acumulado = (TextView)findViewById(R.id.txt_Peso_Acumulado);
        edt_patente = (EditText)findViewById(R.id.edt_patente);
        edt_codigo = (EditText)findViewById(R.id.edt_codigo);
        edt_producto = ( EditText)findViewById(R.id.edt_producto);
        edt_cliente = (EditText) findViewById(R.id.edt_cliente);
        edt_tara = (EditText) findViewById(R.id.edt_tara);
        edt_volumen = (EditText)findViewById(R.id.edt_volumen);
        Layout_Total_Cargado = (LinearLayout)findViewById(R.id.Layout_Total_Cargado);



        btnCero.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Balanza.getInstance().setearCero();
                ActualizarCero();
                return false;
            }
        });

        db = new BaseDeDatos(getApplicationContext());

        Port = new managerPort("/dev/ttyS3",115200);

        Balanza.getInstance().setDriverCelda(new DriverCeldaSerie(Port),var);
        Balanza.getInstance().Loop();


        //Notify.addObserver(this);

        Layout_Total_Cargado.setVisibility(View.INVISIBLE);
        comprobarBaseDeDatos();
        pesaje();

    }

    private void ActualizarCero() {
        String cero = String.valueOf(Balanza.getCERO());
        ArrayList<DBcero> arrayCero = new ArrayList<DBcero>(Arrays.asList(new DBcero(cero)));
        DBcero dBcero = arrayCero.get(0);
        db.actualizarCero(dBcero,"1");
    }

    public  void onClicK(View v)
    {

        /**
            Todos los eventos de botones
        */

        switch (v.getId()){
            case R.id.btn_Cargar:
                new dialogoCargaDatos(this);
            break;
            case R.id.btn_datos:


                break;
            case  R.id.btn_productos:

                new dialogoEdicionProductos(this);
                break;
            case  R.id.btnCalibracion:
                Balanza.getInstance().paraPedido();
                new dialogoCalibracion(this);
                break;
        }

    }

    public void comprobarBaseDeDatos()
    {
        /**  Todo: Busca si ya estan guardados los productos */

        ContentValues contentProductos = new ContentValues();
        Cursor cursorProductos = db.db.rawQuery("SELECT nombre FROM tproductos", null);
        if (!cursorProductos.moveToFirst())
        {
            for (int i = 0;i <= 11; i++)
            {
                String NOMBRE = "";
                contentProductos.put("numero",numeros[i]);
                contentProductos.put("nombre",nombres[i]);
                Log.d(NOMBRE,"*********  " + nombres[i]+" ***********");
                db.db.insert("tproductos", null,contentProductos);
            }
        }
        ContentValues contentCalibracion = new ContentValues();
        Cursor cursorCalibracion = db.db.rawQuery("SELECT * FROM tcalibracion", null);
        if (!cursorCalibracion.moveToFirst())
        {
            contentCalibracion.put("capacidad","10000");
            contentCalibracion.put("celdas","30000");
            contentCalibracion.put("division","20");
            contentCalibracion.put("sensibilidad","2000");
            contentCalibracion.put("ventana","3");
            contentCalibracion.put("kgfiltro","1000");
            contentCalibracion.put("conversiones","1");
            contentCalibracion.put("recortes","0");
            db.db.insert("tcalibracion",null,contentCalibracion);
        }else{
            var.setCAPACIDAD(cursorCalibracion.getString(1));
            var.setCELDAS(cursorCalibracion.getString(2));
            var.setDIVISION(cursorCalibracion.getString(3));
            var.setSENSIBILIDAD(cursorCalibracion.getString(4));
            var.setVENTANA(cursorCalibracion.getString(5));
            var.setKGFILTRO(cursorCalibracion.getString(6));
            var.setCONVERSIONES(cursorCalibracion.getString(7));
            var.setRECORTES(cursorCalibracion.getString(8));
        }
        ContentValues contentCero = new ContentValues();
        Cursor cursorCero = db.db.rawQuery("SELECT * FROM tcero",null);

        if (cursorCero.moveToFirst())
        {
            int cero =Integer.valueOf(cursorCero.getString(1));
            Balanza.getInstance().setCERO(cero);
            //Log.d("CERO", "**************** ENTRO EN LA TABLA CERO :" + cero );
        }else
        {
            contentCero.put("cero", "0");
            db.db.insert("tcero",null,contentCero);
           // Log.d("CERO", "**************** NO ENTRO EN LA TABLA CERO" );
        }

    }

    @SuppressLint("ValidFragment")
    public class dialogoCargaDatos
    {
        String productoSeleccionado;

        public dialogoCargaDatos(Context context){
            final Dialog dialog = new Dialog(context,R.style.Material_Base);

            dialog.setCancelable(false);
            dialog.setTitle(R.string.dialogo_titulo);
            dialog.setContentView(R.layout.dialogo_carga_datos);

            List<String> productos = db.getAllProductos();



            final Spinner sp_producto = (Spinner) dialog.findViewById(R.id.sp_producto);
            final EditText edt_patente = (EditText)dialog.findViewById(R.id.edt_patente);
            final EditText edt_cliente = (EditText)dialog.findViewById(R.id.edt_cliente);
            final EditText edt_tara = (EditText)dialog.findViewById(R.id.edt_dialogo_tara);
            final EditText edt_codigo = (EditText)dialog.findViewById(R.id.edt_dialogo_patente);
            final EditText edt_volumen = (EditText)dialog.findViewById(R.id.edt_dialogo_volumen);
            Button aceptar = (Button)dialog.findViewById(R.id.btn_acrptar);
            Button cancelar = (Button)dialog.findViewById(R.id.btn_Cancelar);

            ArrayAdapter<String> dataAdapter =  new ArrayAdapter<String>(getBaseContext(),R.layout.support_simple_spinner_dropdown_item, productos);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
            sp_producto.setAdapter(dataAdapter);

            sp_producto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    productoSeleccionado = parent.getItemAtPosition(position).toString();
                    //var.setCAPACIDAD( parent.getItemAtPosition(position).toString());
                }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String producto= edt_producto.getText().toString(), patente= edt_patente.getText().toString(),
                            tara= edt_tara.getText().toString() ,volumen = edt_volumen.getText().toString()
                            ,codigo= edt_codigo.getText().toString(), cliente = edt_cliente.getText().toString();

                    edt_patente.setText(patente);
                    edt_codigo.setText(codigo);
                    edt_producto.setText(producto);
                    edt_cliente.setText(cliente);
                    edt_tara.setText(tara);
                    edt_volumen.setText(volumen);

                    Layout_Total_Cargado.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    @SuppressLint("ValidFragment")
    public class dialogoEdicionProductos
    {
        public dialogoEdicionProductos(Context context){
            final Dialog dialog = new Dialog(context,R.style.Material_Base);

            dialog.setCancelable(false);
            dialog.setTitle(R.string.dialogo_titulo);
            dialog.setContentView(R.layout.dialogo_edicion_productos);

            final EditText edt_producto[]= new EditText[12];

            edt_producto[0] = (EditText)dialog.findViewById(R.id.edt_productos1);
            edt_producto[1] = (EditText)dialog.findViewById(R.id.edt_productos2);
            edt_producto[2] = (EditText)dialog.findViewById(R.id.edt_productos3);
            edt_producto[3] = (EditText)dialog.findViewById(R.id.edt_productos4);
            edt_producto[4] = (EditText)dialog.findViewById(R.id.edt_productos5);
            edt_producto[5] = (EditText)dialog.findViewById(R.id.edt_productos6);
            edt_producto[6] = (EditText)dialog.findViewById(R.id.edt_productos7);
            edt_producto[7] = (EditText)dialog.findViewById(R.id.edt_productos8);
            edt_producto[8] = (EditText)dialog.findViewById(R.id.edt_productos9);
            edt_producto[9] = (EditText)dialog.findViewById(R.id.edt_productos10);
            edt_producto[10] = (EditText)dialog.findViewById(R.id.edt_productos11);
            edt_producto[11] = (EditText)dialog.findViewById(R.id.edt_productos12);

            Button aceptar = (Button)dialog.findViewById(R.id.btn_dialogo_productos_aceptar);
            Button cancelar = (Button)dialog.findViewById(R.id.btn_dialogo_productos_cancelar);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Cursor c = db.db.rawQuery("SELECT nombre FROM tproductos",null);
                    if (c.moveToFirst())
                    {
                        for (int i = 0; i <= c.getCount()-1; i++)
                        {
                            nombres[i] = c.getString(0);
                            edt_producto[i].setText(c.getString(0));
                            c.moveToNext();
                        }
                    }
                }
            });
           aceptar.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("LongLogTag")
                @Override
                public void onClick(View v) {
                    ContentValues contentValues = new ContentValues();
                    String GUARDADO = "Se ha guardado producto :";
                    for (int i = 0; i <= 11; i++ )
                        {
                            contentValues.put("numero",String.valueOf(i+1));
                            contentValues.put("nombre",edt_producto[i].getText().toString());
                            db.db.update("tproductos",contentValues,"numero="+ numeros[i],null);
                            Log.d(GUARDADO,"*********** " + edt_producto[i].getText().toString() +" <<<<<<<<<<<");
                        }
                    Toast.makeText(getApplicationContext(),"Los productos se guardaron correctamente",Toast.LENGTH_LONG).show();

                    dialog.dismiss();
                }
            });
            cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    // Todo : Dialogo para editar la calibración //
    public class dialogoCalibracion
    {
        public dialogoCalibracion(Context context)
        {
            final  Dialog dialog = new Dialog(context,R.style.Material_Base);
            dialog.setCancelable(false);
            dialog.setTitle(R.string.dialogo_titulo);
            dialog.setContentView(R.layout.dialogo_calibracion);

            final EditText edtPesoMaximo = (EditText)dialog.findViewById(R.id.edtCapacidadMaxima);
            final EditText edtTotalCeldas = (EditText)dialog.findViewById(R.id.edtTotalCeldas);
            final EditText edtDivision = (EditText)dialog.findViewById(R.id.edtDivisionMinima);
            final EditText edtSensibilidad = (EditText)dialog.findViewById(R.id.edtSensibilidad);
            final EditText edtVentana = (EditText)dialog.findViewById(R.id.edtVentana);
            final EditText edtKgFiltro = (EditText)dialog.findViewById(R.id.edtKgFiltro);
            final EditText edtConversiones = (EditText)dialog.findViewById(R.id.edtConversiones);
            final EditText edtRecortes = (EditText)dialog.findViewById(R.id.edtRecortes);
            Button aceptarCalibracion = (Button)dialog.findViewById(R.id.btnCalibracionAceptar);
            Button cancelarCalibracion = (Button)dialog.findViewById(R.id.btnCalibracionCancelar);

            Cursor cursor = db.db.rawQuery("SELECT * FROM tcalibracion",null);
            if (cursor.moveToFirst()){
                edtPesoMaximo.setText(cursor.getString(1));
                edtTotalCeldas.setText(cursor.getString(2));
                edtDivision.setText(cursor.getString(3));
                edtSensibilidad.setText(cursor.getString(4));
                edtVentana.setText(cursor.getString(5));
                edtKgFiltro.setText(cursor.getString(6));
                edtConversiones.setText(cursor.getString(7));
                edtRecortes.setText(cursor.getString(8));
                Log.d(TAG,"<<<<<<<<<<<" + cursor.getCount() );
            }else{
                Log.d(TAG,"NO CARGO NADA" );
            }
            aceptarCalibracion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        String capac ="",celdas ="", division ="", sensib ="", vent ="", kgF ="",conv ="", recort ="",logica ="";
                        capac = edtPesoMaximo.getText().toString();
                        celdas = edtTotalCeldas.getText().toString();
                        division = edtDivision.getText().toString();
                        sensib = edtSensibilidad.getText().toString();
                        vent = edtVentana.getText().toString();
                        kgF = edtKgFiltro.getText().toString();
                        conv = edtConversiones.getText().toString();
                        recort =edtRecortes.getText().toString();
                        logica = "";
                        ArrayList<DBcalibracion> arrayCalibracion = new ArrayList<DBcalibracion>(Arrays.asList(new DBcalibracion(capac,celdas,
                                              division,sensib,vent,kgF,conv,recort)));

                        DBcalibracion CALI = arrayCalibracion.get(0);

                        db.actualizarCalibracion(CALI,"1");
                        String envio = "AT+PARAM=" + conv +","+ recort + "," + logica + '\r'+'\n';
                        Balanza.getInstance().EnviarCalibracion(envio);

                        Toast.makeText(getBaseContext(),R.string.mensaje_guardado_calibracion,Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        Toast.makeText(getBaseContext(),R.string.mensaje_guardado_calibracion_error,Toast.LENGTH_LONG).show();
                    }

                    dialog.dismiss();
                }
            });
            cancelarCalibracion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    public void upDateProducto(){

    }

    public void pesaje ()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    try{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Todo: presicion este valor se deberia tomar de la division en calibracion
                                /**
                                 *
                                 */

                                txtPesoGarra.setText(String.format ("% .0f", Balanza.getInstance().getPesoFisico()));
                            }
                        });
                        Thread.sleep(100);
                    }catch (Exception e){

                    }
                }
            }
        }).start();
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
     //public native String stringFromJNI();
}
