package com.example.dramirez.garrraspuertoserie;

import android.os.Environment;

import com.example.dramirez.garrraspuertoserie.Base_de_Datos.DBpesadas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CopiarPesadas {

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat formato = new SimpleDateFormat("dd'/'MM'/'yyyy");

    void exportarPesadas (DBpesadas dbpesadas)
    {
        try {
            String fecha =dbpesadas.getFecha();
            calendar.setTimeInMillis(Long.valueOf(fecha));
            fecha= formato.format(calendar.getTime());
            FileWriter file = null;

            File docsFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"copiaDB.csv" );


            file = new FileWriter(docsFolder, true);
            BufferedWriter osw = new BufferedWriter (file);


            osw.write(
                    dbpesadas.getIdPesada() + ";" +          //0
                    fecha+";"+                                     //1
                    dbpesadas.getHora()+";"+                       //2
                    dbpesadas.getArribo()+";"+                     //3
                    dbpesadas.getIdgrua()+";"+                     //4
                    dbpesadas.getChasis()+";"+                     //5
                    dbpesadas.getAcoplado()+";"+                   //6
                    dbpesadas.getAcoplado2()+";"+                  //7
                    dbpesadas.getRemito()+";"+                     //8
                    dbpesadas.getDestino()+";"+                    //9
                    dbpesadas.getProducto()+";"+                   //10
                    dbpesadas.getMedida_aserrable()+";"+           //11
                    dbpesadas.getRodal()+";"+                      //12
                    dbpesadas.getFecha_corte()+";"+                //13
                    dbpesadas.getOperador()+";"+                   //14
                    dbpesadas.getActa_intervencion()+";"+          //15
                    dbpesadas.getTipo_intervencion()+";"+          //16
                    dbpesadas.getPredio()+";"+                     //17
                    dbpesadas.getUmf()+";"+                        //18
                    dbpesadas.getProveedor_elavoracion()+";"+      //19
                    dbpesadas.getProveedor_carga()+";"+            //20
                    dbpesadas.getRaiz_remito()+";"+                //21
                    dbpesadas.getBancos()+";"+                     //22
                    dbpesadas.getBanco1()+";"+                     //23
                    dbpesadas.getBanco2()+";"+                     //24
                    dbpesadas.getBanco3()+";"+                     //25
                    dbpesadas.getBanco4()+";"+                     //26
                    dbpesadas.getBanco5()+";"+                     //27
                    dbpesadas.getBanco6()+";"+                     //28
                    dbpesadas.getBanco7()+";"+                     //29
                    dbpesadas.getBanco8()+";"+                     //30
                    dbpesadas.getBanco9()+";"+                     //31
                    dbpesadas.getCargas()+";"+                     //32
                    dbpesadas.getBruto()+";"+                      //33
                    dbpesadas.getTara()+";"+                       //34
                    dbpesadas.getNeto()+";"+                       //35
                    dbpesadas.getTiempo_carga() +"\r\n");          //36



            osw.flush();
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}