package com.example.dramirez.garrraspuertoserie;



import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Color;
import android.net.Uri;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBcabecera;
import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBcelda;
import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBcero;
import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBcorreccion;
import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBdatos;
import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBpesadas;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Principal extends AppCompatActivity implements Fragment_tre_bancos.OnFragmentInteractionListener,
Fragment_cuatro_bancos.OnFragmentInteractionListener, Fragment_dos_bancos.OnFragmentInteractionListener,
Fragment_cinco_bancos.OnFragmentInteractionListener, Fragment_seis_bancos.OnFragmentInteractionListener{

    managerPort Port = null;
    ProgressBar progressBar;

    TextView tv, txtCargio;
    BaseDeDatos db;
    String[] numeros = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    String[] nombres = {"Pino", "Eucaliptus","Producto 1","Producto 2","Producto 3","Producto 4","Producto 5",
            "Producto 6","Producto 7","Producto 8","Producto 9","Producto 10"};

    int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;

    TextView txtPesoGarra,txt_Peso_Acumulado, txtFecha, txtHora, txtBateria;
    Variables var = new Variables();
    EditText edt_patente, edt_codigo, edt_producto, edt_cliente, edt_tara, edt_volumen;

    String TAG = "PRUEBA DE CARAGA CALIBRACION";
    ImageButton btnCero,btnRestar;
    LinearLayout Layout_Total_Cargado;
    ImageView imgEstable, imgBateria, imgConexionSerie,imgDescargando;
    String desde, hasta;
    String[] arrayMenu;
    String Fecha_Peso;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    ImageButton btnGuardar, btnCargar;
    String fecha;
    Calendar calendar = Calendar.getInstance();
    ArrayList<String> datos;
    Runnable _Runnable = new Exportar_Excel();
    Thread excel = new Thread(_Runnable);
    int netoDescargado;
    boolean cuentaLeedEmpezada = false;
    int TipoCelda,tipoCeldaAEnviar;
    String numero, ssd, pass, puerto , envio;
    Spinner spTipo_equio, spTipo_carga;

    Fragment_tre_bancos fragment_tres_bancos;
    Fragment_dos_bancos fragment_dos_banco;
    Fragment_cuatro_bancos fragment_cuatro_bancos;
    Fragment_cinco_bancos fragment_cinco_bancos;
    Fragment_seis_bancos fragment_seis_bancos;

    LinearLayout SelectorChasis2_1,SelectorChasis2_2,SelectorChasis2_3,SelectorChasis2_4;
    LinearLayout SelectorChasis3_1,SelectorChasis3_2,SelectorChasis3_3,SelectorChasis3_4,SelectorChasis3_5,SelectorChasis3_6;
    TextView txtChasis2_1, txtChasis2_2,txtChasis2_3,txtChasis2_4;
    TextView txtChasis3_1, txtChasis3_2,txtChasis3_3,txtChasis3_4,txtChasis3_5,txtChasis3_6;
    TextView txtChasis1_1,txtChasis1_2;
    String tipoDeCarga = "", equipo = "";
    int bancoSeleccionado =0;
    Handler handler;


    // Used to load the 'native-lib' library oon application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);

        txtPesoGarra = (TextView)findViewById(R.id.txtPesoGarra);
        btnCero = (ImageButton) findViewById(R.id.btnCero);
        txtFecha =(TextView)findViewById(R.id.txtFecha);
        txtHora =(TextView)findViewById(R.id.txtHora);
        txtCargio = (TextView)findViewById(R.id.txtCargio);
        txt_Peso_Acumulado = (TextView)findViewById(R.id.txt_Peso_Acumulado);
        txtBateria = (TextView)findViewById(R.id.txtBateria);
        edt_patente = (EditText)findViewById(R.id.edt_patente);
        edt_codigo = (EditText)findViewById(R.id.edt_codigo);
        edt_producto = ( EditText)findViewById(R.id.edt_producto);
        edt_cliente = (EditText) findViewById(R.id.edt_cliente);
        edt_tara = (EditText) findViewById(R.id.edt_tara);
        edt_volumen = (EditText)findViewById(R.id.edt_volumen);
        Layout_Total_Cargado = (LinearLayout)findViewById(R.id.Layout_Total_Cargado);
        imgEstable = (ImageView)findViewById(R.id.imgEstable);
        imgBateria = (ImageView)findViewById(R.id.imgBateria);
        imgDescargando = (ImageView)findViewById(R.id.imgDescargando);
        imgConexionSerie = (ImageView)findViewById(R.id.imgConexionSerie);
        btnGuardar = (ImageButton)findViewById(R.id.btnGuardar);
        btnCargar = (ImageButton) findViewById(R.id.btnCargar);
        btnRestar =(ImageButton) findViewById(R.id.btnRestar);
        btnGuardar.setEnabled(false);
        arrayMenu = getResources().getStringArray(R.array.dialogo_menu);
        progressBar = (ProgressBar)findViewById(R.id.progressBarImpresion);
        spTipo_carga = (Spinner)findViewById(R.id.spTipo_carga);
        spTipo_equio = (Spinner)findViewById(R.id.spTipo_equipo);


        db = new BaseDeDatos(getApplicationContext());
        comprobarBaseDeDatos();
        /**
         * Para mostrar la fecha en pantalla y guardar la fecha en elka pesada
         */

        fecha = new SimpleDateFormat("dd-MM-yyyy").format (new Date());
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

        pesaje();
        goFullScreen();

        btnCero.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                if (!Balanza.getInstance().isCominzoPesaje()){
                    Balanza.getInstance().setearCero();
                    ActualizarCero();
                }

                return false;
            }
        });
        if (!var.isBATERIA())
        {
            Runnable runnableBateria = new MostrarEstaadoBateria();
            Thread threadBateria = new Thread(runnableBateria);
            threadBateria.start();
        }
        if (!var.isRELOJ())
        {
            Runnable runnable = new CountDownRunner();
            Thread myThread = new Thread(runnable);
            myThread.start();
        }
        fragment_tres_bancos = new Fragment_tre_bancos();
        fragment_dos_banco = new Fragment_dos_bancos();
        fragment_cuatro_bancos = new Fragment_cuatro_bancos();
        fragment_cinco_bancos = new Fragment_cinco_bancos();
        fragment_seis_bancos = new Fragment_seis_bancos();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.pirulo,fragment_dos_banco);
        fragmentTransaction.commit();


    }




    public void actualizarAcumilaror()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (tipoDeCarga){

                    case "2":
                        fragment_dos_banco.recibirPeso(String.valueOf(Balanza.getInstance().getPesoAcumuladoBancos()));
                        break;
                    case "3":
                        fragment_tres_bancos.recibirPeso(String.valueOf(Balanza.getInstance().getPesoAcumuladoBancos()));
                        break;
                    case "4":
                        fragment_cuatro_bancos.recibirPeso(String.valueOf(Balanza.getInstance().getPesoAcumuladoBancos()));
                        break;
                    case "5":
                        fragment_cinco_bancos.recibirPeso(String.valueOf(Balanza.getInstance().getPesoAcumuladoBancos()));
                        break;
                    case "6":
                        fragment_seis_bancos.recibirPeso(String.valueOf(Balanza.getInstance().getPesoAcumuladoBancos()));
                        break;
                }
                txt_Peso_Acumulado.setText(String.valueOf(Balanza.getInstance().getPesoAcumulado()));
            }
        });
    }

    private void ActualizarCero()
    {
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
                if (Balanza.getInstance().isConexionSerie())
                {
                    Balanza.getInstance().setCominzoPesaje(true);
                    new dialogoCargaDatos(this);
                    Layout_Total_Cargado.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Toast.makeText(getBaseContext(),"No se puede comenzar el pesaje, Balanza desconectada!!!",Toast.LENGTH_LONG).show();
                }
            break;
            case R.id.btnMenu:
                dialogoMenu();
                break;
            case  R.id.btnGuardar:
                dialogoGuardarPesada();
                Balanza.getInstance().setCominzoPesaje(false);
                break;
            case R.id.btnAcumular:
                txt_Peso_Acumulado.setText(String.valueOf(Balanza.getInstance().setAcumularPeso()));
                break;
            case  R.id.btnRestar:
                if (var.isRESTAR())
                {
                    var.setRESTAR(false);
                }else{
                    var.setRESTAR(true);
                }

                break;
        }
    }

    public void dialogoGuardarPesada()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom));
        builder.setTitle(R.string.tiitulo_dialogo_guardar_pesada);
        builder.setCancelable(false)
                .setPositiveButton(R.string.dialgo_aceptar, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        var.setTIEMPOCARGA(false);
                        GuardarUltimosDatos();
                        GuardarPesada();
                        dialog.cancel();
                        btnGuardar.setEnabled(false);
                        btnCargar.setEnabled(true);
                    }
                })
                .setNegativeButton(R.string.dialgo_cancelar, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
        builder.create();
        builder.show();

    }

    public void  GuardarPesada()
    {
        try
        {
            netoDescargado = Integer.valueOf(txt_Peso_Acumulado.getText().toString()) - Integer.valueOf(edt_tara.getText().toString());

            final ArrayList<DBpesadas> arrayPesadas = new ArrayList<>(Arrays.asList(new DBpesadas(Fecha_Peso,
                    txtHora.getText().toString(),edt_producto.getText().toString(),txtCargio.getText().toString(),
                    edt_patente.getText().toString(),edt_volumen.getText().toString(),
                    edt_codigo.getText().toString(),edt_cliente.getText().toString(),
                    txt_Peso_Acumulado.getText().toString(),edt_tara.getText().toString(),String.valueOf(netoDescargado))));

            DBpesadas dBpesadas;
            dBpesadas = arrayPesadas.get(0);
            db.InsertarPesadas(dBpesadas);
            if (arrayPesadas.isEmpty()){
                Toast.makeText(getBaseContext(),"No se pudo guardar la pesada",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getBaseContext(),"La pesada se guardo correctamente",Toast.LENGTH_LONG).show();
                Layout_Total_Cargado.setVisibility(View.INVISIBLE);
                new impresionAsyncTask().execute();
            }
        }catch (Exception e)
        {
            Toast.makeText(getBaseContext(),"EXCEPCION No se guardo la pesada",Toast.LENGTH_LONG).show();
        }
    }

    private void GuardarUltimosDatos()
    {
        ArrayList<DBdatos> arrayDatos = new ArrayList<DBdatos>(Arrays.asList(new DBdatos(edt_producto.getText().toString(),
                edt_patente.getText().toString(),edt_tara.getText().toString(), edt_volumen.getText().toString(),
                edt_codigo.getText().toString(),edt_cliente.getText().toString())));
        DBdatos datos;
        datos  = arrayDatos.get(0);
        db.actualizarDatos(datos,"1");
    }

    @Override
    public void onFragmentInteraction() {
        Balanza.getInstance().setPesoAcumuladoBancos(0);
    }


    public class impresionAsyncTask extends AsyncTask<Void, Integer, Void>
    {
        int progreso;
        String hora = "", cargio = "",patente ="",codigo="",producto="",cliente="",volumen = "", peso_acumulado="", tara = "";
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            hora = txtHora.getText().toString();
            cargio = txtCargio.getText().toString();
            patente = edt_patente.getText().toString();
            codigo = edt_codigo.getText().toString();
            producto = edt_producto.getText().toString();
            cliente = edt_cliente.getText().toString();
            volumen = edt_volumen.getText().toString();
            peso_acumulado = txt_Peso_Acumulado.getText().toString();
            tara = edt_tara.getText().toString();
            progreso = 0;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for (int i =1; i <= var.getTICKETS(); i++)
            {
                Balanza.getInstance().ImprimirTicket("       BALANZAS HOOK SA");
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                if (!var.getCabecera1().equals(null))
                {
                    if (!var.getCabecera1().equals("")) {
                        Balanza.getInstance().ImprimirTicket("  " + var.getCabecera1());
                        Balanza.getInstance().getOK();
                        progreso++;
                        publishProgress(progreso);
                    }
                }
                if (!var.getCabecera4().equals(null))
                {
                    if (!var.getCabecera2().equals("")){
                        Balanza.getInstance().ImprimirTicket("  "+ var.getCabecera2());
                        Balanza.getInstance().getOK();
                        progreso++;
                        publishProgress(progreso);
                    }
                }

                if (!var.getCabecera4().equals(null))
                {
                    if (!var.getCabecera3().equals(""))
                    {
                        Balanza.getInstance().ImprimirTicket("  "+ var.getCabecera3());
                        Balanza.getInstance().getOK();
                        progreso++;
                        publishProgress(progreso);
                    }
                }

                if (!var.getCabecera4().equals(null))
                {
                    if (!var.getCabecera4().equals(""))
                    {
                        Balanza.getInstance().ImprimirTicket("  "+ var.getCabecera4());
                        Balanza.getInstance().getOK();
                        progreso++;
                        publishProgress(progreso);
                    }
                }
                Balanza.getInstance().ImprimirTicket("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  " + fecha + "      " + hora);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("");
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("");
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Tiempo de Carga : " + cargio);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Patente  : " + patente);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Codigo   : " + codigo);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Producto : " + producto);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Cliente  : " + cliente);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Volumen  : " + volumen);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("");
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Bruto : " + peso_acumulado);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);

                Balanza.getInstance().ImprimirTicket("  Tara  : " + tara);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Neto  : " + String.valueOf(netoDescargado));
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("");
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("");
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("");
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("");

            }
            progreso = 100;
            publishProgress(progreso);

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            if (progreso < 100)
            {
                progressBar.setProgress(values[0]);
            }else {
                progressBar.setVisibility(View.INVISIBLE);
            }


        }
    }



    private int sumarTotal(int peso){
        int suma =0;


        return suma;
    }

    public void comprobarBaseDeDatos()
    {
        /**  Todo: Busca si ya estan guardados los productos */

        ContentValues contentCabecera = new ContentValues();
        Cursor cursorCabecera = db.db.rawQuery("SELECT * FROM tcabecera", null);
        if (cursorCabecera.moveToNext())
        {
            var.setCabecera1(cursorCabecera.getString(1));
            var.setCabecera2(cursorCabecera.getString(2));
            var.setCabecera3(cursorCabecera.getString(3));
            var.setCabecera4(cursorCabecera.getString(4));
        }else
        {
            contentCabecera.put("uno","");
            contentCabecera.put("dos","");
            contentCabecera.put("tres","");
            contentCabecera.put("cuatro","");
            var.setCabecera1("");
            var.setCabecera2("");
            var.setCabecera3("");
            var.setCabecera4("");
            db.db.insert("tcabecera",null,contentCabecera);
        }

        ContentValues contentDatos = new ContentValues();
        Cursor cursorDatos = db.db.rawQuery("SELECT * FROM tdatos", null);
        if (cursorDatos.moveToNext())
        {
            edt_producto.setText(cursorDatos.getString(1));
            edt_patente.setText(cursorDatos.getString(2));
            edt_tara.setText(cursorDatos.getString(3));
            edt_volumen.setText(cursorDatos.getString(4));
            edt_codigo.setText(cursorDatos.getString(5));
            edt_cliente.setText(cursorDatos.getString(6));
            edt_tara.setText(cursorDatos.getString(3));
        }else
        {
            contentDatos.put("producto","");
            contentDatos.put("patente","");
            contentDatos.put("tara","");
            contentDatos.put("volumen","");
            contentDatos.put("codigo","");
            contentDatos.put("cliente","");
            db.db.insert("tdatos",null,contentDatos);
        }
        // Buscar productos //
        ContentValues contentProductos = new ContentValues();
        Cursor cursorProductos = db.db.rawQuery("SELECT nombre FROM tproductos", null);
        if (!cursorProductos.moveToFirst())
        {
            for (int i = 0;i <= 11; i++)
            {
                String NOMBRE = "";
                contentProductos.put("numero",numeros[i]);
                contentProductos.put("nombre",nombres[i]);
                db.db.insert("tproductos", null,contentProductos);
            }
        }
        //Buscar calibracion//

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
            contentCalibracion.put("logica","0");
            contentCalibracion.put("ticket","1");

            var.setCAPACIDAD("10000");
            var.setCELDAS("40000");
            var.setDIVISION("20");
            var.setSENSIBILIDAD("2000");
            var.setVENTANA(Integer.valueOf("3"));
            var.setKGFILTRO(Integer.valueOf("1000"));
            var.setCONVERSIONES("10");
            var.setRECORTES("1");
            var.setLOGICA("0");
            var.setTICKETS(1);
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
            var.setLOGICA(cursorCalibracion.getString(9));
            var.setTICKETS(cursorCalibracion.getInt(10));
        }
        // Buscar Cero //
        ContentValues contentCero = new ContentValues();
        Cursor cursorCero = db.db.rawQuery("SELECT * FROM tcero",null);

        if (cursorCero.moveToFirst())
        {
            int cero =Integer.valueOf(cursorCero.getString(1));
            Balanza.getInstance().setCERO(cero);
        }else
        {
            contentCero.put("cero", "0");
            db.db.insert("tcero",null,contentCero);
        }

        ContentValues contentCorreccion = new ContentValues();
        Cursor cursorCorreccion = db.db.rawQuery("SELECT * FROM tcorreccion",null);
        if (cursorCorreccion.moveToFirst())
        {
            float correccion = Float.valueOf(cursorCorreccion.getString(1));
            Balanza.getInstance().setCORRECCION(correccion);
        }
        else
        {
            contentCorreccion.put("porcentaje", "0");
            db.db.insert("tcorreccion",null,contentCorreccion);
            Balanza.getInstance().setCORRECCION(0);
        }

        ContentValues contentCelda = new ContentValues();
        Cursor cursorCelda = db.db.rawQuery("SELECT * FROM tcelda",null);

        if (cursorCelda.moveToFirst())
        {
            int celda =Integer.valueOf(cursorCelda.getString(1));
            TipoCelda = celda;
        }else
        {
            contentCelda.put("celda", "0");
            db.db.insert("tcelda",null,contentCelda);
        }



    }

    @SuppressLint("ValidFragment")
    public class dialogoCargaDatos
    {
        String productoSeleccionado;


        public dialogoCargaDatos(Context context){
            tipoDeCarga = "";
            equipo ="";

            final Dialog dialog = new Dialog(context,R.style.Material_Base);

            dialog.setCancelable(false);
            dialog.setTitle(R.string.dialogo_titulo);
            dialog.setContentView(R.layout.dialogo_carga_datos);

            List<String> productos = db.getAllProductos();

            final Spinner sp_producto = (Spinner) dialog.findViewById(R.id.sp_producto);
            final Spinner spTipo_carga = (Spinner) dialog.findViewById(R.id.spTipoCarga);
            final Spinner spEquipo = (Spinner) dialog.findViewById(R.id.spEquipo);

            final EditText edt_dialogo_patente = (EditText)dialog.findViewById(R.id.edt_dialogo_patente);
            final EditText edt_dialogo_cliente = (EditText)dialog.findViewById(R.id.edt_dialogo_cliente);
            final EditText edt_dialogo_tara = (EditText)dialog.findViewById(R.id.edt_dialogo_tara);
            final EditText edt_dialogo_codigo = (EditText)dialog.findViewById(R.id.edt_dialogo_codigo);
            final EditText edt_dialogo_volumen = (EditText)dialog.findViewById(R.id.edt_dialogo_volumen);

            Button aceptar = (Button)dialog.findViewById(R.id.btn_acrptar);
            Button cancelar = (Button)dialog.findViewById(R.id.btn_Cancelar);

            edt_dialogo_patente.setText(edt_patente.getText().toString());
            edt_dialogo_cliente.setText(edt_cliente.getText().toString());
            edt_dialogo_tara.setText(edt_tara.getText().toString());
            edt_dialogo_codigo.setText(edt_codigo.getText().toString());
            edt_dialogo_volumen.setText(edt_volumen.getText().toString());

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

            ArrayAdapter<CharSequence> adapterCarga = ArrayAdapter.createFromResource(getBaseContext(),
                    R.array.tipo_carga,android.R.layout.simple_spinner_item);
            spTipo_carga.setAdapter(adapterCarga);

            ArrayAdapter<CharSequence> adapterEquipo = ArrayAdapter.createFromResource(getBaseContext(),
                    R.array.tipo_equipo,android.R.layout.simple_spinner_item);
            spEquipo.setAdapter(adapterEquipo);

            spTipo_carga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    tipoDeCarga = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spEquipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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
                    btnCargar.setEnabled(false);
                    dialog.dismiss();
                    Layout_Total_Cargado.setVisibility(View.VISIBLE);
                    Balanza.getInstance().setCominzoPesaje(true);
                    /**
                     * Pone el acumulador en 0
                     */
                    Balanza.getInstance().setPesoAcumulado(Integer.valueOf(tara));
                    btnGuardar.setEnabled(true);
                    var.setTIEMPOCARGA(true);
                    activarCuentaCarga();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    switch (tipoDeCarga){
                          case "2":
                            fragmentTransaction.replace(R.id.pirulo,fragment_dos_banco);
                            break;
                        case "3":
                            fragmentTransaction.replace(R.id.pirulo,fragment_tres_bancos);
                            break;
                        case "4":
                            fragmentTransaction.replace(R.id.pirulo,fragment_cuatro_bancos);
                            break;
                        case "5":
                            fragmentTransaction.replace(R.id.pirulo,fragment_cinco_bancos);
                            break;
                        case "6":
                            fragmentTransaction.replace(R.id.pirulo,fragment_seis_bancos);
                            break;
                    }

                    fragmentTransaction.commit();

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

    private class seleccionarTipoDeCarga
    {
        String tipoDeCarga = "", equipo = "";

        public seleccionarTipoDeCarga(Context context)
        {
            final Dialog dialog = new Dialog(context,R.style.Material_Base);

            dialog.setCancelable(false);
            dialog.setTitle(R.string.dialogo_titulo);
            dialog.setContentView(R.layout.dialogo_seleccion_carga);

            final Spinner spEquipo = (Spinner) dialog.findViewById(R.id.spTipo_equipo);
            final Spinner spCarga = (Spinner) dialog.findViewById(R.id.spTipo_carga);

            Button aceptar = (Button)dialog.findViewById(R.id.btnTipoCargaAceptar);
            Button cancelar = (Button)dialog.findViewById(R.id.btnTipoCargaCancelar);

            ArrayAdapter<CharSequence> adapterCarga = ArrayAdapter.createFromResource(getBaseContext(),
                                            R.array.tipo_carga,android.R.layout.simple_spinner_item);
            spCarga.setAdapter(adapterCarga);

            ArrayAdapter<CharSequence> adapterEquipo = ArrayAdapter.createFromResource(getBaseContext(),
                    R.array.tipo_equipo,android.R.layout.simple_spinner_item);
            spEquipo.setAdapter(adapterEquipo);

            spCarga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    tipoDeCarga = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spEquipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    equipo = parent.getItemAtPosition(position).toString();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    switch (tipoDeCarga){
                        case "2":
                                fragmentTransaction.replace(R.id.pirulo,fragment_dos_banco);
                            break;
                        case "3":
                                fragmentTransaction.replace(R.id.pirulo,fragment_tres_bancos);
                            break;
                        case "4":
                                fragmentTransaction.replace(R.id.pirulo,fragment_cuatro_bancos);
                            break;
                        case "5":
                                fragmentTransaction.replace(R.id.pirulo,fragment_cinco_bancos);
                            break;
                        case "6":
                                fragmentTransaction.replace(R.id.pirulo,fragment_seis_bancos);
                            break;
                    }

                    fragmentTransaction.commit();
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

    private void activarCuentaCarga()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                segundos = 0;
                minutos = 0;
                while (var.isTIEMPOCARGA())
                {
                    try {
                        contarTiempo();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    int segundos,minutos;
    private void contarTiempo()
    {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                segundos ++;
                if (segundos < 60)
                {
                    if (segundos < 10)
                    {
                        txtCargio.setText(String.valueOf(minutos) + ":" + "0" + String.valueOf(segundos));
                    }else{
                        txtCargio.setText(String.valueOf(minutos) + ":" + String.valueOf(segundos));
                    }
                }else{
                    segundos = 0;
                    minutos ++;
                    txtCargio.setText(String.valueOf(minutos) + ":" + String.valueOf(segundos));
                    if (segundos < 10)
                    {
                        txtCargio.setText(String.valueOf(minutos) + ":" + "0" + String.valueOf(segundos));
                    }else{
                        txtCargio.setText(String.valueOf(minutos) + ":" + String.valueOf(segundos));
                    }
                }
            }
        });

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
                            showInputDialog_Contrasena();

                        break;
                    case 1:
                            showInputDialog_ContrasenaTipoCelda();

                        break;
                    case 2:
                            Intent p = new Intent(getBaseContext(),Productos.class);
                            startActivity(p);
                        break;
                    case 3:
                            showInputDialog_EntreFecha();
                        break;
                    case 4:
                        excel.run();
                        break;
                    case 5:
                            dialogoCabeceraImpresion();
                        break;
                    case 6:
                        dialogoCorreccion();
                        break;
                }
                dialog.cancel();
            }
        });
         builder.create();
         builder.show();
    }

    protected void dialogoCabeceraImpresion()
    {

        final EditText edtCabeceraUno, edtCabeceraDos, edtCabeceraTres, edtCabeceraCuatro;
        LayoutInflater layoutInflater = LayoutInflater.from(Principal.this);
        View promptView = layoutInflater.inflate(R.layout.dialogo_cabecera_impresion, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.TemaGeneral));
        alertDialogBuilder.setView(promptView);

        Button btnCabeceraAceptar = (Button)promptView.findViewById(R.id.btnCabeceraAceptar);
        edtCabeceraUno = (EditText)promptView.findViewById(R.id.edtCabeceraUno);
        edtCabeceraDos = (EditText)promptView.findViewById(R.id.edtCabeceraDos);
        edtCabeceraTres = (EditText)promptView.findViewById(R.id.edtCabeceraTres);
        edtCabeceraCuatro = (EditText)promptView.findViewById(R.id.edtCabeceraCuatro);

        edtCabeceraUno.setText(var.getCabecera1());
        edtCabeceraDos.setText(var.getCabecera2());
        edtCabeceraTres.setText(var.getCabecera3());
        edtCabeceraCuatro.setText(var.getCabecera4());

        final AlertDialog alert = alertDialogBuilder.create();

        btnCabeceraAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<DBcabecera> arrayCabecera = new ArrayList<>(Arrays.asList(new DBcabecera(edtCabeceraUno.getText().toString(),
                        edtCabeceraDos.getText().toString(),edtCabeceraTres.getText().toString(),edtCabeceraCuatro.getText().toString())));
                DBcabecera dBcabecera = arrayCabecera.get(0);
                db.actualizarCabecera(dBcabecera,"1");

                var.setCabecera1(edtCabeceraUno.getText().toString());
                var.setCabecera2(edtCabeceraDos.getText().toString());
                var.setCabecera3(edtCabeceraTres.getText().toString());
                var.setCabecera4(edtCabeceraCuatro.getText().toString());
                Toast.makeText(getBaseContext(), "La cabecera se guardo correctamente",Toast.LENGTH_LONG).show();
                alert.dismiss();
            }
        });

        alert.show();
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
                .setPositiveButton(R.string.dialgo_aceptar, new DialogInterface.OnClickListener() {
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
                .setNegativeButton(R.string.dialgo_cancelar,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void dialogoCorreccion()
    {
        final EditText edtCorreccion;
        final AlertDialog dialog;
        final Button btnCorreccionAceptar;
        final Button btnCorreccionCancelar;

        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.Material_Base));
        LayoutInflater inflater = LayoutInflater.from(Principal.this);
        View view = inflater.inflate(R.layout.dialogo_correccion, null);
        btnCorreccionAceptar = (Button)view.findViewById(R.id.btnCorreccionAceptar);
        btnCorreccionCancelar = (Button)view.findViewById(R.id.btnCorreccionCancelar);
        edtCorreccion = (EditText)view.findViewById(R.id.edtCorreccion) ;

        ContentValues contentCorreccion = new ContentValues();
        Cursor cursorCorreccion = db.db.rawQuery("SELECT * FROM tcorreccion",null);
        if (cursorCorreccion.moveToFirst())
        {
            float correccion = Float.valueOf(cursorCorreccion.getString(1));
            edtCorreccion.setText(String.valueOf(correccion));
            Balanza.getInstance().setCORRECCION(correccion);
        }

        builder.setView(view);
        dialog =   builder.create();
        btnCorreccionAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtCorreccion.getText().toString().equals(""))
                {
                    edtCorreccion.setText("0");
                }

                ArrayList<DBcorreccion> arrayCorreccion = new ArrayList<DBcorreccion>(Arrays.asList(new DBcorreccion(edtCorreccion.getText().toString())));
                DBcorreccion dBcorreccion = arrayCorreccion.get(0);
                db.actualizarCorreccion(dBcorreccion,"1");

                Balanza.getInstance().setCORRECCION(Float.valueOf(edtCorreccion.getText().toString()));
                Toast.makeText(getBaseContext(), "La corrección se guardo correctamente",Toast.LENGTH_LONG).show();
                dialog.dismiss();

            }
        });

       btnCorreccionCancelar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dialog.dismiss();
           }
       });
        dialog.show();

    }

    protected void showInputDialog_ContrasenaTipoCelda()
    {
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);

        LayoutInflater layoutInflater = LayoutInflater.from(Principal.this);
        View  promptView = layoutInflater.inflate(R.layout.dialogo_contrasena, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom));
        alertDialogBuilder.setView(promptView);
        final EditText txtContrasena = (EditText) promptView.findViewById(R.id.txtContrasena);

        alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!txtContrasena.getText().toString().equals("")){
                            if (txtContrasena.getText().toString().equals("1910"))
                            {
                                Balanza.getInstance().paraPedido();
                                dialogoTipoDeCelda();
                            }else{
                                Toast.makeText(getBaseContext(),"La Contraseña es incorrecta",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(getBaseContext(),"La Contraseña es incorrecta",Toast.LENGTH_LONG).show();
                        }
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    protected void showInputDialog_Contrasena()
    {
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);

        LayoutInflater layoutInflater = LayoutInflater.from(Principal.this);
        View  promptView = layoutInflater.inflate(R.layout.dialogo_contrasena, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom));

        alertDialogBuilder.setView(promptView);
        final EditText txtContrasena = (EditText) promptView.findViewById(R.id.txtContrasena);

        alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!txtContrasena.getText().toString().equals("")){
                            if (txtContrasena.getText().toString().equals("1910"))
                            {
                                Balanza.getInstance().paraPedido();
                                Intent intent = new Intent(getBaseContext(),Calibracion.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getBaseContext(),"La Contraseña es incorrecta",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(getBaseContext(),"La Contraseña es incorrecta",Toast.LENGTH_LONG).show();
                        }
                        dialogInterface.cancel();
                    }
                })
                .setCancelable(false);
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void dialogoTipoDeCelda()
    {
        final TextView txt1, txt2, txt3;
        final EditText edt1, edt2, edt3;
        Button btnAceptar, btnCancelar;
        final AlertDialog dialog;

        Spinner spTipo;
        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.Material_Base));
        LayoutInflater inflater = LayoutInflater.from(Principal.this);
        View view = inflater.inflate(R.layout.dialogo_tipo_de_celda, null);

        btnAceptar = (Button)view.findViewById(R.id.btnTipoCeldaAceptar);
        btnCancelar = (Button)view.findViewById(R.id.btnTipoCeldaCancelar);
        txt1 = (TextView)view.findViewById(R.id.txt1);
        txt2 =(TextView)view.findViewById(R.id.txt2);
        txt3 =(TextView)view.findViewById(R.id.txt3);
        edt1 = (EditText)view.findViewById(R.id.edt1);
        edt2 = (EditText)view.findViewById(R.id.edt2);
        edt3 = (EditText)view.findViewById(R.id.edt3);
        txt1.setVisibility(View.INVISIBLE);
        txt2.setVisibility(View.INVISIBLE);
        txt3.setVisibility(View.INVISIBLE);
        edt1.setVisibility(View.INVISIBLE);
        edt2.setVisibility(View.INVISIBLE);
        edt3.setVisibility(View.INVISIBLE);

        spTipo = (Spinner)view.findViewById(R.id.spTipo);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_celda, R.layout.spinner_item);
        spTipo.setAdapter(adapter);
        builder.setCancelable(false);
        builder.setView(view);
        dialog =   builder.create();
        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {

                    case 0:
                        tipoCeldaAEnviar = 0;
                        txt1.setVisibility(View.INVISIBLE);
                        txt2.setVisibility(View.INVISIBLE);
                        txt3.setVisibility(View.INVISIBLE);
                        edt1.setVisibility(View.INVISIBLE);
                        edt2.setVisibility(View.INVISIBLE);
                        edt3.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        tipoCeldaAEnviar = 1;
                        txt1.setText(R.string.tipo_celda_numero_celda);
                        txt1.setVisibility(View.VISIBLE);
                        txt2.setVisibility(View.INVISIBLE);
                        txt3.setVisibility(View.INVISIBLE);
                        edt1.setVisibility(View.VISIBLE);
                        edt2.setVisibility(View.INVISIBLE);
                        edt3.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        tipoCeldaAEnviar = 2;
                        txt1.setText(R.string.tipo_celda_ssd);
                        txt2.setText(R.string.tipo_celda_pass);
                        txt3.setText(R.string.tipo_celda_puerto);
                        txt1.setVisibility(View.VISIBLE);
                        txt2.setVisibility(View.VISIBLE);
                        txt3.setVisibility(View.VISIBLE);
                        edt1.setVisibility(View.VISIBLE);
                        edt2.setVisibility(View.VISIBLE);
                        edt3.setVisibility(View.VISIBLE);
                        break;
                }

             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envio = String.valueOf(tipoCeldaAEnviar);
                switch (tipoCeldaAEnviar)
                {
                    case 0:
                        try {
                            envio = "AT+CELDA=" + envio + '\r'+'\n';
                            Balanza.getInstance().getOK();
                            Balanza.getInstance().EnviarTipoCelda(envio);
                            TipoCelda = tipoCeldaAEnviar;
                            if (Balanza.getInstance().getOK() == 1){
                                Toast.makeText(getBaseContext(),R.string.mensaje_tipo_celda,Toast.LENGTH_LONG).show();
                                Balanza.getInstance().pedirCuentas();
                                dialog.dismiss();

                            }else if (Balanza.getInstance().getOK() == 0){
                                Toast.makeText(getBaseContext(),R.string.mensaje__tipo_celda_fail,Toast.LENGTH_LONG).show();
                            }else if (Balanza.getInstance().getOK() == -1){
                                Toast.makeText(getBaseContext(),R.string.mensaje__tipo_celda_error,Toast.LENGTH_LONG).show();
                            }
                        }catch (Exception e){

                        }
                        break;
                    case 1:
                        try {
                            numero = edt1.getText().toString();
                            if (!numero.equals(""))
                            {
                                envio = "AT+CELDA=" + envio + "," + numero+ '\r'+'\n';

                                Balanza.getInstance().EnviarTipoCelda(envio);
                                Balanza.getInstance().getOK();
                                TipoCelda = tipoCeldaAEnviar;
                            }else {
                                Toast.makeText(getBaseContext(),R.string.tipo_celda_mensaje,Toast.LENGTH_LONG).show();
                            }
                            if (Balanza.getInstance().getOK() == 1){
                                Toast.makeText(getBaseContext(),R.string.mensaje_tipo_celda,Toast.LENGTH_LONG).show();
                                Balanza.getInstance().pedirCuentas();
                                dialog.dismiss();

                            }else if (Balanza.getInstance().getOK() == 0){
                                Toast.makeText(getBaseContext(),R.string.mensaje__tipo_celda_fail,Toast.LENGTH_LONG).show();
                            }else if (Balanza.getInstance().getOK() == -1){
                                Toast.makeText(getBaseContext(),R.string.mensaje__tipo_celda_error,Toast.LENGTH_LONG).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(getBaseContext(),R.string.mensaje__tipo_celda_error,Toast.LENGTH_LONG).show();
                        }
                        break;
                    case  2:
                        try {
                            ssd = edt1.getText().toString();
                            pass = edt2.getText().toString();
                            puerto = edt3.getText().toString();
                            if (ssd.equals("") || pass.equals("") || puerto.equals("")){
                                envio = "AT+CELDA=" + envio + "," + ssd + "," + pass + "," + puerto+ '\r'+'\n';
                                Balanza.getInstance().EnviarTipoCelda(envio);
                                TipoCelda = tipoCeldaAEnviar;
                            }else {
                                Toast.makeText(getBaseContext(),R.string.tipo_celda_mensaje,Toast.LENGTH_LONG).show();
                            }
                        }catch (Exception e){

                        }
                        break;
                }

                TipoCelda = tipoCeldaAEnviar;
                try {
                    ArrayList<DBcelda> arrayCelda = new ArrayList<DBcelda>(Arrays.asList(new DBcelda(String.valueOf(TipoCelda))));
                    DBcelda dBcelda = arrayCelda.get(0);
                    db.actualizarCelda(dBcelda,"1");
                }catch (Exception e){

                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Balanza.getInstance().pedirCuentas();
                dialog.dismiss();
            }
        });
        dialog.show();
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
                                if (Balanza.getInstance().isConexionSerie()){
                                    imgConexionSerie.setBackgroundResource(R.drawable.serie_conectado);
                                }else{
                                    imgConexionSerie.setBackgroundResource(R.drawable.serie_desconectado);
                                }
                                if (Integer.valueOf(var.getCAPACIDAD()) > Balanza.getInstance().getPesoFisico()){
                                    txtPesoGarra.setText(String.format ("% .0f", Balanza.getInstance().getPesoFisico()));
                                }else{
                                    txtPesoGarra.setText("MAX");
                                }

                                if (Balanza.getInstance().isEstable()){
                                    imgEstable.setBackgroundResource(R.drawable.circulo_estable);
                                }else{
                                    imgEstable.setBackgroundResource(R.drawable.circulo_inestable);
                                }
                                if (Balanza.getInstance().isGuardado())
                                {
                                    imgDescargando.setVisibility(View.VISIBLE);
                                    if (!cuentaLeedEmpezada)
                                    {
                                        cuentaLeedAzul();
                                        cuentaLeedEmpezada = true;
                                    }
                                }else {
                                    imgDescargando.setVisibility(View.INVISIBLE);
                                }
                                if (var.isRESTAR())
                                {
                                    btnRestar.setBackgroundResource(R.drawable.boton_restar_azul_pres);
                                }
                                else{
                                    btnRestar.setBackgroundResource(R.drawable.boton_restar_azul);
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

    @Override
    public void onBackPressed() {

    }

    private void cuentaLeedAzul()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int cuenta = 0;
                        while (cuenta > 100){
                            try {
                            imgDescargando.setVisibility(View.VISIBLE);
                            cuenta++;

                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        cuentaLeedEmpezada = false;
                        Balanza.getInstance().setGuardado(false);
                    }
                });

            }
        }).start();
    }

    class CountDownRunner implements Runnable
    {
        @Override
        public void run()
        {
            while (!Thread.currentThread().isInterrupted())
            {
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

    class MostrarEstaadoBateria implements Runnable
    {
        int contador = 0;
        @Override
        public void run()
        {
            while (!Thread.currentThread().isInterrupted()) {
                if (!var.isBATERIA())
                {
                    var.setBATERIA(true);
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
        public void doWork()
        {
            runOnUiThread(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        if (Balanza.getInstance().isConexionSerie())
                        {
                            if (TipoCelda == 0)
                            {
                                imgBateria.setBackgroundResource(R.drawable.cableada);

                                txtBateria.setText("");
                            }
                            else
                            {
                                if (Balanza.getInstance().getBateria() == 0)
                                {
                                    imgBateria.setBackgroundResource(R.drawable.celda_desconectada);
                                    txtBateria.setText("0%");
                                }
                                else
                                {
                                    contador = Balanza.getInstance().getBateria();
                                    if (contador>100){
                                        contador = 100;
                                    }
                                    txtBateria.setText(String.valueOf(Balanza.getInstance().getBateria())+"%");
                                    if (contador <= 25){
                                        imgBateria.setBackgroundResource(R.drawable.celda_25);
                                    }
                                    else if (contador > 25 && contador <= 50){
                                        imgBateria.setBackgroundResource(R.drawable.celda_50);
                                    }
                                    else if (contador > 50 && contador <= 75)
                                    {
                                        imgBateria.setBackgroundResource(R.drawable.celda_75);
                                    }
                                    else if (contador > 75 )
                                    {
                                        imgBateria.setBackgroundResource(R.drawable.celda_100);
                                    }
                                }
                            }

                        }
                        else
                        {
                            imgConexionSerie.setBackgroundResource(R.drawable.serie_desconectado);
                            //txtBateria.setText("0%");
                        }
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

    public class Exportar_Excel extends Thread
    {
        @Override
        public void run()
        {

            final String ExpandedRowCountCarga = "";
            final String ExpandedRowCountDescarga = "";

            final String Crear_Libro = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                    "<?mso-application progid=\"Excel.Sheet\"?>\n" +
                    "<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"\n" +
                    "xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
                    "xmlns:x=\"urn:schemas-microsoft-com:office:excel\"\n" +
                    "xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\">\n" +
                    "xmlns:html=\"http://www.w3.org/TR/REC-html40\">\n" +
                    " <DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\">\n" +
                    "  <Version>12.00</Version>\n" +
                    " </DocumentProperties>\n" +
                    " <ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">\n" +
                    "  <WindowHeight>10005</WindowHeight>\n" +
                    "  <WindowWidth>10005</WindowWidth>\n" +
                    "  <WindowTopX>120</WindowTopX>\n" +
                    "  <WindowTopY>135</WindowTopY>\n" +
                    "  <ActiveSheet>4</ActiveSheet>\n" +
                    "  <ProtectStructure>False</ProtectStructure>\n" +
                    "  <ProtectWindows>False</ProtectWindows>\n" +
                    " </ExcelWorkbook>\n" +
                    "<Styles>\n" +
                    "  <Style ss:ID=\"s62\">\n" +
                    "   <Interior ss:Color=\"#FB4A4A\" ss:Pattern=\"Solid\"/>\n" +
                    "  </Style>\n" +
                    "  <Style ss:ID=\"s63\">\n" +
                    "   <Interior ss:Color=\"#FFFF00\" ss:Pattern=\"Solid\"/>\n" +
                    "  </Style>\n" +
                    "  <Style ss:ID=\"s66\">\n" +
                    "   <Borders>\n" +
                    "    <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n" +
                    "    <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n" +
                    "    <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n" +
                    "    <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n" +
                    "   </Borders>\n" +
                    "  </Style>\n" +
                    "  <Style ss:ID=\"s67\">\n" +
                    "   <Borders>\n" +
                    "    <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n" +
                    "    <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n" +
                    "    <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n" +
                    "    <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n" +
                    "   </Borders>\n" +
                    "   <Interior ss:Color=\"#FFFF00\" ss:Pattern=\"Solid\"/>\n" +
                    "  </Style>\n" +
                    "  <Style ss:ID=\"s73\">\n" +
                    "   <Alignment ss:Vertical=\"Bottom\"/>\n" +
                    "   <Borders/>\n" +
                    "   <Font ss:FontName=\"Calibri\" x:Family=\"Swiss\" ss:Size=\"11\" ss:Color=\"#FFFFFF\"\n" +
                    "    ss:Bold=\"1\"/>\n" +
                    "   <Interior ss:Color=\"#002060\" ss:Pattern=\"Solid\"/>\n" +
                    "   <NumberFormat/>\n" +
                    "   <Protection/>\n" +
                    "  </Style>\n" +
                    "  <Style ss:ID=\"s100\">\n" +
                    "   <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\"/>\n" +
                    "   <Font ss:FontName=\"Cambria\" x:Family=\"Roman\" ss:Size=\"48\" ss:Color=\"#1F497D\"\n" +
                    "    ss:Bold=\"1\"/>\n" +
                    "   <Interior/>\n" +
                    "  </Style>\n" +
                    "<Style ss:ID=\"s92\">\n" +
                    "   <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\"/>\n" +
                    "   <Font ss:FontName=\"Cambria\" x:Family=\"Roman\" ss:Size=\"48\" ss:Color=\"#1F497D\"\n" +
                    "    ss:Bold=\"1\"/>\n" +
                    "  </Style>" +
                    " </Styles>\n";
            //.............. Hojas de calculo ........................
            final String Hoja_Cargas = "<Worksheet ss:Name=\"DESCARGAS\">\n" +
                    "<Names><NamedRange ss:Name=\"_FilterDatabase\" ss:RefersTo=\"=DESCARGAS!R5C1:R5C7\" ss:Hidden=\"1\"/></Names>\n";


            final String Encabezado_Carga = "<Row ss:AutoFitHeight=\"0\">\n" +
                    "    <Cell ss:MergeAcross=\"11\" ss:MergeDown=\"3\" ss:StyleID=\"s100\"><Data\n" +
                    "      ss:Type=\"String\">Balanzas Hook ST-455</Data></Cell>\n" +
                    "   </Row>\n" +
                    "   <Row ss:AutoFitHeight=\"0\" ss:Span=\"2\"/>\n" +
                    "<Row ss:Index=\"5\">\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">DESC. ID</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">FECHA</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">HORA</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">PRODUCTO</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">CARGIO</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">PATENTE</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">VOLUMEN</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">CODIGO</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">CLIENTE</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">BRUTO</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">TARA</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">NETO</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +

                    "</Row>\n";

            final String Cerrar_Fila = "</Row>\n";
            final String Cerrar_Tabla = "</Table>\n";
            // ............... Cerrar Hojas .....................................


            final String Cerrar_Libro = "</Workbook>\n";
            //Escribir en el USB con BufferWriter
            File sdcard = Environment.getExternalStorageDirectory();
            //String sdcard = "storage/usbotg/usbotg-sda1";
            File file = new File(sdcard.getAbsolutePath(), fecha+"-ST455.xml");
            BufferedWriter br = null;
           /* String sdcard = "storage/usbotg/usbotg-sda1";
            File file = new File(sdcard, "Pesadas.csv");
            BufferedWriter br = null;*/

            try
            {
                br = new BufferedWriter(new FileWriter(file));

                br.append(Crear_Libro);
                //Hoja Cargas
                br.append(Hoja_Cargas);
                int descargas = 0;
                // br.append(Abrir_Fila);
                Cursor h = db.db.rawQuery("SELECT COUNT(tpesadas._id) AS 'Cantidad' FROM tpesadas ", null);
                if (h.moveToFirst()) {
                    descargas = h.getInt(0);
                }
                final String Abrir_Tabla_Cargas = "<Table ss:ExpandedColumnCount=\"12\" ss:ExpandedRowCount=\"" + (descargas + 7) + "\" x:FullColumns=\"1\"\n" +
                        "   x:FullRows=\"1\" ss:DefaultColumnWidth=\"81.75\" ss:DefaultRowHeight=\"15\">\n";

                br.append(Abrir_Tabla_Cargas);
                br.append(Encabezado_Carga);
                // br.append(Cerrar_Fila);

                Cursor c = db.db.rawQuery("SELECT * FROM tpesadas ", null);
                datos = new ArrayList<String>();
                int celdas = 0;
                String fechaCarga = "";
                String ids = "", fecha = "", hora = "", prod = "", cargio = "", patente = "",  volumen = "";
                String codigo = "", cliente = "", bruto = "", tara = "", neto = "";
                if (c.moveToFirst()) {
                    for (int i = 0; i <= c.getCount() - 1; i++) {
                        ids = c.getString(0);
                        // emp = c.getString(1);
                        //u = c.getString(2);
                        // pass = c.getString(3);
                        fecha = c.getString(1);
                        calendar.setTimeInMillis(Long.valueOf(fecha));
                        fechaCarga = dateFormat.format(calendar.getTime());
                        hora = c.getString(2);
                        prod = c.getString(3);
                        cargio = c.getString(4);
                        patente = c.getString(5);
                        volumen = c.getString(6);
                        codigo = c.getString(7);
                        cliente = c.getString(8);
                        bruto = c.getString(9);
                        tara = c.getString(10);
                        neto = c.getString(11);

                        try {
                            br.append("<Row>\n");
                            br.append("<Cell><Data ss:Type=\"Number\">" + ids + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + fechaCarga + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + hora + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + prod + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + cargio + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + patente + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + volumen + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + codigo + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + cliente + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + bruto + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + tara + "</Data></Cell>\n");
                            br.append("<Cell ss:StyleID=\"s63\"><Data ss:Type=\"Number\">" + neto + "</Data></Cell>\n");

                            br.append("</Row>\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        c.moveToNext();
                        celdas = i;
                    }
                }

                final String Calculo_Carga = "<Row>\n" +
                        "</Row>\n" +
                        "<Row>\n" +
                        "<Cell><Data ss:Type=\"String\"></Data></Cell>\n" +
                        "<Cell><Data ss:Type=\"String\"></Data></Cell>\n" +
                        "<Cell><Data ss:Type=\"String\"></Data></Cell>\n" +
                        "<Cell><Data ss:Type=\"String\"></Data></Cell>\n" +
                        "<Cell><Data ss:Type=\"String\"></Data></Cell>\n" +
                        "<Cell><Data ss:Type=\"String\"></Data></Cell>\n" +
                        "<Cell><Data ss:Type=\"String\"></Data></Cell>\n" +
                        "<Cell><Data ss:Type=\"String\"></Data></Cell>\n" +
                        "<Cell><Data ss:Type=\"String\"></Data></Cell>\n" +
                        "<Cell><Data ss:Type=\"String\"></Data></Cell>\n" +
                        "<Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">TOTAL</Data></Cell>\n" +
                        "<Cell ss:StyleID=\"s67\" ss:Formula=\"=SUBTOTAL(9,R[-" + (celdas + 2) + "]C:R[-1]C)\"></Cell>\n" +
                        "</Row>\n";
                br.append(Calculo_Carga);
                br.append(Cerrar_Tabla);
                final String Cerrar_Hoja_Cargas = " <WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">\n" +
                        "   <Unsynced/>\n" +
                        "   <Panes>\n" +
                        "    <Pane>\n" +
                        "     <Number>3</Number>\n" +
                        "     <ActiveRow>15</ActiveRow>\n" +
                        "     <ActiveCol>5</ActiveCol>\n" +
                        "    </Pane>\n" +
                        "   </Panes>\n" +
                        "   <ProtectObjects>False</ProtectObjects>\n" +
                        "   <ProtectScenarios>False</ProtectScenarios>\n" +
                        "  </WorksheetOptions>\n" +
                        "  <AutoFilter x:Range=\"R5C1:R5C10\"\n" +
                        "   xmlns=\"urn:schemas-microsoft-com:office:excel\">\n" +
                        "  </AutoFilter>\n" +
                        "  <ss:WorksheetOptions/>\n" +
                        "  <ss:WorksheetOptions/>\n" +
                        "   </Worksheet>\n";
                br.append(Cerrar_Hoja_Cargas);
                //Cerrar Libro
                br.append(Cerrar_Libro);



                br.close();
                Toast.makeText(getBaseContext(),"El archivo se exportó correctamente", Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                Toast.makeText(getBaseContext(),"No se pudo exportar el archivo", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }


    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
     //public native String stringFromJNI();
}
