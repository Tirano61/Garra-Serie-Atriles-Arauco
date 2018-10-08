package com.example.dramirez.garrraspuertoserie;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dramirez.garrraspuertoserie.Base_de_Datos.BaseDeDatos;

public class Productos extends AppCompatActivity {
    EditText edt_producto[]= new EditText[12];
    BaseDeDatos db;
    String[] nombres = {"Pino", "Eucaliptus","Producto 1","Producto 2","Producto 3","Producto 4","Producto 5",
            "Producto 6","Producto 7","Producto 8","Producto 9","Producto 10"};
    String[] numeros = {"1","2","3","4","5","6","7","8","9","10","11","12"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        edt_producto[0] = (EditText)findViewById(R.id.edt_productos1);
        edt_producto[1] = (EditText)findViewById(R.id.edt_productos2);
        edt_producto[2] = (EditText)findViewById(R.id.edt_productos3);
        edt_producto[3] = (EditText)findViewById(R.id.edt_productos4);
        edt_producto[4] = (EditText)findViewById(R.id.edt_productos5);
        edt_producto[5] = (EditText)findViewById(R.id.edt_productos6);
        edt_producto[6] = (EditText)findViewById(R.id.edt_productos7);
        edt_producto[7] = (EditText)findViewById(R.id.edt_productos8);
        edt_producto[8] = (EditText)findViewById(R.id.edt_productos9);
        edt_producto[9] = (EditText)findViewById(R.id.edt_productos10);
        edt_producto[10] = (EditText)findViewById(R.id.edt_productos11);
        edt_producto[11] = (EditText)findViewById(R.id.edt_productos12);

        db = new BaseDeDatos(getBaseContext());

        CargarProductos();

    }

    public void CargarProductos()
    {
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

    public void onClickProductos(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_dialogo_productos_aceptar:
                GuardarProductos();
                break;
            case R.id.btn_dialogo_productos_cancelar:
                this.finish();
                break;
        }
    }

    public void GuardarProductos()
    {
        ContentValues contentValues = new ContentValues();
        String GUARDADO = "Se ha guardado producto :";
        for (int i = 0; i <= 11; i++ )
        {
            contentValues.put("numero",String.valueOf(i+1));
            contentValues.put("nombre",edt_producto[i].getText().toString());
            db.db.update("tproductos",contentValues,"numero="+ numeros[i],null);
        }
        Toast.makeText(getApplicationContext(),"Los productos se guardaron correctamente",Toast.LENGTH_LONG).show();

    }
}
