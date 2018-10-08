package com.example.dramirez.garrraspuertoserie;


import android.app.Fragment;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.net.Uri;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dramirez.garrraspuertoserie.Base_de_Datos.BaseDeDatos;

import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBcero;
import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBpesadas;


import java.sql.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Principal extends AppCompatActivity {

    managerPort Port = null;
    ProgressBar progressBar;

    TextView tv, txtCargio;
    BaseDeDatos db;
    String[] numeros = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    String[] nombres = {"Pino", "Eucaliptus","Producto 1","Producto 2","Producto 3","Producto 4","Producto 5",
            "Producto 6","Producto 7","Producto 8","Producto 9","Producto 10"};

    int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;

    TextView txtPesoGarra,txt_Peso_Acumulado, txtFecha, txtHora;
    Variables var = new Variables();
    EditText edt_patente, edt_codigo, edt_producto, edt_cliente, edt_tara, edt_volumen;

    String TAG = "PRUEBA DE CARAGA CALIBRACION";
    ImageButton btnCero;
    LinearLayout Layout_Total_Cargado;
    ImageView imgEstable;
    String desde, hasta;
    String[] arrayMenu;
    String Fecha_Peso;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    ImageButton btnGuardar;

    // Used to load the 'native-lib' library oon application startup.
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
        txtFecha =(TextView)findViewById(R.id.txtFecha);
        txtHora =(TextView)findViewById(R.id.txtHora);
        txtCargio = (TextView)findViewById(R.id.txtCargio);
        txt_Peso_Acumulado = (TextView)findViewById(R.id.txt_Peso_Acumulado);
        edt_patente = (EditText)findViewById(R.id.edt_patente);
        edt_codigo = (EditText)findViewById(R.id.edt_codigo);
        edt_producto = ( EditText)findViewById(R.id.edt_producto);
        edt_cliente = (EditText) findViewById(R.id.edt_cliente);
        edt_tara = (EditText) findViewById(R.id.edt_tara);
        edt_volumen = (EditText)findViewById(R.id.edt_volumen);
        Layout_Total_Cargado = (LinearLayout)findViewById(R.id.Layout_Total_Cargado);
        imgEstable = (ImageView)findViewById(R.id.imgEstable);
        btnGuardar = (ImageButton)findViewById(R.id.btnGuardar);
        btnGuardar.setEnabled(false);
        arrayMenu = getResources().getStringArray(R.array.dialogo_menu);



        db = new BaseDeDatos(getApplicationContext());
        /**
         * Para mostrar la fecha en pantalla y guardar la fecha en elka pesada
         */

        String fecha = new SimpleDateFormat("dd/MM/yyyy").format (new Date());
        java.util.Date fechaConvertida = new java.util.Date();
        try {
            fechaConvertida =  dateFormat.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Fecha para guerdar la pesada
        Fecha_Peso=String.valueOf(fechaConvertida.getTime());
        //Fecha para mostrar
        txtFecha.setText(fecha);
        /**
         * *****************************************************************************************
         */
        Port = new managerPort("/dev/ttyS3",115200);

        Balanza.getInstance().setDriverCelda(new DriverCeldaSerie(Port), var, this);
        Balanza.getInstance().Loop();
        Balanza.getInstance().LoopAcumulador();

        Layout_Total_Cargado.setVisibility(View.INVISIBLE);
        comprobarBaseDeDatos();
        pesaje();
        goFullScreen();

        btnCero.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Balanza.getInstance().setearCero();
                ActualizarCero();
                return false;
            }
        });

        if (!var.isRELOJ())
        {
            Runnable runnable = new CountDownRunner();
            Thread myThread = new Thread(runnable);
            myThread.start();
        }
    }

    private void ActualizarCero() {
        try {
            String cero = String.valueOf(Balanza.getInstance().getCERO());
            ArrayList<DBcero> arrayCero = new ArrayList<DBcero>(Arrays.asList(new DBcero(cero)));
            DBcero dBcero = arrayCero.get(0);
            db.actualizarCero(dBcero,"1");
        }catch (Exception e){

        }

    }

    public  void onClicK(View v)
    {
        switch (v.getId())
        {
            case R.id.btnCargar:
                Balanza.getInstance().setCominzoPesaje(true);
                new dialogoCargaDatos(this);
                Layout_Total_Cargado.setVisibility(View.INVISIBLE);
            break;
            case R.id.btnMenu:
                dialogoMenu();
                break;
            case  R.id.btnGuardar:
                dialogoGuardarPesada();
                break;
        }
    }

    public void dialogoGuardarPesada()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom));
        builder.setTitle(R.string.tiitulo_dialogo_guardar_pesada);
        builder.setCancelable(false)
                .setPositiveButton(R.string.dialgo_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GuardarPesada();
                        dialog.cancel();
                        btnGuardar.setEnabled(false);
                    }
                })
                .setNegativeButton(R.string.dialgo_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        builder.create();
        builder.show();

    }

    public void  GuardarPesada()
    {
        try {
            final ArrayList<DBpesadas> arrayPesadas = new ArrayList<>(Arrays.asList(new DBpesadas(Fecha_Peso,
                    txtHora.getText().toString(),edt_producto.getText().toString(),txtCargio.getText().toString(),
                    edt_patente.getText().toString(),edt_tara.getText().toString(),edt_volumen.getText().toString(),
                    edt_codigo.getText().toString(),txt_Peso_Acumulado.getText().toString())));
            DBpesadas dBpesadas;
            dBpesadas = arrayPesadas.get(0);
            db.InsertarPesadas(dBpesadas);
            if (arrayPesadas.isEmpty()){
                Toast.makeText(getBaseContext(),"No se pudo guardar la pesada",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getBaseContext(),"La pesada se guardo correctamente",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(getBaseContext(),"EXCEPCION No se guardo la pesada",Toast.LENGTH_LONG).show();
        }


    }

    public void actualizarAcumilaror()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt_Peso_Acumulado.setText(String.valueOf(Balanza.getInstance().getPesoAcumulado()));
            }
        });

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
            contentCalibracion.put("celdas","40000");
            contentCalibracion.put("division","20");
            contentCalibracion.put("sensibilidad","2000");
            contentCalibracion.put("ventana","3");
            contentCalibracion.put("kgfiltro","1000");
            contentCalibracion.put("conversiones","10");
            contentCalibracion.put("recortes","1");
            var.setCAPACIDAD("10000");
            var.setCELDAS("40000");
            var.setDIVISION("20");
            var.setSENSIBILIDAD("2000");
            var.setVENTANA(Integer.valueOf("3"));
            var.setKGFILTRO(Integer.valueOf("1000"));
            var.setCONVERSIONES("10");
            var.setRECORTES("1");
            var.setLOGICA("0");
            db.db.insert("tcalibracion",null,contentCalibracion);
        }else{
            var.setCAPACIDAD(cursorCalibracion.getString(1));
            var.setCELDAS(cursorCalibracion.getString(2));
            var.setDIVISION(cursorCalibracion.getString(3));
            var.setSENSIBILIDAD(cursorCalibracion.getString(4));
            var.setVENTANA(cursorCalibracion.getInt(5));
            var.setKGFILTRO(cursorCalibracion.getInt(6));
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
            final EditText edt_dialogo_patente = (EditText)dialog.findViewById(R.id.edt_dialogo_patente);
            final EditText edt_dialogo_cliente = (EditText)dialog.findViewById(R.id.edt_dialogo_cliente);
            final EditText edt_dialogo_tara = (EditText)dialog.findViewById(R.id.edt_dialogo_tara);
            final EditText edt_dialogo_codigo = (EditText)dialog.findViewById(R.id.edt_dialogo_codigo);
            final EditText edt_dialogo_volumen = (EditText)dialog.findViewById(R.id.edt_dialogo_volumen);
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
                    String producto= productoSeleccionado , patente= edt_dialogo_patente.getText().toString(),
                            tara= edt_dialogo_tara.getText().toString() ,volumen = edt_dialogo_volumen.getText().toString()
                            ,codigo= edt_dialogo_codigo.getText().toString(), cliente = edt_dialogo_cliente.getText().toString();

                    edt_patente.setText(patente);
                    edt_codigo.setText(codigo);
                    edt_producto.setText(producto);
                    edt_cliente.setText(cliente);
                    edt_tara.setText(tara);
                    edt_volumen.setText(volumen);


                    dialog.dismiss();
                    Layout_Total_Cargado.setVisibility(View.VISIBLE);
                    Balanza.getInstance().setCominzoPesaje(true);
                    /**
                     * Pone el acumulador en 0
                     */
                    Balanza.getInstance().setPesoAcumulado();
                    btnGuardar.setEnabled(true);
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

    public void dialogoMenu()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom));
        builder.setTitle(R.string.menu_titulo);
        builder.setItems(R.array.dialogo_menu, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which)
                {
                    case 0:
                        Balanza.getInstance().paraPedido();
                        Intent i = new Intent(getBaseContext(),Calibracion.class);
                        startActivity(i);
                        break;
                    case 1:
                        Intent p = new Intent(getBaseContext(),Productos.class);
                        startActivity(p);
                        break;
                    case 2:
                           dialogoPesadas();
                        break;
                    case 3:

                        break;


                }
                dialog.cancel();
            }
        });
         builder.create();
         builder.show();
    }

    protected void showInputDialog_EntreFecha()
    {
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        final String[] tipo = new String[1];
        LayoutInflater layoutInflater = LayoutInflater.from(Principal.this);
        View promptView = layoutInflater.inflate(R.layout.input_entre_fechas, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.TemaGeneral));
        alertDialogBuilder.setView(promptView);
        final Calendar c = Calendar.getInstance();
        final DatePicker inicio = (DatePicker) promptView.findViewById(R.id.datePicker);
        final DatePicker fin = (DatePicker) promptView.findViewById(R.id.datePicker2);
        int hdia = fin.getDayOfMonth();
        int hmes = fin.getMonth();
        int hano = fin.getYear();
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int dia = inicio.getDayOfMonth();
                        int mes = inicio.getMonth() + 1;
                        int ano = inicio.getYear();

                        if (dia < 10) {
                            desde = String.valueOf("0" + dia + "/");
                            if (mes < 10) {
                                desde = desde + String.valueOf("0" + mes + "/" + ano);
                            } else {
                                desde = desde + String.valueOf(mes + "/" + ano);
                            }
                        } else {
                            desde = String.valueOf(dia + "/");
                            if (mes < 10) {
                                desde = desde + String.valueOf("0" + mes + "/" + ano);
                            } else {
                                desde = desde + String.valueOf(mes + "/" + ano);
                            }
                        }
                        int hdia = fin.getDayOfMonth();
                        int hmes = fin.getMonth() + 1;
                        int hano = fin.getYear();

                        if (hdia < 10) {
                            hasta = String.valueOf("0" + hdia + "/");
                            if (hmes < 10) {
                                hasta = hasta + String.valueOf("0" + hmes + "/" + hano);
                            } else {
                                hasta = hasta + String.valueOf(hmes + "/" + hano);
                            }
                        } else {
                            hasta = String.valueOf(hdia + "/");
                            if (hmes < 10) {
                                hasta = hasta + String.valueOf("0" + hmes + "/" + hano);
                            } else {
                                hasta = hasta + String.valueOf(hmes + "/" + hano);
                            }
                        }

                        Intent i = new Intent(getBaseContext(), Pesadas.class);
                        i.putExtra("inicio", desde);
                        i.putExtra("final", hasta);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public  void  dialogoPesadas()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));

        builder.setTitle("Seleccione el filtro ...")
                .setItems(R.array.FiltrosPesadas, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which)
        {
            switch (which)
            {
                case 0:

                    return;
                case 1:

                    return;
                case 2:

                    return;
                case 3:
                        showInputDialog_EntreFecha();
                    return;
                case 4:

                    return;
                case 5:

                    break;
                case 6:

                    break;
            }
        }
    });
        builder.create();
        builder.show();
    }

    // Todo : Dialogo para editar la calibración //
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
                                if (Balanza.getInstance().isEstable()){
                                    imgEstable.setBackgroundResource(R.drawable.circulo_estable);
                                }else{
                                    imgEstable.setBackgroundResource(R.drawable.circulo_inestable);
                                }
                                actualizarAcumilaror();
                            }
                        });
                        Thread.sleep(100);
                    }catch (Exception e){

                    }
                }
            }
        }).start();
    }

    class CountDownRunner implements Runnable
    {
        @Override
        public void run()
        {
            while (!Thread.currentThread().isInterrupted()) {
                if (!var.isRELOJ())
                {
                    var.setRELOJ(true);
                }
                try {
                    doWork();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                }
            }
        }

        private void doWork()
        {
            runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        Date dt = new Date();
                        int hours = dt.getHours();
                        int minutes = dt.getMinutes();
                        int seconds = dt.getSeconds();
                        String curTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
                        txtHora.setText(curTime);
                    } catch (Exception e) {
                    }
                }
            });
        }
    }
    private void goFullScreen()
    {
        // Only navigation will be shown when opening a spinner
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
     //public native String stringFromJNI();
}
