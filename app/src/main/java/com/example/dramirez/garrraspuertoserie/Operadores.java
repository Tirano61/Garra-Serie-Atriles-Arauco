package com.example.dramirez.garrraspuertoserie;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dramirez.garrraspuertoserie.Base_de_Datos.BaseDeDatos;
import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBoperadores;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Operadores extends AppCompatActivity {

    Variables v;
    BaseDeDatos db;
    ListView lvListaOperadores;
    List<String> listaOperadores;

    Button btnOperadoresSalir,btnOperadoresAgregar;
    String IdOperadores;
    ArrayList<ListaEntradaOperadores> datosOperadores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operadores);

        v = new Variables();
        db = new BaseDeDatos(getBaseContext());

        lvListaOperadores = (ListView)findViewById(R.id.lvListaOperadores);
        btnOperadoresSalir = (Button)findViewById(R.id.btnOperadoresSalir);
        btnOperadoresAgregar = (Button)findViewById(R.id.btnOperadoresNuevo);
        btnOperadoresAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoNuevoOperador();

            }
        });

        btnOperadoresSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvListaOperadores.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ListaEntradaOperadores lista = (ListaEntradaOperadores) parent.getItemAtPosition(position);
                IdOperadores = lista.getId();
                try {
                    dialogoBorrarOperadores();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        CargarOperadores();

    }

    public void dialogoNuevoOperador()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(Operadores.this);
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

                    alert.dismiss();
                    CargarOperadores();
                }
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert.dismiss();
                CargarOperadores();
            }
        });
        alert.show();
    }

    public  void CargarOperadores(){
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {

                Cursor c = db.db.rawQuery("SELECT * FROM toperadores ",null);
                datosOperadores = new ArrayList<ListaEntradaOperadores>();
                String id = "", nombre = "",codigo="";
                if (c.moveToFirst()) {
                    do {
                        id = c.getString(0);
                        nombre = c.getString(1);
                        codigo = c.getString(2);



                        datosOperadores.add(new ListaEntradaOperadores(id, nombre ,  codigo));
                    } while (c.moveToNext());
                }
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        lvListaOperadores.setAdapter(new Lista_adaptador(getApplicationContext(), R.layout.lista_operadores, datosOperadores) {
                            @Override
                            public void onEntrada(Object entrada, View view)
                            {
                                TextView txtoperadorId = (TextView) view.findViewById(R.id.txtOperadoresId);
                                txtoperadorId.setText(((ListaEntradaOperadores) entrada).getId());
                                TextView txtoperadorNombre = (TextView) view.findViewById(R.id.txtOperadoresNombre);
                                txtoperadorNombre.setText(((ListaEntradaOperadores) entrada).getNombre());
                                TextView txtoperadorCodigo = (TextView) view.findViewById(R.id.txtOperadoresCodigo);
                                txtoperadorCodigo.setText(((ListaEntradaOperadores) entrada).getCod_operador());

                            }
                        });
                    }
                });
            }

        }).start();
    }

    public void dialogoBorrarOperadores() throws InterruptedException
    {
        final AlertDialog dialog;
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.dialogo_borrar_operadores, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.TemaGeneral));
        alertDialogBuilder.setView(promptView);

        Button btnBorrar = (Button) promptView.findViewById(R.id.btnOperadoresBorrar);


        Button btnSalr = (Button) promptView.findViewById(R.id.btnOperadoresSalir);

        alertDialogBuilder.setCancelable(true);
        dialog =   alertDialogBuilder.create();


        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarPesada();
                dialog.dismiss();
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
            db.db.delete("toperadores","_id = "+ IdOperadores ,null );


            CargarOperadores();

        }catch (Exception e){}
    }

}
