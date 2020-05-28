package com.example.dramirez.garrraspuertoserie;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dramirez.garrraspuertoserie.FragmentInterfaces.EnvioDatos;


public class Fragment_siete_bancos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int BancoSeleccionado;
    int CargaXBancos = 0;
    private EnvioDatos mListener;
    TextView txtChasis7_1, txtChasis7_2, txtChasis7_3,txtChasis7_4,txtChasis7_5,txtChasis7_6,txtChasis7_7,
            txtTotal7_1,txtTotal7_2,txtTotal7_3,txtTotal7_4,txtTotal7_5,txtTotal7_6,txtTotal7_7;
    ImageButton SelectorChasis7_1,SelectorChasis7_2,SelectorChasis7_3,SelectorChasis7_4,SelectorChasis7_5,SelectorChasis7_6,SelectorChasis7_7;
    public Fragment_siete_bancos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_siete_bancos.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_siete_bancos newInstance(String param1, String param2) {
        Fragment_siete_bancos fragment = new Fragment_siete_bancos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public void TotalXBancos(int totalXbancos)
    {
        CargaXBancos = totalXbancos;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_siete_bancos, container, false);

        SelectorChasis7_1 = (ImageButton) mView.findViewById(R.id.SelectorChasis7_1);
        SelectorChasis7_2 = (ImageButton) mView.findViewById(R.id.SelectorChasis7_2);
        SelectorChasis7_3 = (ImageButton) mView.findViewById(R.id.SelectorChasis7_3);
        SelectorChasis7_4 = (ImageButton) mView.findViewById(R.id.SelectorChasis7_4);
        SelectorChasis7_5 = (ImageButton) mView.findViewById(R.id.SelectorChasis7_5);
        SelectorChasis7_6 = (ImageButton) mView.findViewById(R.id.SelectorChasis7_6);
        SelectorChasis7_7 = (ImageButton) mView.findViewById(R.id.SelectorChasis7_7);

        txtChasis7_1 = (TextView) mView.findViewById(R.id.txtTotal7_1);
        txtChasis7_2 = (TextView) mView.findViewById(R.id.txtTotal7_2);
        txtChasis7_3 = (TextView) mView.findViewById(R.id.txtTotal7_3);
        txtChasis7_4 = (TextView) mView.findViewById(R.id.txtTotal7_4);
        txtChasis7_5 = (TextView) mView.findViewById(R.id.txtTotal7_5);
        txtChasis7_6 = (TextView) mView.findViewById(R.id.txtTotal7_6);
        txtChasis7_7 = (TextView) mView.findViewById(R.id.txtTotal7_7);

        txtTotal7_1 = (TextView) mView.findViewById(R.id.txtChasis7_1);
        txtTotal7_2 = (TextView) mView.findViewById(R.id.txtChasis7_2);
        txtTotal7_3 = (TextView) mView.findViewById(R.id.txtChasis7_3);
        txtTotal7_4 = (TextView) mView.findViewById(R.id.txtChasis7_4);
        txtTotal7_5 = (TextView) mView.findViewById(R.id.txtChasis7_5);
        txtTotal7_6 = (TextView) mView.findViewById(R.id.txtChasis7_6);
        txtTotal7_7 = (TextView) mView.findViewById(R.id.txtChasis7_7);

        SelectorChasis7_1.setBackgroundColor(Color.YELLOW);
        SelectorChasis7_2.setBackgroundColor(Color.GRAY);
        SelectorChasis7_3.setBackgroundColor(Color.GRAY);
        SelectorChasis7_4.setBackgroundColor(Color.GRAY);
        SelectorChasis7_5.setBackgroundColor(Color.GRAY);
        SelectorChasis7_6.setBackgroundColor(Color.GRAY);
        SelectorChasis7_7.setBackgroundColor(Color.GRAY);
        BancoSeleccionado = 1;
        mListener.lugarDeCarga(1);
        txtTotal7_1.setText(String.valueOf(CargaXBancos));
        txtTotal7_2.setText(String.valueOf(CargaXBancos));
        txtTotal7_3.setText(String.valueOf(CargaXBancos));
        txtTotal7_4.setText(String.valueOf(CargaXBancos));
        txtTotal7_5.setText(String.valueOf(CargaXBancos));
        txtTotal7_6.setText(String.valueOf(CargaXBancos));
        txtTotal7_7.setText(String.valueOf(CargaXBancos));
        SelectorChasis7_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis7_1.setBackgroundColor(Color.YELLOW);
                SelectorChasis7_2.setBackgroundColor(Color.GRAY);
                SelectorChasis7_3.setBackgroundColor(Color.GRAY);
                SelectorChasis7_4.setBackgroundColor(Color.GRAY);
                SelectorChasis7_5.setBackgroundColor(Color.GRAY);
                SelectorChasis7_6.setBackgroundColor(Color.GRAY);
                SelectorChasis7_7.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 1;
                mListener.lugarDeCarga(1);
                if (!mListener.comprobarCero(txtChasis7_1)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis7_1));
                }
            }
        });
        SelectorChasis7_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis7_1.setBackgroundColor(Color.GRAY);
                SelectorChasis7_2.setBackgroundColor(Color.YELLOW);
                SelectorChasis7_3.setBackgroundColor(Color.GRAY);
                SelectorChasis7_4.setBackgroundColor(Color.GRAY);
                SelectorChasis7_5.setBackgroundColor(Color.GRAY);
                SelectorChasis7_6.setBackgroundColor(Color.GRAY);
                SelectorChasis7_7.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 2;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis7_2)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis7_2));
                }
            }
        });
        SelectorChasis7_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis7_1.setBackgroundColor(Color.GRAY);
                SelectorChasis7_2.setBackgroundColor(Color.GRAY);
                SelectorChasis7_3.setBackgroundColor(Color.YELLOW);
                SelectorChasis7_4.setBackgroundColor(Color.GRAY);
                SelectorChasis7_5.setBackgroundColor(Color.GRAY);
                SelectorChasis7_6.setBackgroundColor(Color.GRAY);
                SelectorChasis7_7.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 3;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis7_3)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis7_3));
                }
            }
        });
        SelectorChasis7_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis7_1.setBackgroundColor(Color.GRAY);
                SelectorChasis7_2.setBackgroundColor(Color.GRAY);
                SelectorChasis7_3.setBackgroundColor(Color.GRAY);
                SelectorChasis7_4.setBackgroundColor(Color.YELLOW);
                SelectorChasis7_5.setBackgroundColor(Color.GRAY);
                SelectorChasis7_6.setBackgroundColor(Color.GRAY);
                SelectorChasis7_7.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 4;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis7_4)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis7_4));
                }
            }
        });
        SelectorChasis7_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis7_1.setBackgroundColor(Color.GRAY);
                SelectorChasis7_2.setBackgroundColor(Color.GRAY);
                SelectorChasis7_3.setBackgroundColor(Color.GRAY);
                SelectorChasis7_4.setBackgroundColor(Color.GRAY);
                SelectorChasis7_5.setBackgroundColor(Color.YELLOW);
                SelectorChasis7_6.setBackgroundColor(Color.GRAY);
                SelectorChasis7_7.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 5;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis7_5)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis7_5));
                }
            }
        });
        SelectorChasis7_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis7_1.setBackgroundColor(Color.GRAY);
                SelectorChasis7_2.setBackgroundColor(Color.GRAY);
                SelectorChasis7_3.setBackgroundColor(Color.GRAY);
                SelectorChasis7_4.setBackgroundColor(Color.GRAY);
                SelectorChasis7_5.setBackgroundColor(Color.GRAY);
                SelectorChasis7_6.setBackgroundColor(Color.YELLOW);
                SelectorChasis7_7.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 6;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis7_6)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis7_6));
                }
            }
        });
        SelectorChasis7_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis7_1.setBackgroundColor(Color.GRAY);
                SelectorChasis7_2.setBackgroundColor(Color.GRAY);
                SelectorChasis7_3.setBackgroundColor(Color.GRAY);
                SelectorChasis7_4.setBackgroundColor(Color.GRAY);
                SelectorChasis7_5.setBackgroundColor(Color.GRAY);
                SelectorChasis7_6.setBackgroundColor(Color.GRAY);
                SelectorChasis7_7.setBackgroundColor(Color.YELLOW);
                BancoSeleccionado = 7;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis7_7)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis7_7));
                }
            }
        });
        return mView;
    }

    public void recibirPeso(String peso){
        switch (BancoSeleccionado){
            case 1:
                txtChasis7_1.setText(peso);
                if (Integer.valueOf(txtChasis7_1.getText().toString()) < Integer.valueOf(txtTotal7_1.getText().toString())){
                    txtTotal7_1.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal7_1.getText().toString()) ){
                    txtTotal7_1.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis7_1.getText().toString()) > Integer.valueOf(txtTotal7_1.getText().toString())){
                    txtTotal7_1.setBackgroundColor(Color.RED);
                }
                break;
            case 2:
                txtChasis7_2.setText(peso);
                if (Integer.valueOf(txtChasis7_2.getText().toString()) < Integer.valueOf(txtTotal7_2.getText().toString())){
                    txtTotal7_2.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal7_2.getText().toString()) ){
                    txtTotal7_2.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis7_2.getText().toString()) > Integer.valueOf(txtTotal7_2.getText().toString())){
                    txtTotal7_2.setBackgroundColor(Color.RED);
                }
                break;
            case 3:
                txtChasis7_3.setText(peso);
                if (Integer.valueOf(txtChasis7_3.getText().toString()) < Integer.valueOf(txtTotal7_3.getText().toString())){
                    txtTotal7_3.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal7_3.getText().toString()) ){
                    txtTotal7_3.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis7_3.getText().toString()) > Integer.valueOf(txtTotal7_3.getText().toString())){
                    txtTotal7_3.setBackgroundColor(Color.RED);
                }
                break;
            case 4:
                txtChasis7_4.setText(peso);
                if (Integer.valueOf(txtChasis7_4.getText().toString()) < Integer.valueOf(txtTotal7_4.getText().toString())){
                    txtTotal7_4.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal7_4.getText().toString()) ){
                    txtTotal7_4.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis7_4.getText().toString()) > Integer.valueOf(txtTotal7_4.getText().toString())){
                    txtTotal7_4.setBackgroundColor(Color.RED);
                }
                break;
            case 5:
                txtChasis7_5.setText(peso);
                if (Integer.valueOf(txtChasis7_5.getText().toString()) < Integer.valueOf(txtTotal7_5.getText().toString())){
                    txtTotal7_5.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal7_5.getText().toString()) ){
                    txtTotal7_5.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis7_5.getText().toString()) > Integer.valueOf(txtTotal7_5.getText().toString())){
                    txtTotal7_5.setBackgroundColor(Color.RED);
                }
                break;
            case 6:
                txtChasis7_6.setText(peso);
                if (Integer.valueOf(txtChasis7_6.getText().toString()) < Integer.valueOf(txtTotal7_6.getText().toString())){
                    txtTotal7_6.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal7_6.getText().toString()) ){
                    txtTotal7_6.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis7_6.getText().toString()) > Integer.valueOf(txtTotal7_6.getText().toString())){
                    txtTotal7_6.setBackgroundColor(Color.RED);
                }
                break;
            case 7:
                txtChasis7_7.setText(peso);
                if (Integer.valueOf(txtChasis7_7.getText().toString()) < Integer.valueOf(txtTotal7_7.getText().toString())){
                    txtTotal7_7.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal7_7.getText().toString()) ){
                    txtTotal7_7.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis7_7.getText().toString()) > Integer.valueOf(txtTotal7_7.getText().toString())){
                    txtTotal7_7.setBackgroundColor(Color.RED);
                }
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EnvioDatos) {
            mListener = (EnvioDatos) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
