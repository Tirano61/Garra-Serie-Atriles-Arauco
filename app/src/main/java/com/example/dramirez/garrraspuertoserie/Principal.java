package com.example.dramirez.garrraspuertoserie;



import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.ListView;
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
import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBoperadores;
import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBpesadas;
import com.example.dramirez.garrraspuertoserie.FragmentInterfaces.EnvioDatos;


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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Principal extends AppCompatActivity implements  EnvioDatos {

    PendingIntent mPermissionIntent;
    Button btnCheck;
    TextView textInfo;
    UsbDevice device;
    UsbManager manager;
    private static final String ACTION_USB_PERMISSION = "com.mobilemerit.usbhost.USB_PERMISSION";



    managerPort Port = null;
    ProgressBar progressBar;

    TextView tv, txtCargio;
    BaseDeDatos db;
    String[] numeros = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    String[] nombres = {"Pino", "Eucaliptus","Producto 1","Producto 2","Producto 3","Producto 4","Producto 5",
            "Producto 6","Producto 7","Producto 8","Producto 9","Producto 10"};

    int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;

    TextView txtPesoGarra,txt_Peso_Acumulado, txtFecha, txtHora, txtBateria,txtGarradas,txtVersion;
    Variables var = new Variables();
    EditText edt_vehiculo, edt_codigo, edt_producto, edt_operador, edt_tara, edt_grua;

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
    Fragment_siete_bancos fragment_siete_bancos;
    Fragment_ocho_bancos fragment_ocho_bancos;
    Fragment_nueve_bancos fragment_nueve_bancos;
    FragmentInicio fragmentInicio;

    String bancos,banco1,banco2,banco3,banco4,banco5,banco6,banco7,banco8,banco9;

    String tipoDeCarga = "";
    int  lugar = 0;

    int chasis= 0, acoplado = 0, acoplado2 = 0;

    Calculadora calculadora;
    boolean comprobarSpiner = false;
    boolean comprobarSpinerProducto = false;
    MediaPlayer mediaPlayer;
    ListView lvOperadores;
    String IdOperadores;
    String TotalACargar;

    ArrayList<ListaEntradaOperadores> datosOperadores;
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

        mediaPlayer =  MediaPlayer.create(this,R.raw.dos_beep);
        InicializarComponentesGraficos();

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
        Port = new managerPort("/dev/user_external_tty",115200);

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
                //LO SAQUE AFUERA PARA QUE PUSIERA SIEMPRE EN CERO
                if (!Balanza.getInstance().isCominzoPesaje()){

                }
                Balanza.getInstance().setearCero();
                ActualizarCero();

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
        fragment_siete_bancos = new Fragment_siete_bancos();
        fragment_ocho_bancos = new Fragment_ocho_bancos();
        fragment_nueve_bancos = new Fragment_nueve_bancos();
        fragmentInicio = new FragmentInicio();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.pirulo,fragmentInicio);
        fragmentTransaction.commit();
        txtVersion.setText("ver " + getVersionName());


        checkInfo();



    }
    public void InicializarComponentesGraficos()
    {

        txtVersion = (TextView)findViewById(R.id.txtVersion);
        txtPesoGarra = (TextView)findViewById(R.id.txtPesoGarra);
        btnCero = (ImageButton) findViewById(R.id.btnCero);
        txtFecha =(TextView)findViewById(R.id.txtFecha);
        txtHora =(TextView)findViewById(R.id.txtHora);
        txtCargio = (TextView)findViewById(R.id.txtCargio);
        txt_Peso_Acumulado = (TextView)findViewById(R.id.txt_Peso_Acumulado);
        txtBateria = (TextView)findViewById(R.id.txtBateria);
        txtGarradas = (TextView)findViewById(R.id.txtGarradas);

        edt_vehiculo = (EditText)findViewById(R.id.edt_vehiculo);
        edt_codigo = (EditText)findViewById(R.id.edt_codigo);
        edt_producto = ( EditText)findViewById(R.id.edt_producto);
        edt_operador = (EditText) findViewById(R.id.edt_operador);
        edt_tara = (EditText) findViewById(R.id.edt_tara);
        edt_grua = (EditText)findViewById(R.id.edt_grua);

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

    }

    public void Actualizar2()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "https://www.balanzashook.com.ar/app/download/st455garra_atriles.apk";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }catch (Exception e)
                {

                }
            }
        }).start();
    }

    public String getVersionName()
    {
        String version = "";
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
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
                    case "7":
                        fragment_siete_bancos.recibirPeso(String.valueOf(Balanza.getInstance().getPesoAcumuladoBancos()));
                        break;
                    case "8":
                        fragment_ocho_bancos.recibirPeso(String.valueOf(Balanza.getInstance().getPesoAcumuladoBancos()));
                        break;
                    case "9":
                        fragment_nueve_bancos.recibirPeso(String.valueOf(Balanza.getInstance().getPesoAcumuladoBancos()));
                        break;
                }

                txt_Peso_Acumulado.setText(String.valueOf(Balanza.getInstance().getPesoAcumulado()));
                txtGarradas.setText(String.valueOf(Balanza.getInstance().getCantidadGarradas()));
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

                        switch (tipoDeCarga){
                            case "2":
                                TextView txtChasis2_1 = (TextView)findViewById(R.id.txtChasis2_1);
                                TextView txtChasis2_2 = (TextView)findViewById(R.id.txtChasis2_2);
                                bancos = "2";
                                banco1 = txtChasis2_1.getText().toString();
                                banco2 = txtChasis2_2.getText().toString();
                                banco3 = "";
                                banco4 = "";
                                banco5 = "";
                                banco6 = "";
                                banco7 = "";
                                banco8 = "";
                                banco9 = "";
                                break;
                            case "3":
                                TextView txtChasis3_1 = (TextView)findViewById(R.id.txtChasis3_1);
                                TextView txtChasis3_2 = (TextView)findViewById(R.id.txtChasis3_2);
                                TextView txtChasis3_3 = (TextView)findViewById(R.id.txtChasis3_3);
                                bancos = "3";
                                banco1 = txtChasis3_1.getText().toString();
                                banco2 = txtChasis3_2.getText().toString();
                                banco3 = txtChasis3_3.getText().toString();
                                banco4 = "";
                                banco5 = "";
                                banco6 = "";
                                banco7 = "";
                                banco8 = "";
                                banco9 = "";
                                break;
                            case "4":
                                TextView txtChasis4_1 = (TextView)findViewById(R.id.txtChasis4_1);
                                TextView txtChasis4_2 = (TextView)findViewById(R.id.txtChasis4_2);
                                TextView txtChasis4_3 = (TextView)findViewById(R.id.txtChasis4_3);
                                TextView txtChasis4_4 = (TextView)findViewById(R.id.txtChasis4_4);
                                bancos = "4";
                                banco1 = txtChasis4_1.getText().toString();
                                banco2 = txtChasis4_2.getText().toString();
                                banco3 = txtChasis4_3.getText().toString();
                                banco4 = txtChasis4_4.getText().toString();
                                banco5 = "";
                                banco6 = "";
                                banco7 = "";
                                banco8 = "";
                                banco9 = "";
                                break;
                            case "5":
                                TextView txtChasis5_1 = (TextView)findViewById(R.id.txtChasis5_1);
                                TextView txtChasis5_2 = (TextView)findViewById(R.id.txtChasis5_2);
                                TextView txtChasis5_3 = (TextView)findViewById(R.id.txtChasis5_3);
                                TextView txtChasis5_4 = (TextView)findViewById(R.id.txtChasis5_4);
                                TextView txtChasis5_5 = (TextView)findViewById(R.id.txtChasis5_5);
                                bancos = "5";
                                banco1 = txtChasis5_1.getText().toString();
                                banco2 = txtChasis5_2.getText().toString();
                                banco3 = txtChasis5_3.getText().toString();
                                banco4 = txtChasis5_4.getText().toString();
                                banco5 = txtChasis5_5.getText().toString();
                                banco6 = "";
                                banco7 = "";
                                banco8 = "";
                                banco9 = "";
                                break;
                            case "6":
                                TextView txtChasis6_1 = (TextView)findViewById(R.id.txtChasis6_1);
                                TextView txtChasis6_2 = (TextView)findViewById(R.id.txtChasis6_2);
                                TextView txtChasis6_3 = (TextView)findViewById(R.id.txtChasis6_3);
                                TextView txtChasis6_4 = (TextView)findViewById(R.id.txtChasis6_4);
                                TextView txtChasis6_5 = (TextView)findViewById(R.id.txtChasis6_5);
                                TextView txtChasis6_6 = (TextView)findViewById(R.id.txtChasis6_6);
                                bancos = "6";
                                banco1 = txtChasis6_1.getText().toString();
                                banco2 = txtChasis6_2.getText().toString();
                                banco3 = txtChasis6_3.getText().toString();
                                banco4 = txtChasis6_4.getText().toString();
                                banco5 = txtChasis6_5.getText().toString();
                                banco6 = txtChasis6_6.getText().toString();
                                banco7 = "";
                                banco8 = "";
                                banco9 = "";
                                break;
                            case "7":
                                TextView txtChasis7_1 = (TextView)findViewById(R.id.txtChasis7_1);
                                TextView txtChasis7_2 = (TextView)findViewById(R.id.txtChasis7_2);
                                TextView txtChasis7_3 = (TextView)findViewById(R.id.txtChasis7_3);
                                TextView txtChasis7_4 = (TextView)findViewById(R.id.txtChasis7_4);
                                TextView txtChasis7_5 = (TextView)findViewById(R.id.txtChasis7_5);
                                TextView txtChasis7_6 = (TextView)findViewById(R.id.txtChasis7_6);
                                TextView txtChasis7_7 = (TextView)findViewById(R.id.txtChasis7_7);
                                bancos = "7";
                                banco1 = txtChasis7_1.getText().toString();
                                banco2 = txtChasis7_2.getText().toString();
                                banco3 = txtChasis7_3.getText().toString();
                                banco4 = txtChasis7_4.getText().toString();
                                banco5 = txtChasis7_5.getText().toString();
                                banco6 = txtChasis7_6.getText().toString();
                                banco7 = txtChasis7_7.getText().toString();
                                banco8 = "";
                                banco9 = "";
                                break;
                            case "8":
                                TextView txtChasis8_1 = (TextView)findViewById(R.id.txtChasis8_1);
                                TextView txtChasis8_2 = (TextView)findViewById(R.id.txtChasis8_2);
                                TextView txtChasis8_3 = (TextView)findViewById(R.id.txtChasis8_3);
                                TextView txtChasis8_4 = (TextView)findViewById(R.id.txtChasis8_4);
                                TextView txtChasis8_5 = (TextView)findViewById(R.id.txtChasis8_5);
                                TextView txtChasis8_6 = (TextView)findViewById(R.id.txtChasis8_6);
                                TextView txtChasis8_7 = (TextView)findViewById(R.id.txtChasis8_7);
                                TextView txtChasis8_8 = (TextView)findViewById(R.id.txtChasis8_8);
                                bancos = "8";
                                banco1 = txtChasis8_1.getText().toString();
                                banco2 = txtChasis8_2.getText().toString();
                                banco3 = txtChasis8_3.getText().toString();
                                banco4 = txtChasis8_4.getText().toString();
                                banco5 = txtChasis8_5.getText().toString();
                                banco6 = txtChasis8_6.getText().toString();
                                banco7 = txtChasis8_7.getText().toString();
                                banco8 = txtChasis8_8.getText().toString();
                                banco9 = "";
                                break;
                            case "9":
                                TextView txtChasis9_1 = (TextView)findViewById(R.id.txtChasis9_1);
                                TextView txtChasis9_2 = (TextView)findViewById(R.id.txtChasis9_2);
                                TextView txtChasis9_3 = (TextView)findViewById(R.id.txtChasis9_3);
                                TextView txtChasis9_4 = (TextView)findViewById(R.id.txtChasis9_4);
                                TextView txtChasis9_5 = (TextView)findViewById(R.id.txtChasis9_5);
                                TextView txtChasis9_6 = (TextView)findViewById(R.id.txtChasis9_6);
                                TextView txtChasis9_7 = (TextView)findViewById(R.id.txtChasis9_7);
                                TextView txtChasis9_8 = (TextView)findViewById(R.id.txtChasis9_8);
                                TextView txtChasis9_9 = (TextView)findViewById(R.id.txtChasis9_9);
                                bancos = "9";
                                banco1 = txtChasis9_1.getText().toString();
                                banco2 = txtChasis9_2.getText().toString();
                                banco3 = txtChasis9_3.getText().toString();
                                banco4 = txtChasis9_4.getText().toString();
                                banco5 = txtChasis9_5.getText().toString();
                                banco6 = txtChasis9_6.getText().toString();
                                banco7 = txtChasis9_7.getText().toString();
                                banco8 = txtChasis9_8.getText().toString();
                                banco9 = txtChasis9_9.getText().toString();
                                break;
                        }
                        var.setTIEMPOCARGA(false);
                        GuardarUltimosDatos();
                        GuardarPesada();
                        dialog.cancel();

                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.pirulo,fragmentInicio);
                        fragmentTransaction.commit();

                        btnGuardar.setEnabled(false);
                        btnCargar.setEnabled(true);
                        tipoDeCarga = "0";
                        chasis = 0;
                        acoplado = 0;
                        acoplado2 = 0;
                        lugar = 0;
                        Balanza.getInstance().setCominzoPesaje(false);
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
                    txtHora.getText().toString(),txtCargio.getText().toString(),edt_producto.getText().toString(),
                    edt_grua.getText().toString(),edt_operador.getText().toString(),
                    edt_vehiculo.getText().toString(),edt_codigo.getText().toString(),
                    bancos,banco1,banco2,banco3,banco4,banco5,banco6,banco7,banco8,banco9,
                    txtGarradas.getText().toString(),txt_Peso_Acumulado.getText().toString(),
                    edt_tara.getText().toString(),String.valueOf(netoDescargado))));

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
                edt_grua.getText().toString(),edt_operador.getText().toString(),
                edt_vehiculo.getText().toString(),
                edt_codigo.getText().toString(),edt_tara.getText().toString())));
        DBdatos datos;
        datos  = arrayDatos.get(0);
        db.actualizarDatos(datos,"1");
    }

    @Override
    public boolean comprobarCero(TextView textView)
    {
        boolean comprobar = false;
        int valor = Integer.valueOf(textView.getText().toString());
        if (valor != 0){
            comprobar = true;
        }else{
            comprobar = false;
        }
        return comprobar;
    }

    @Override
    public int recabarPeso(TextView textView) {
        int peso =0;
        try {
            peso = Integer.valueOf(textView.getText().toString());
        }catch (Exception e){
            peso = 0;
        }
        return peso;
    }

    @Override
    public int lugarDeCarga(int lugarCarga) {
        lugar = lugarCarga;
        return 0;
    }

    @Override
    public void enviarCero(int peso) {
        Balanza.getInstance().setPesoAcumuladoBancos(peso);
    }

    public class impresionAsyncTask extends AsyncTask<Void, Integer, Void>
    {
        int progreso;
        String hora = "", cargio = "",vehiculo ="",codigo="",producto="",operador="",grua = "", peso_acumulado="", tara = "",cargas = "";
        //String banco1 ="",banco2 ="",banco3 ="",banco4 ="",banco5 ="",banco6 ="",banco7 ="",banco8 ="",banco9 ="",cargas ="";
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            hora = txtHora.getText().toString();
            cargio = txtCargio.getText().toString();
            vehiculo = edt_vehiculo.getText().toString();
            codigo = edt_codigo.getText().toString();
            producto = edt_producto.getText().toString();
            operador = edt_operador.getText().toString();
            grua = edt_grua.getText().toString();
            peso_acumulado = txt_Peso_Acumulado.getText().toString();
            cargas = txtGarradas.getText().toString();
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
                Balanza.getInstance().ImprimirTicket("  "+getString(R.string.Tiempo_de_carga) + cargio);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  "+ getString(R.string.patente)   + vehiculo);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Codigo    : " + codigo);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  "+ getString(R.string.producto) + producto);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Cod.      : " + operador);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Grua      : " + grua);
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
                Balanza.getInstance().ImprimirTicket("  Banco-1  : " + banco1);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Banco-2  : " + banco2);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Banco-3  : " + banco3);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Banco-4  : " + banco4);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Banco-5  : " + banco5);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Banco-6  : " + banco6);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Banco-7  : " + banco7);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Banco-8  : " + banco8);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Banco-9  : " + banco9);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Cargas   : " + cargas);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Bruto    : " + peso_acumulado);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);

                Balanza.getInstance().ImprimirTicket("  Tara     : " + tara);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  "+ getString(R.string.neto) + String.valueOf(netoDescargado));
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

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            bancos ="0";
            banco1 = "";
            banco2 = "";
            banco3 = "";
            banco4 = "";
            banco5 = "";
            banco6 = "";
            banco7 = "";
            banco8 = "";
            banco9 = "";
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
            edt_grua.setText(cursorDatos.getString(2));
            edt_operador.setText(cursorDatos.getString(3));
            edt_vehiculo.setText(cursorDatos.getString(4));
            edt_codigo.setText(cursorDatos.getString(5));
            edt_tara.setText(cursorDatos.getString(6));
        }else
        {
            contentDatos.put("producto","");
            contentDatos.put("grua","");
            contentDatos.put("operador","");
            contentDatos.put("vehiculo","");
            contentDatos.put("codigo","");
            contentDatos.put("tara","");
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
            contentCalibracion.put("semiaut","1");

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
            var.setSEMIAUT("1");
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
            var.setSEMIAUT(cursorCalibracion.getString(11));
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
            comprobarSpinerProducto = false;

            final Dialog dialog = new Dialog(context,R.style.Material_Base);

            dialog.setCancelable(false);
            dialog.setTitle(R.string.dialogo_titulo);
            dialog.setContentView(R.layout.dialogo_carga_datos);

            List<String> operadores = db.getAllOperadores();
            List<String> productos = db.getAllProductos();


            final Spinner sp_producto = (Spinner) dialog.findViewById(R.id.sp_producto);
            final Spinner spTipo_carga = (Spinner) dialog.findViewById(R.id.spTipoCarga);
            final EditText edt_dialogo_vehiculo = (EditText)dialog.findViewById(R.id.edt_dialogo_vehiculo);
            final EditText edt_dialogo_codigo = (EditText)dialog.findViewById(R.id.edt_dialogo_codigo);
            final EditText edt_dialogo_tara = (EditText)dialog.findViewById(R.id.edt_dialogo_tara);
            final EditText edt_dialogo_operador = (EditText) dialog.findViewById(R.id.edt_dialogo_operador);
            final EditText edt_dialogo_grua = (EditText)dialog.findViewById(R.id.edt_dialogo_grua);
            final EditText txtTotalACargar = (EditText)dialog.findViewById(R.id.txtTotalACargar);
            final EditText edt_diaogo_Producto = (EditText)dialog.findViewById(R.id.edtDatosProducto);
            final Spinner spBuscarOperador = (Spinner) dialog.findViewById(R.id.spBuscarOperador);

            Button aceptar = (Button)dialog.findViewById(R.id.btn_acrptar);
            Button cancelar = (Button)dialog.findViewById(R.id.btn_Cancelar);

            edt_dialogo_vehiculo.setText(edt_vehiculo.getText().toString());
            edt_dialogo_operador.setText(edt_operador.getText().toString());
            edt_dialogo_tara.setText(edt_tara.getText().toString());
            edt_dialogo_codigo.setText(edt_codigo.getText().toString());
            edt_dialogo_grua.setText(edt_grua.getText().toString());
            edt_diaogo_Producto.setText(edt_producto.getText().toString());
            txtTotalACargar.setText(TotalACargar);

            ArrayAdapter<String> dataAdapterOPerador = (new ArrayAdapter<String>(getBaseContext(),R.layout.spinner_item,operadores));
            dataAdapterOPerador.setDropDownViewResource(R.layout.spinner_item);
            spBuscarOperador.setAdapter(dataAdapterOPerador);
            spBuscarOperador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   if (comprobarSpiner){
                       if(position==1){

                           //dialogoNuevoOperador();
                           //dialog.dismiss();
                       }else{

                           String mNombre = parent.getItemAtPosition(position).toString();
                           String[] nombre = {mNombre};
                           //db.db.rawQuery("SELECT codigo FROM toperador",nombre);

                           Cursor c = db.db.rawQuery("SELECT cod_operador,nombre FROM toperadores  WHERE nombre='"+mNombre+"'",null);
                           if (c.moveToFirst()){
                               edt_dialogo_operador.setText(c.getString(0));
                           }
                       }
                   }else{
                       comprobarSpiner = true;
                   }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });

            ArrayAdapter<String> dataAdapter =  new ArrayAdapter<String>(getBaseContext(),R.layout.spinner_item, productos);
            dataAdapter.setDropDownViewResource(R.layout.spinner_item);
            sp_producto.setAdapter(dataAdapter);

            sp_producto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (comprobarSpinerProducto){
                        productoSeleccionado = parent.getItemAtPosition(position).toString();
                        edt_diaogo_Producto.setText(parent.getItemAtPosition(position).toString());
                    }else{
                        comprobarSpinerProducto = true;
                        productoSeleccionado = edt_diaogo_Producto.getText().toString();
                    }

                    //var.setCAPACIDAD( parent.getItemAtPosition(position).toString());
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            ArrayAdapter<CharSequence> adapterCarga = ArrayAdapter.createFromResource(getBaseContext(),
                    R.array.tipo_carga,R.layout.spiner_modificado);
            spTipo_carga.setAdapter(adapterCarga);

            spTipo_carga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    tipoDeCarga = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String producto= productoSeleccionado , vehiculo= edt_dialogo_vehiculo.getText().toString(),
                            tara= edt_dialogo_tara.getText().toString() ,grua = edt_dialogo_grua.getText().toString()
                            ,operador= edt_dialogo_operador.getText().toString(), codigo = edt_dialogo_codigo.getText().toString(),
                            total = txtTotalACargar.getText().toString();
                    if (!(tara.equals("")  || vehiculo.equals("") || grua.equals("") || codigo.equals("") || operador.equals("") || total.equals(""))){
                        edt_vehiculo.setText(vehiculo);
                        edt_codigo.setText(codigo);
                        edt_producto.setText(producto);
                        edt_operador.setText(operador);
                        edt_tara.setText(tara);
                        edt_grua.setText(grua);
                        btnCargar.setEnabled(false);
                        TotalACargar = txtTotalACargar.getText().toString();
                        dialog.dismiss();
                        Layout_Total_Cargado.setVisibility(View.VISIBLE);
                        Balanza.getInstance().setCominzoPesaje(true);
                        /**
                         * Pone el acumulador en 0
                         */

                        Balanza.getInstance().setPesoAcumulado(Integer.valueOf(tara));

                        Balanza.getInstance().setPesoAcumuladoBancos(0);
                        Balanza.getInstance().setPesoAcumuladoCasis(0);
                        Balanza.getInstance().setCantidadGarradas(0);

                        btnGuardar.setEnabled(true);
                        var.setTIEMPOCARGA(true);
                        activarCuentaCarga();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                        switch (tipoDeCarga){
                            case "2":
                                fragmentTransaction.replace(R.id.pirulo,fragment_dos_banco);
                                if(!total.equals("")){
                                    calculadora = new Calculadora(total,tara);
                                    fragment_dos_banco.TotalXBancos(calculadora.Calcular(2));
                                }else{
                                    fragment_dos_banco.TotalXBancos(0);
                                }
                                break;
                            case "3":
                                fragmentTransaction.replace(R.id.pirulo,fragment_tres_bancos);
                                if(!total.equals("")){
                                    calculadora = new Calculadora(total,tara);
                                    fragment_tres_bancos.TotalXBancos(calculadora.Calcular(3));
                                }else{
                                    fragment_tres_bancos.TotalXBancos(0);
                                }
                                break;
                            case "4":
                                fragmentTransaction.replace(R.id.pirulo,fragment_cuatro_bancos);
                                if(!total.equals("")){
                                    calculadora = new Calculadora(total,tara);
                                    fragment_cuatro_bancos.TotalXBancos(calculadora.Calcular(4));
                                }else{
                                    fragment_cuatro_bancos.TotalXBancos(0);
                                }
                                break;
                            case "5":
                                fragmentTransaction.replace(R.id.pirulo,fragment_cinco_bancos);
                                if(!total.equals("")){
                                    calculadora = new Calculadora(total,tara);
                                    fragment_cinco_bancos.TotalXBancos(calculadora.Calcular(5));
                                }else{
                                    fragment_cinco_bancos.TotalXBancos(0);
                                }
                                break;
                            case "6":
                                fragmentTransaction.replace(R.id.pirulo,fragment_seis_bancos);
                                if(!total.equals("")){
                                    calculadora = new Calculadora(total,tara);
                                    fragment_seis_bancos.TotalXBancos(calculadora.Calcular(6));
                                }else{
                                    fragment_seis_bancos.TotalXBancos(0);
                                }
                                break;
                            case "7":
                                fragmentTransaction.replace(R.id.pirulo,fragment_siete_bancos);
                                if(!total.equals("")){
                                    calculadora = new Calculadora(total,tara);
                                    fragment_siete_bancos.TotalXBancos(calculadora.Calcular(7));
                                }else{
                                    fragment_siete_bancos.TotalXBancos(0);
                                }
                                break;
                            case "8":
                                fragmentTransaction.replace(R.id.pirulo,fragment_ocho_bancos);
                                if(!total.equals("")){
                                    calculadora = new Calculadora(total,tara);
                                    fragment_ocho_bancos.TotalXBancos(calculadora.Calcular(8));
                                }else{
                                    fragment_ocho_bancos.TotalXBancos(0);
                                }
                                break;
                            case "9":
                                fragmentTransaction.replace(R.id.pirulo,fragment_nueve_bancos);
                                if(!total.equals("")){
                                    calculadora = new Calculadora(total,tara);
                                    fragment_nueve_bancos.TotalXBancos(calculadora.Calcular(9));
                                }else{
                                    fragment_nueve_bancos.TotalXBancos(0);
                                }
                                break;
                        }
                        fragmentTransaction.commit();
                    }else {
                        Toast.makeText(getBaseContext(),R.string.tara_vacia,Toast.LENGTH_LONG).show();
                    }
                }
            });
            cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Balanza.getInstance().setCominzoPesaje(false);
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
                    case 7:
                            Intent i = new Intent(getBaseContext(),Operadores.class);
                            startActivity(i);
                        break;
                    case 8:
                            Actualizar2();
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

        final EditText edtCabeceraUno, edtCabeceraDos, edtCabeceraTres, edtCabeceraCuatro,edtCantidad;

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

                Toast.makeText(getBaseContext(), "O cabeçalho foi salvo corretamente",Toast.LENGTH_LONG).show();
                alert.dismiss();
            }
        });

        alert.show();
    }





    public void dialogoNuevoOperador()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(Principal.this);
        View promptView = layoutInflater.inflate(R.layout.dialogo_nuevo_operador, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.TemaGeneral));
        alertDialogBuilder.setView(promptView);

        final Button btnGuardar= (Button)promptView.findViewById(R.id.btnNuevoOperadorGuardar);
        final Button btnSalir= (Button)promptView.findViewById(R.id.btnNuevoOperadorSalir);
        final EditText edtNuevoOperadorNombre = (EditText)promptView.findViewById(R.id.edtNuevoOperadorNombre);
        final EditText edtNuevoOperadorCodigo = (EditText) promptView.findViewById(R.id.edtNuevoOPeradorCodigo);

        final AlertDialog alert = alertDialogBuilder.create();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<DBoperadores> arrayPesadas = new ArrayList<>(Arrays.asList(new DBoperadores(edtNuevoOperadorCodigo.getText().toString()
                        ,edtNuevoOperadorNombre.getText().toString())));

                DBoperadores dBoperadores;
                dBoperadores = arrayPesadas.get(0);
                db.InsertarOperadores(dBoperadores);
                if (arrayPesadas.isEmpty()){
                    Toast.makeText(getBaseContext(),getString(R.string.mensaje_operador_guardado_error),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getBaseContext(),getString(R.string.mensaje_operador_guardado),Toast.LENGTH_LONG).show();
                    new dialogoCargaDatos(Principal.this);
                    alert.dismiss();
                }
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new dialogoCargaDatos(Principal.this);
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
                .setPositiveButton(getString(R.string.dialgo_aceptar), new DialogInterface.OnClickListener() {
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
                                    if (Balanza.getInstance().isCominzoPesaje()){
                                       // mediaPlayer.start();
                                    }
                                }else{
                                    imgEstable.setBackgroundResource(R.drawable.circulo_inestable);
                                }
                                if (Balanza.getInstance().isGuardado())
                                {
                                    imgDescargando.setVisibility(View.VISIBLE);
                                    mediaPlayer.start();
                                    mediaPlayer.start();
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
            final String Hoja_Cargas = "<Worksheet ss:Name=\"CARGAS\">\n" +
                    "<Names><NamedRange ss:Name=\"_FilterDatabase\" ss:RefersTo=\"=CARGAS!R5C1:R5C7\" ss:Hidden=\"1\"/></Names>\n";


            final String Encabezado_Carga = "<Row ss:AutoFitHeight=\"0\">\n" +
                    "    <Cell ss:MergeAcross=\"11\" ss:MergeDown=\"3\" ss:StyleID=\"s100\"><Data\n" +
                    "      ss:Type=\"String\">Balanzas Hook ST-455</Data></Cell>\n" +
                    "   </Row>\n" +
                    "   <Row ss:AutoFitHeight=\"0\" ss:Span=\"2\"/>\n" +
                    "<Row ss:Index=\"5\">\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">DESC. ID</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">FECHA</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">HORA</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">TEMPO</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">"+getString(R.string.menu_productos)+"</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">GRUA</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">OPERADOR</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">VEICULO</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">CODIGO</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">BANCOS</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">BANCO-1</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">BANCO-2</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">BANCO-3</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">BANCO-4</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">BANCO-5</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">BANCO-6</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">BANCO-7</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">BANCO-8</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">BANCO-9</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">CARGAS</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">BRUTO</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">TARA</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +
                    "<Cell ss:StyleID=\"s73\"><Data ss:Type=\"String\">LIQ </Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n" +

                    "</Row>\n";

            final String Cerrar_Fila = "</Row>\n";
            final String Cerrar_Tabla = "</Table>\n";
            // ............... Cerrar Hojas .....................................


            final String Cerrar_Libro = "</Workbook>\n";
            //Escribir en el USB con BufferWriter
            BufferedWriter br = null;
            File file;
            if (check()){
                file = new File("/mnt/extsd/"+ fecha +"-ST455.xml");
            }else {
                File sdcard = Environment.getExternalStorageDirectory();
                file = new File(sdcard.getAbsolutePath(),  fecha +"-ST455.xml");
            }

            //file = new File("/mnt/extsd/"+ fecha +"-ST455.xml");
           // String sdcard = "storage/usbotg/usbotg-sda1";
           // File file = new File(sdcard, "Pesadas.csv");
           // BufferedWriter br = null;

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
                final String Abrir_Tabla_Cargas = "<Table ss:ExpandedColumnCount=\"23\" ss:ExpandedRowCount=\"" + (descargas + 7) + "\" x:FullColumns=\"1\"\n" +
                        "   x:FullRows=\"1\" ss:DefaultColumnWidth=\"81.75\" ss:DefaultRowHeight=\"15\">\n";

                br.append(Abrir_Tabla_Cargas);
                br.append(Encabezado_Carga);
                // br.append(Cerrar_Fila);

                Cursor c = db.db.rawQuery("SELECT * FROM tpesadas ", null);
                datos = new ArrayList<String>();
                int celdas = 0;
                String fechaCarga = "";
                String ids = "", fecha = "", hora = "", prod = "", cargio = "", grua = "",  operador = "";
                String codigo = "", vehiculo = "",nbancos= "", banco1 ="",banco2 ="",banco3 ="",banco4 ="",banco5 ="",banco6 ="",
                        banco7 ="",banco8 ="",banco9 ="",cargas ="",bruto = "", tara = "", neto = "";
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
                        cargio = c.getString(3);
                        prod = c.getString(4);
                        grua = c.getString(5);
                        operador = c.getString(6);
                        vehiculo = c.getString(7);
                        codigo = c.getString(8);
                        nbancos = c.getString(9);
                        banco1 = c.getString(10);
                        banco2 = c.getString(11);
                        banco3 = c.getString(12);
                        banco4 = c.getString(13);
                        banco5 = c.getString(14);
                        banco6 = c.getString(15);
                        banco7 = c.getString(16);
                        banco8 = c.getString(17);
                        banco9 = c.getString(18);
                        cargas = c.getString(19);
                        bruto = c.getString(20);
                        tara = c.getString(21);
                        neto = c.getString(22);

                        try {
                            br.append("<Row>\n");
                            br.append("<Cell><Data ss:Type=\"Number\">" + ids + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + fechaCarga + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + hora + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + cargio + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + prod + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + grua + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + operador + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + vehiculo + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + codigo + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + nbancos + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + banco1 + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + banco2 + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + banco3 + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + banco4 + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + banco5 + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + banco6 + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + banco7 + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + banco8 + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + banco9 + "</Data></Cell>\n");
                            br.append("<Cell><Data ss:Type=\"String\">" + cargas + "</Data></Cell>\n");
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
                        "  <AutoFilter x:Range=\"R5C1:R5C23\"\n" +
                        "   xmlns=\"urn:schemas-microsoft-com:office:excel\">\n" +
                        "  </AutoFilter>\n" +
                        "  <ss:WorksheetOptions/>\n" +
                        "  <ss:WorksheetOptions/>\n" +
                        "   </Worksheet>\n";
                br.append(Cerrar_Hoja_Cargas);
                //Cerrar Libro
                br.append(Cerrar_Libro);



                br.close();
                Toast.makeText(getBaseContext(),getString(R.string.mensaje_exportacion), Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                Toast.makeText(getBaseContext(),getString(R.string.mensaje_exportacion_error), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }


    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
     //public native String stringFromJNI();

    private boolean isExternalStorageAvailable() {

        String state = Environment.getExternalStorageState();
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Something else is wrong. It may be one of many other states, but
            // all we need
            // to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }

        if (mExternalStorageAvailable == true
                && mExternalStorageWriteable == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean check(){
        boolean usb = false;
        manager = (UsbManager) getSystemService(Context.USB_SERVICE);

        HashMap<String , UsbDevice> deviceList;
        manager.getDeviceList().clear();
        deviceList = manager.getDeviceList();
        if (deviceList.isEmpty()) {
            usb = false;
        }else{
            if (deviceList.size() > 1){
                usb = true;
            }else{
                usb = false;
            }
        }
        return  usb;
    }


    HashMap<String , UsbDevice> deviceList;
    int cnt = 0;
    private void checkInfo()
    {
        manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        // ------------------------------------------------------------------
        mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(
                ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        registerReceiver(mUsbReceiver, filter);
        // -------------------------------------------------------------------
        //HashMap<String , UsbDevice> deviceList = manager.getDeviceList();
        deviceList = manager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        String i = "";
        while (deviceIterator.hasNext()) {
            device = deviceIterator.next();

            manager.requestPermission(device, mPermissionIntent);
            i += "\n" + "DeviceID: " + device.getDeviceId() + "\n"
                    + "DeviceName: " + device.getDeviceName() + "\n"
                    + "DeviceClass: " + device.getDeviceClass() + " - "
                    + "DeviceSubClass: " + device.getDeviceSubclass() + "\n"
                    + "VendorID: " + device.getVendorId() + "\n"
                    + "ProductID: " + device.getProductId() + "\n";
        }

        cnt = deviceList.size();

    }

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = (UsbDevice) intent
                            .getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(
                            UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if (device != null) {
                            // call method to set up device communication
                        }
                    } else {
                        Log.d("ERROR", "permission denied for device " + device);
                    }
                }
            }
        }
    };


}
