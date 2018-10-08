package com.example.dramirez.garrraspuertoserie;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dramirez.garrraspuertoserie.Base_de_Datos.BaseDeDatos;
import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBcalibracion;

import java.util.ArrayList;
import java.util.Arrays;

public class Calibracion extends AppCompatActivity {

    EditText edtPesoMaximo, edtTotalCeldas,edtDivision,edtSensibilidad,edtVentana,edtKgFiltro,
            edtConversiones, edtRecortes, edtlogica;

    BaseDeDatos db;
    Integer counter = 1;

    Variables var;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibracion);

          edtPesoMaximo = (EditText)findViewById(R.id.edtCapacidadMaxima);
          edtTotalCeldas = (EditText)findViewById(R.id.edtTotalCeldas);
          edtDivision = (EditText)findViewById(R.id.edtDivisionMinima);
          edtSensibilidad = (EditText)findViewById(R.id.edtSensibilidad);
          edtVentana = (EditText)findViewById(R.id.edtVentana);
          edtKgFiltro = (EditText)findViewById(R.id.edtKgFiltro);
          edtConversiones = (EditText)findViewById(R.id.edtConversiones);
          edtRecortes = (EditText)findViewById(R.id.edtRecortes);
          edtlogica = (EditText)findViewById(R.id.edt_dialogo_logica);

          db =  new BaseDeDatos(getBaseContext());
          var = Balanza.getInstance().getVariables();

          CargarCalibracion();

    }

    /**
     * Carga la calibracion al iniciar la pantalla
     */
    private void CargarCalibracion() {
        Cursor cursor = db.db.rawQuery("SELECT * FROM tcalibracion",null);
        if (cursor.moveToFirst())
        {
            edtPesoMaximo.setText(cursor.getString(1));
            edtTotalCeldas.setText(cursor.getString(2));
            edtDivision.setText(cursor.getString(3));
            edtSensibilidad.setText(cursor.getString(4));
            edtVentana.setText(cursor.getString(5));
            edtKgFiltro.setText(cursor.getString(6));
            edtConversiones.setText(cursor.getString(7));
            edtRecortes.setText(cursor.getString(8));

        }else{

        }
    }

    public void onClickCalibracion(View v)
    {
        switch (v.getId())
        {
            case R.id.btnCalibracionAceptar:
                    Guardarcalibracion();
                break;
            case R.id.btnCalibracionCancelar:
                    Balanza.getInstance().pedirCuentas();
                    this.finish();
                break;
        }
    }

    private void Guardarcalibracion()
    {
        counter = 1;

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
            logica = edtlogica.getText().toString();;
            ArrayList<DBcalibracion> arrayCalibracion = new ArrayList<DBcalibracion>(Arrays.asList(new DBcalibracion(capac,celdas,
                    division,sensib,vent,kgF,conv,recort,logica)));

            var.setCAPACIDAD(capac);
            var.setCELDAS(celdas);
            var.setDIVISION(division);
            var.setSENSIBILIDAD(sensib);
            var.setVENTANA(Integer.valueOf(vent));
            var.setKGFILTRO(Integer.valueOf(kgF));
            var.setCONVERSIONES(conv);
            var.setRECORTES(recort);
            var.setLOGICA(logica);

            DBcalibracion CALI = arrayCalibracion.get(0);

            db.actualizarCalibracion(CALI,"1");
            String envio = "AT+PARAM=" + conv +","+ recort + "," + logica + '\r'+'\n';
            Balanza.getInstance().getOK();
            Balanza.getInstance().EnviarCalibracion(envio);

            /**
             * Para verificar la calibracion
             */
            if (Balanza.getInstance().getOK() == 1){
                Toast.makeText(getBaseContext(),R.string.mensaje_guardado_calibracion,Toast.LENGTH_LONG).show();
            }else if (Balanza.getInstance().getOK() == 0){
                Toast.makeText(getBaseContext(),"La balanza devovlvió FAIL",Toast.LENGTH_LONG).show();
            }else if (Balanza.getInstance().getOK() == -1){
                Toast.makeText(getBaseContext(),"Devolvio -1 la puta",Toast.LENGTH_LONG).show();
            }

            //* todo: cerrar dialogo espera

        }catch (Exception e){
            Toast.makeText(getBaseContext(),R.string.mensaje_guardado_calibracion_error,Toast.LENGTH_LONG).show();
        }
    }
}
