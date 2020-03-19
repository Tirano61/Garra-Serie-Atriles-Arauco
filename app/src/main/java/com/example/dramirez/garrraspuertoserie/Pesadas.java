package com.example.dramirez.garrraspuertoserie;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dramirez.garrraspuertoserie.Base_de_Datos.BaseDeDatos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Pesadas extends AppCompatActivity {

    int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;



    SimpleDateFormat formato = new SimpleDateFormat("dd'/'MM'/'yyyy");
    Calendar calendar = Calendar.getInstance();
    java.util.Date fechaC1 = new java.util.Date();
    java.util.Date fechaC2 = new java.util.Date();
    String fecha1,fecha2,desde,hasta;

    BaseDeDatos db;
    TextView txtPesadasFecha1,txtPesadasFecha2,txtPesadasTotal;
    ListView lvPesadas;

    String IdPesada;
    ArrayList<ListaEntradaPesadas> datos;

    String fecha;
    String fechaImpresion ="", hora = "", cargio = "",grua ="",operador="",producto="",vehiculo="",codigo = "", peso_acumulado="", tara = "",cargas = "";
    String bancos,banco1,banco2,banco3,banco4,banco5,banco6,banco7,banco8,banco9;
    ProgressBar progressBar;
    int netoDescargado;
    Variables var ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesadas);
        var = new Variables();
        db = new BaseDeDatos(getBaseContext());

        txtPesadasFecha1 = (TextView)findViewById(R.id.txtPesadasFecha1);
        txtPesadasFecha2 = (TextView)findViewById(R.id.txtPesadasFecha2);
        txtPesadasTotal = (TextView)findViewById(R.id.txtPesadasTotal);
        lvPesadas= (ListView)findViewById(R.id.lvPesadas);
        progressBar = (ProgressBar)findViewById(R.id.progressBarReimpresion);
        fecha = new SimpleDateFormat("dd/MM/yyyy").format (new Date());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            desde = bundle.getString("inicio");
            hasta = bundle.getString("final");
        }
        try {
            fechaC1 =  formato.parse(desde);
            fechaC2 =  formato.parse(hasta);
            Calendar calendar = Calendar.getInstance();
            fecha1= String.valueOf( fechaC1.getTime());
            fecha2= String.valueOf( fechaC2.getTime());
            mostrarPesadas();
            txtPesadasTotal.setText(String.valueOf(Total_descarga()));
            txtPesadasFecha1.setText(String.valueOf(desde));
            txtPesadasFecha2.setText(String.valueOf(hasta));
        } catch(Exception e) {
            System.out.println("Error occurred"+ e.getMessage());
        }
        lvPesadas.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ListaEntradaPesadas lista = (ListaEntradaPesadas) parent.getItemAtPosition(position);
                IdPesada = lista.getIdPesada();
                try {
                    dialogoBorrarPesadas();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void dialogoBorrarPesadas() throws InterruptedException
    {
        final AlertDialog dialog;
        LayoutInflater layoutInflater = LayoutInflater.from(Pesadas.this);
        View promptView = layoutInflater.inflate(R.layout.dialogo_borrado_reimpresion, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.TemaGeneral));
        alertDialogBuilder.setView(promptView);

        Button btnBorrar = (Button) promptView.findViewById(R.id.btnBorrar);
        Button btnBorrarTodas = (Button) promptView.findViewById(R.id.btnBorrarTodas);
        Button btnImprimir = (Button) promptView.findViewById(R.id.btnImprimir);
        Button btnSalr = (Button) promptView.findViewById(R.id.btnNuevoOperadorSalir);

        alertDialogBuilder.setCancelable(true);
        dialog =   alertDialogBuilder.create();


        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarPesada();
                dialog.dismiss();
            }
        });
        btnBorrarTodas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarTodas();
            }
        });
        btnImprimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor c = db.db.rawQuery("SELECT * FROM tpesadas WHERE _id ='"+IdPesada+"'",null);
                if (c.moveToFirst()){

                    fechaImpresion = c.getString(1);
                    hora = c.getString(2);
                    cargio = c.getString(3);
                    producto = c.getString(4);
                    grua = c.getString(5);//grua
                    operador = c.getString(6);//operador
                    vehiculo = c.getString(7);//vehiculo
                    codigo = c.getString(8);//codigo
                    bancos = c.getString(9);
                    banco1 = c.getString(10);
                    banco2 = c.getString(11);
                    banco3 = c.getString(12);
                    banco4 = c.getString(13);
                    banco5 = c.getString(14);
                    banco6 = c.getString(15);
                    banco7 = c.getString(16);
                    banco8 = c.getString(17);
                    banco9 = c.getString(18);
                    peso_acumulado = c.getString(20);
                    tara = c.getString(21);
                    cargas = c.getString(19);

                    netoDescargado = Integer.valueOf(peso_acumulado) - Integer.valueOf(tara);

                    new impresionAsyncTask().execute();
                    dialog.dismiss();
                }
            }
        });
        btnSalr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }

    public void borrarPesada()
    {
        try {
            db.db.delete("tpesadas","_id = "+ IdPesada ,null );
            txtPesadasTotal.setText(String.valueOf(Total_descarga()));

            mostrarPesadas();

        }catch (Exception e){}
    }

    public void borrarTodas()
    {
        try {
            dialogoEliminacion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int Total_descarga()
    {
        int serving =0;
        Cursor cursor = db.db.rawQuery("SELECT SUM(neto) FROM tpesadas WHERE fecha BETWEEN '"+ fecha1 +"' AND '" + fecha2 + "'", null);
        if(cursor.moveToFirst()) {
            serving = cursor.getInt(0);
        }
        return serving;
    }

    public void dialogoEliminacion() throws  InterruptedException
    {
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Desea borrar todas las pesadas" )
                .setTitle("Borrar pesadas.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            db.borrarPesadas();
                            txtPesadasTotal.setText(String.valueOf(Total_descarga()));
                            //mensajeGuardado("La pesadas se eliminaron correcetamente");
                            mostrarPesadas();
                        }catch (Exception e){}
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        Dialog dialogo = builder.create();
        Thread.sleep(1500);
        dialogo.show();
    }

    public void  mostrarPesadas()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Cursor c = db.db.rawQuery("SELECT * FROM tpesadas WHERE fecha BETWEEN '"+ fecha1 +"' AND '" + fecha2 + "'", null);
                datos = new ArrayList<ListaEntradaPesadas>();
                String id = "", fecha  = "",  hora = "", grua = "", chasis = "", acoplado = "", remito = "", tara = "",destino = "", producto = "",
                        aserrable = "", rodal = "", fCorte = "",operador = "",aIntervencion = "",tIntervencion = "",predio = "",umf = "",pElab = "",pCarga = "",
                        raiz = "", cargio = "",bruto = "",neto = "";
                if (c.moveToFirst()) {
                    do {
                        id = c.getString(0);
                        fecha = c.getString(1);
                        calendar.setTimeInMillis(Long.valueOf(fecha));
                        fecha= formato.format(calendar.getTime());
                        hora = c.getString(2);
                        grua =  c.getString(3);

                        chasis=  c.getString(4);
                        acoplado =  c.getString(5);
                        remito =  c.getString(6);
                        destino =  c.getString(7);
                        producto = c.getString(8);
                        aserrable =  c.getString(9);
                        rodal =  c.getString(10);
                        fCorte = c.getString(11);
                        operador = c.getString(12);
                        aIntervencion = c.getString(13);
                        tIntervencion = c.getString(14);
                        predio = c.getString(15);
                        umf = c.getString(16);
                        pElab = c.getString(17);
                        pCarga = c.getString(18);
                        raiz  = c.getString(19);
                        bruto =  c.getString(31);
                        tara = c.getString(32);
                        neto = c.getString(33);
                        cargio = c.getString(34);

                        datos.add(new ListaEntradaPesadas(id , fecha ,  hora, grua, chasis, acoplado, remito, tara,destino, producto,
                                                          aserrable, rodal, fCorte,operador,aIntervencion,tIntervencion,predio,umf,pElab,pCarga,
                                                            raiz,bruto,neto, cargio));
                    } while (c.moveToNext());
                }
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        lvPesadas.setAdapter(new Lista_adaptador(Pesadas.this, R.layout.lista_pesadas, datos) {
                            @Override
                            public void onEntrada(Object entrada, View view)
                            {
                                TextView txtPesadasId = (TextView) view.findViewById(R.id.txtPesadasId);
                                txtPesadasId.setText(((ListaEntradaPesadas) entrada).getIdPesada());
                                TextView txtPesadasFecha = (TextView) view.findViewById(R.id.txtPesadasFecha);
                                txtPesadasFecha.setText(((ListaEntradaPesadas) entrada).getFecha());
                                TextView txtPesadasHora = (TextView) view.findViewById(R.id.txtPesadasHora);
                                txtPesadasHora.setText(((ListaEntradaPesadas) entrada).getHora());

                                TextView txtPesadasGrua = (TextView) view.findViewById(R.id.txtPesadasGrua);
                                txtPesadasGrua.setText(((ListaEntradaPesadas) entrada).getIdgrua());
                                TextView txtPesadasChasis = (TextView) view.findViewById(R.id.txtPesadasChasis);
                                txtPesadasChasis.setText(((ListaEntradaPesadas) entrada).getChasis());
                                TextView txtPesadasAcoplado = (TextView) view.findViewById(R.id.txtPesadasAcoplado);
                                txtPesadasAcoplado.setText(((ListaEntradaPesadas) entrada).getAcoplado());
                                TextView txtPesadasRemito = (TextView) view.findViewById(R.id.txtPesadasRemito);
                                txtPesadasRemito.setText(((ListaEntradaPesadas) entrada).getRemito());
                                TextView txtPesadasTara = (TextView) view.findViewById(R.id.txtPesadasTara);
                                txtPesadasTara.setText(((ListaEntradaPesadas) entrada).getTara());

                                TextView txtPesadasDestino = (TextView) view.findViewById(R.id.txtPesadasDestino);
                                txtPesadasDestino.setText(((ListaEntradaPesadas) entrada).getDestino());

                                TextView txtPesadasProducto = (TextView) view.findViewById(R.id.txtPesadasProducto);
                                txtPesadasProducto.setText(((ListaEntradaPesadas) entrada).getProducto());

                                TextView txtPesadasAserrable = (TextView) view.findViewById(R.id.txtPesadasAserrable);
                                txtPesadasAserrable.setText(((ListaEntradaPesadas) entrada).getMedida_aserrable());

                                TextView txtPesadasRodal = (TextView) view.findViewById(R.id.txtPesadasRodal);
                                txtPesadasRodal.setText(((ListaEntradaPesadas) entrada).getRodal());

                                TextView txtPesadasFCorte = (TextView) view.findViewById(R.id.txtPesadasFCorte);
                                txtPesadasFCorte.setText(((ListaEntradaPesadas) entrada).getFecha_corte());

                                TextView txtPesadasOperador = (TextView) view.findViewById(R.id.txtPesadasOperador);
                                txtPesadasOperador.setText(((ListaEntradaPesadas) entrada).getOperador());

                                TextView txtPesadasAIntervencion = (TextView) view.findViewById(R.id.txtPesadasAIntervencion);
                                txtPesadasAIntervencion.setText(((ListaEntradaPesadas) entrada).getActa_intervencion());

                                TextView txtPesadasTIntervencion = (TextView) view.findViewById(R.id.txtPesadasTIntervencion);
                                txtPesadasTIntervencion.setText(((ListaEntradaPesadas) entrada).getTipo_intervencion());

                                TextView txtPesadasPredio = (TextView) view.findViewById(R.id.txtPesadasPredio);
                                txtPesadasPredio.setText(((ListaEntradaPesadas) entrada).getPredio());

                                TextView txtPesadasUmf = (TextView) view.findViewById(R.id.txtPesadasUmf);
                                txtPesadasUmf.setText(((ListaEntradaPesadas) entrada).getUmf());

                                TextView txtPesadasPElaboracion = (TextView) view.findViewById(R.id.txtPesadasPElaboracion);
                                txtPesadasPElaboracion.setText(((ListaEntradaPesadas) entrada).getProveedor_elavoracion());

                                TextView txtPesadasPCarga = (TextView) view.findViewById(R.id.txtPesadasPCarga);
                                txtPesadasPCarga.setText(((ListaEntradaPesadas) entrada).getProveedor_carga());

                                TextView txtPesadasRaiz = (TextView) view.findViewById(R.id.txtPesadasRaiz);
                                txtPesadasRaiz.setText(((ListaEntradaPesadas) entrada).getRaiz_remito());
                                TextView txtPesadasCargio = (TextView) view.findViewById(R.id.txtPesadasCargio);
                                txtPesadasCargio.setText(((ListaEntradaPesadas) entrada).getTiempo_carga());
                                TextView txtPesadasBruto= (TextView) view.findViewById(R.id.txtPesadasBruto);
                                txtPesadasBruto.setText(((ListaEntradaPesadas) entrada).getBruto());
                                TextView txtPesadasNeto = (TextView) view.findViewById(R.id.txtPesadasNeto);
                                txtPesadasNeto.setText(((ListaEntradaPesadas) entrada).getNeto());
                            }
                        });
                    }
                });
            }

        }).start();

    }





    public class impresionAsyncTask extends AsyncTask<Void, Integer, Void>
    {
        int progreso;
        //String hora = "", cargio = "",vehiculo ="",codigo="",producto="",operador="",grua = "", peso_acumulado="", tara = "",cargas = "";
        //String banco1 ="",banco2 ="",banco3 ="",banco4 ="",banco5 ="",banco6 ="",banco7 ="",banco8 ="",banco9 ="",cargas ="";
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);

            progreso = 0;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for (int i =1; i <= Variables.getTICKETS(); i++)
            {
                Balanza.getInstance().ImprimirTicket("       BALANZAS HOOK SA");
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                if (!Variables.getCabecera1().equals(null))
                {
                    if (!Variables.getCabecera1().equals("")) {
                        Balanza.getInstance().ImprimirTicket("  " + Variables.getCabecera1());
                        Balanza.getInstance().getOK();
                        progreso++;
                        publishProgress(progreso);
                    }
                }
                if (!Variables.getCabecera4().equals(null))
                {
                    if (!Variables.getCabecera2().equals("")){
                        Balanza.getInstance().ImprimirTicket("  "+ Variables.getCabecera2());
                        Balanza.getInstance().getOK();
                        progreso++;
                        publishProgress(progreso);
                    }
                }

                if (!Variables.getCabecera4().equals(null))
                {
                    if (!Variables.getCabecera3().equals(""))
                    {
                        Balanza.getInstance().ImprimirTicket("  "+ Variables.getCabecera3());
                        Balanza.getInstance().getOK();
                        progreso++;
                        publishProgress(progreso);
                    }
                }

                if (!Variables.getCabecera4().equals(null))
                {
                    if (!Variables.getCabecera4().equals(""))
                    {
                        Balanza.getInstance().ImprimirTicket("  "+ Variables.getCabecera4());
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

   /* public class impresionAsyncTask extends AsyncTask<Void, Integer, Void>
    {
        int progreso;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);

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
                Balanza.getInstance().ImprimirTicket("  Vehículo : " + vehiculo);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Codigo    : " + codigo);
                Balanza.getInstance().getOK();
                progreso++;
                publishProgress(progreso);
                Balanza.getInstance().ImprimirTicket("  Producto  : " + producto);
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
                Balanza.getInstance().ImprimirTicket("  Neto     : " + String.valueOf(netoDescargado));
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
    }*/




}
