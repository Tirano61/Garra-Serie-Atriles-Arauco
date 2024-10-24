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


public class Fragment_seis_bancos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EnvioDatos mListener;
    TextView txtChasis6_1, txtChasis6_2, txtChasis6_3, txtChasis6_4, txtChasis6_5, txtChasis6_6,
            txtTotal6_1,txtTotal6_2,txtTotal6_3,txtTotal6_4,txtTotal6_5,txtTotal6_6;
    ImageButton SelectorChasis6_1,SelectorChasis6_2,SelectorChasis6_3,SelectorChasis6_4,SelectorChasis6_5,SelectorChasis6_6;
    int BancoSeleccionado =0;
    int CargaXBancos = 0;
    public Fragment_seis_bancos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_seis_bancos.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_seis_bancos newInstance(String param1, String param2) {
        Fragment_seis_bancos fragment = new Fragment_seis_bancos();
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

    public void recibirPeso(String peso){
        switch (BancoSeleccionado){
            case 1:
                txtChasis6_1.setText(peso);
                if (Integer.valueOf(txtChasis6_1.getText().toString()) < Integer.valueOf(txtTotal6_1.getText().toString())){
                    txtTotal6_1.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal6_1.getText().toString()) ){
                    txtTotal6_1.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis6_1.getText().toString()) > Integer.valueOf(txtTotal6_1.getText().toString())){
                    txtTotal6_1.setBackgroundColor(Color.RED);
                }
                break;
            case 2:
                txtChasis6_2.setText(peso);
                if (Integer.valueOf(txtChasis6_2.getText().toString()) < Integer.valueOf(txtTotal6_2.getText().toString())){
                    txtTotal6_2.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal6_2.getText().toString()) ){
                    txtTotal6_2.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis6_2.getText().toString()) > Integer.valueOf(txtTotal6_2.getText().toString())){
                    txtTotal6_2.setBackgroundColor(Color.RED);
                }
                break;
            case 3:
                txtChasis6_3.setText(peso);
                if (Integer.valueOf(txtChasis6_3.getText().toString()) < Integer.valueOf(txtTotal6_3.getText().toString())){
                    txtTotal6_3.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal6_3.getText().toString()) ){
                    txtTotal6_3.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis6_3.getText().toString()) > Integer.valueOf(txtTotal6_3.getText().toString())){
                    txtTotal6_3.setBackgroundColor(Color.RED);
                }
                break;
            case 4:
                txtChasis6_4.setText(peso);
                if (Integer.valueOf(txtChasis6_4.getText().toString()) < Integer.valueOf(txtTotal6_4.getText().toString())){
                    txtTotal6_4.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal6_4.getText().toString()) ){
                    txtTotal6_4.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis6_4.getText().toString()) > Integer.valueOf(txtTotal6_4.getText().toString())){
                    txtTotal6_4.setBackgroundColor(Color.RED);
                }
                break;
            case 5:
                txtChasis6_5.setText(peso);
                if (Integer.valueOf(txtChasis6_5.getText().toString()) < Integer.valueOf(txtTotal6_5.getText().toString())){
                    txtTotal6_5.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal6_5.getText().toString()) ){
                    txtTotal6_5.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis6_5.getText().toString()) > Integer.valueOf(txtTotal6_5.getText().toString())){
                    txtTotal6_5.setBackgroundColor(Color.RED);
                }
                break;
            case 6:
                txtChasis6_6.setText(peso);
                if (Integer.valueOf(txtChasis6_6.getText().toString()) < Integer.valueOf(txtTotal6_6.getText().toString())){
                    txtTotal6_6.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal6_6.getText().toString()) ){
                    txtTotal6_6.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis6_6.getText().toString()) > Integer.valueOf(txtTotal6_6.getText().toString())){
                    txtTotal6_6.setBackgroundColor(Color.RED);
                }
                break;
        }
    }
    public void TotalXBancos(int totalXbancos)
    {
        CargaXBancos = totalXbancos;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_seis_bancos, container, false);

        SelectorChasis6_1 = (ImageButton) mView.findViewById(R.id.SelectorChasis6_1);
        SelectorChasis6_2 = (ImageButton) mView.findViewById(R.id.SelectorChasis6_2);
        SelectorChasis6_3 = (ImageButton) mView.findViewById(R.id.SelectorChasis6_3);
        SelectorChasis6_4 = (ImageButton) mView.findViewById(R.id.SelectorChasis6_4);
        SelectorChasis6_5 = (ImageButton) mView.findViewById(R.id.SelectorChasis6_5);
        SelectorChasis6_6 = (ImageButton) mView.findViewById(R.id.SelectorChasis6_6);

        txtChasis6_1 = (TextView) mView.findViewById(R.id.txtTotal6_1);
        txtChasis6_2 = (TextView) mView.findViewById(R.id.txtTotal6_2);
        txtChasis6_3 = (TextView) mView.findViewById(R.id.txtTotal6_3);
        txtChasis6_4 = (TextView) mView.findViewById(R.id.txtTotal6_4);
        txtChasis6_5 = (TextView) mView.findViewById(R.id.txtTotal6_5);
        txtChasis6_6 = (TextView) mView.findViewById(R.id.txtTotal6_6);

        txtTotal6_1 = (TextView) mView.findViewById(R.id.txtChasis6_1);
        txtTotal6_2 = (TextView) mView.findViewById(R.id.txtChasis6_2);
        txtTotal6_3 = (TextView) mView.findViewById(R.id.txtChasis6_3);
        txtTotal6_4 = (TextView) mView.findViewById(R.id.txtChasis6_4);
        txtTotal6_5 = (TextView) mView.findViewById(R.id.txtChasis6_5);
        txtTotal6_6 = (TextView) mView.findViewById(R.id.txtChasis6_6);

        SelectorChasis6_1.setBackgroundColor(Color.YELLOW);
        SelectorChasis6_2.setBackgroundColor(Color.GRAY);
        SelectorChasis6_3.setBackgroundColor(Color.GRAY);
        SelectorChasis6_4.setBackgroundColor(Color.GRAY);
        SelectorChasis6_5.setBackgroundColor(Color.GRAY);
        SelectorChasis6_6.setBackgroundColor(Color.GRAY);
        BancoSeleccionado =1;
        mListener.lugarDeCarga(1);

        txtTotal6_1.setText(String.valueOf(CargaXBancos));
        txtTotal6_2.setText(String.valueOf(CargaXBancos));
        txtTotal6_3.setText(String.valueOf(CargaXBancos));
        txtTotal6_4.setText(String.valueOf(CargaXBancos));
        txtTotal6_5.setText(String.valueOf(CargaXBancos));
        txtTotal6_6.setText(String.valueOf(CargaXBancos));

        SelectorChasis6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis6_1.setBackgroundColor(Color.YELLOW);
                SelectorChasis6_2.setBackgroundColor(Color.GRAY);
                SelectorChasis6_3.setBackgroundColor(Color.GRAY);
                SelectorChasis6_4.setBackgroundColor(Color.GRAY);
                SelectorChasis6_5.setBackgroundColor(Color.GRAY);
                SelectorChasis6_6.setBackgroundColor(Color.GRAY);
                BancoSeleccionado =1;
                mListener.lugarDeCarga(1);
                if (!mListener.comprobarCero(txtChasis6_1)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis6_1));
                }
            }
        });
        SelectorChasis6_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis6_1.setBackgroundColor(Color.GRAY);
                SelectorChasis6_2.setBackgroundColor(Color.YELLOW);
                SelectorChasis6_3.setBackgroundColor(Color.GRAY);
                SelectorChasis6_4.setBackgroundColor(Color.GRAY);
                SelectorChasis6_5.setBackgroundColor(Color.GRAY);
                SelectorChasis6_6.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 2;
                mListener.lugarDeCarga(1);
                if (!mListener.comprobarCero(txtChasis6_2)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis6_2));
                }
            }
        });
        SelectorChasis6_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis6_1.setBackgroundColor(Color.GRAY);
                SelectorChasis6_2.setBackgroundColor(Color.GRAY);
                SelectorChasis6_3.setBackgroundColor(Color.YELLOW);
                SelectorChasis6_4.setBackgroundColor(Color.GRAY);
                SelectorChasis6_5.setBackgroundColor(Color.GRAY);
                SelectorChasis6_6.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 3;
                mListener.lugarDeCarga(1);
                if (!mListener.comprobarCero(txtChasis6_3)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis6_3));
                }
            }
        });
        SelectorChasis6_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis6_1.setBackgroundColor(Color.GRAY);
                SelectorChasis6_2.setBackgroundColor(Color.GRAY);
                SelectorChasis6_3.setBackgroundColor(Color.GRAY);
                SelectorChasis6_4.setBackgroundColor(Color.YELLOW);
                SelectorChasis6_5.setBackgroundColor(Color.GRAY);
                SelectorChasis6_6.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 4;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis6_4)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis6_4));
                }
            }
        });
        SelectorChasis6_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis6_1.setBackgroundColor(Color.GRAY);
                SelectorChasis6_2.setBackgroundColor(Color.GRAY);
                SelectorChasis6_3.setBackgroundColor(Color.GRAY);
                SelectorChasis6_4.setBackgroundColor(Color.GRAY);
                SelectorChasis6_5.setBackgroundColor(Color.YELLOW);
                SelectorChasis6_6.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 5;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis6_5)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis6_5));
                }
            }
        });
        SelectorChasis6_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis6_1.setBackgroundColor(Color.GRAY);
                SelectorChasis6_2.setBackgroundColor(Color.GRAY);
                SelectorChasis6_3.setBackgroundColor(Color.GRAY);
                SelectorChasis6_4.setBackgroundColor(Color.GRAY);
                SelectorChasis6_5.setBackgroundColor(Color.GRAY);
                SelectorChasis6_6.setBackgroundColor(Color.YELLOW);
                BancoSeleccionado = 6;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis6_6)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis6_6));
                }
            }
        });
        return mView;
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
