package com.example.dramirez.garrraspuertoserie;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesadas);

        db = new BaseDeDatos(getBaseContext());

        txtPesadasFecha1 = (TextView)findViewById(R.id.txtPesadasFecha1);
        txtPesadasFecha2 = (TextView)findViewById(R.id.txtPesadasFecha2);
        txtPesadasTotal = (TextView)findViewById(R.id.txtPesadasTotal);
        lvPesadas= (ListView)findViewById(R.id.lvPesadas);
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
        getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Desea borrar la pesada número : " + IdPesada + " o borrar todas las pesadas" )
                .setTitle("Borrar pesada.")
                .setPositiveButton("Una Pesada", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        borrarPesada();
                    }
                })
                .setNeutralButton("Borrar Todas", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        borrarTodas();
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
                String id = "", fecha = "",hora="",  producto="", cargio ="", patente="",
                        volumen="",codigo = "", cliente = "", bruto="", tara ="", neto = "";
                if (c.moveToFirst()) {
                    do {
                        id = c.getString(0);
                        fecha = c.getString(1);
                        calendar.setTimeInMillis(Long.valueOf(fecha));
                        fecha= formato.format(calendar.getTime());
                        hora = c.getString(2);
                        producto = c.getString(3);
                        cargio =  c.getString(4);
                        patente = c.getString(5);
                        volumen = c.getString(6);
                        codigo = c.getString(7);
                        cliente = c.getString(8);
                        bruto = c.getString(9);
                        tara = c.getString(10);
                        neto = c.getString(11);

                        datos.add(new ListaEntradaPesadas( id , fecha ,  hora, producto, cargio, patente, volumen, codigo, cliente, bruto, tara, neto));
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
                                TextView txtPesadasProducto = (TextView) view.findViewById(R.id.txtPesadasProducto);
                                txtPesadasProducto.setText(((ListaEntradaPesadas) entrada).getProducto());
                                TextView txtPesadasCargio = (TextView) view.findViewById(R.id.txtPesadasCargio);
                                txtPesadasCargio.setText(((ListaEntradaPesadas) entrada).getCargio());
                                TextView txtPesadasPatente = (TextView) view.findViewById(R.id.txtPesadasPatente);
                                txtPesadasPatente.setText(((ListaEntradaPesadas) entrada).getPatente());
                                TextView txtPesadasVolumen = (TextView) view.findViewById(R.id.txtPesadasVolumen);
                                txtPesadasVolumen.setText(((ListaEntradaPesadas) entrada).getVolumen());
                                TextView txtPesadasCodigo = (TextView) view.findViewById(R.id.txtPesadasCodigo);
                                txtPesadasCodigo.setText(((ListaEntradaPesadas) entrada).getCodigo());
                                TextView txtPesadasCliente = (TextView) view.findViewById(R.id.txtPesadasCliente);
                                txtPesadasCliente.setText(((ListaEntradaPesadas) entrada).getCliente());
                                TextView txtPesadasBruto = (TextView) view.findViewById(R.id.txtPesadasBruto);
                                txtPesadasBruto.setText(((ListaEntradaPesadas) entrada).getBruto());
                                TextView txtPesadasTara = (TextView) view.findViewById(R.id.txtPesadasTara);
                                txtPesadasTara.setText(((ListaEntradaPesadas) entrada).getTara());
                                TextView txtPesadasNeto = (TextView) view.findViewById(R.id.txtPesadasNeto);
                                txtPesadasNeto.setText(((ListaEntradaPesadas) entrada).getNeto());
                            }
                        });
                    }
                });
            }

        }).start();

    }
}
