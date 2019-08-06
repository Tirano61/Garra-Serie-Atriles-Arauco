package com.example.dramirez.garrraspuertoserie;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dramirez.garrraspuertoserie.FragmentInterfaces.EnvioDatos;


public class Fragment_ocho_bancos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int BancoSeleccionado;
    private EnvioDatos mListener;
    TextView txtChasis8_1, txtChasis8_2, txtChasis8_3,txtChasis8_4,txtChasis8_5,txtChasis8_6,txtChasis8_7,txtChasis8_8,
            txtTotal8_1,txtTotal8_2,txtTotal8_3,txtTotal8_4,txtTotal8_5,txtTotal8_6,txtTotal8_7,txtTotal8_8;
    ImageButton SelectorChasis8_1,SelectorChasis8_2,SelectorChasis8_3,SelectorChasis8_4,
                SelectorChasis8_5,SelectorChasis8_6,SelectorChasis8_7,SelectorChasis8_8;
    int CargaXBancos = 0;

    public Fragment_ocho_bancos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_ocho_bancos.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_ocho_bancos newInstance(String param1, String param2) {
        Fragment_ocho_bancos fragment = new Fragment_ocho_bancos();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_ocho_bancos, container, false);
        SelectorChasis8_1 = (ImageButton) mView.findViewById(R.id.SelectorChasis8_1);
        SelectorChasis8_2 = (ImageButton) mView.findViewById(R.id.SelectorChasis8_2);
        SelectorChasis8_3 = (ImageButton) mView.findViewById(R.id.SelectorChasis8_3);
        SelectorChasis8_4 = (ImageButton) mView.findViewById(R.id.SelectorChasis8_4);
        SelectorChasis8_5 = (ImageButton) mView.findViewById(R.id.SelectorChasis8_5);
        SelectorChasis8_6 = (ImageButton) mView.findViewById(R.id.SelectorChasis8_6);
        SelectorChasis8_7 = (ImageButton) mView.findViewById(R.id.SelectorChasis8_7);
        SelectorChasis8_8 = (ImageButton) mView.findViewById(R.id.SelectorChasis8_8);
        txtChasis8_1 = (TextView) mView.findViewById(R.id.txtChasis8_1);
        txtChasis8_2 = (TextView) mView.findViewById(R.id.txtChasis8_2);
        txtChasis8_3 = (TextView) mView.findViewById(R.id.txtChasis8_3);
        txtChasis8_4 = (TextView) mView.findViewById(R.id.txtChasis8_4);
        txtChasis8_5 = (TextView) mView.findViewById(R.id.txtChasis8_5);
        txtChasis8_6 = (TextView) mView.findViewById(R.id.txtChasis8_6);
        txtChasis8_7 = (TextView) mView.findViewById(R.id.txtChasis8_7);
        txtChasis8_8 = (TextView) mView.findViewById(R.id.txtChasis8_8);

        txtTotal8_1 = (TextView) mView.findViewById(R.id.txtTotal8_1);
        txtTotal8_2 = (TextView) mView.findViewById(R.id.txtTotal8_2);
        txtTotal8_3 = (TextView) mView.findViewById(R.id.txtTotal8_3);
        txtTotal8_4 = (TextView) mView.findViewById(R.id.txtTotal8_4);
        txtTotal8_5 = (TextView) mView.findViewById(R.id.txtTotal8_5);
        txtTotal8_6 = (TextView) mView.findViewById(R.id.txtTotal8_6);
        txtTotal8_7 = (TextView) mView.findViewById(R.id.txtTotal8_7);
        txtTotal8_8 = (TextView) mView.findViewById(R.id.txtTotal8_8);

        SelectorChasis8_1.setBackgroundColor(Color.YELLOW);
        SelectorChasis8_2.setBackgroundColor(Color.GRAY);
        SelectorChasis8_3.setBackgroundColor(Color.GRAY);
        SelectorChasis8_4.setBackgroundColor(Color.GRAY);
        SelectorChasis8_5.setBackgroundColor(Color.GRAY);
        SelectorChasis8_6.setBackgroundColor(Color.GRAY);
        SelectorChasis8_7.setBackgroundColor(Color.GRAY);
        SelectorChasis8_8.setBackgroundColor(Color.GRAY);
        BancoSeleccionado = 1;
        mListener.lugarDeCarga(1);

        txtTotal8_1.setText(String.valueOf(CargaXBancos));
        txtTotal8_2.setText(String.valueOf(CargaXBancos));
        txtTotal8_3.setText(String.valueOf(CargaXBancos));
        txtTotal8_4.setText(String.valueOf(CargaXBancos));
        txtTotal8_5.setText(String.valueOf(CargaXBancos));
        txtTotal8_6.setText(String.valueOf(CargaXBancos));
        txtTotal8_7.setText(String.valueOf(CargaXBancos));
        txtTotal8_8.setText(String.valueOf(CargaXBancos));

        SelectorChasis8_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis8_1.setBackgroundColor(Color.YELLOW);
                SelectorChasis8_2.setBackgroundColor(Color.GRAY);
                SelectorChasis8_3.setBackgroundColor(Color.GRAY);
                SelectorChasis8_4.setBackgroundColor(Color.GRAY);
                SelectorChasis8_5.setBackgroundColor(Color.GRAY);
                SelectorChasis8_6.setBackgroundColor(Color.GRAY);
                SelectorChasis8_7.setBackgroundColor(Color.GRAY);
                SelectorChasis8_8.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 1;
                mListener.lugarDeCarga(1);
                if (!mListener.comprobarCero(txtChasis8_1)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis8_1));
                }
            }
        });
        SelectorChasis8_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis8_1.setBackgroundColor(Color.GRAY);
                SelectorChasis8_2.setBackgroundColor(Color.YELLOW);
                SelectorChasis8_3.setBackgroundColor(Color.GRAY);
                SelectorChasis8_4.setBackgroundColor(Color.GRAY);
                SelectorChasis8_5.setBackgroundColor(Color.GRAY);
                SelectorChasis8_6.setBackgroundColor(Color.GRAY);
                SelectorChasis8_7.setBackgroundColor(Color.GRAY);
                SelectorChasis8_8.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 2;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis8_2)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis8_2));
                }
            }
        });
        SelectorChasis8_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis8_1.setBackgroundColor(Color.GRAY);
                SelectorChasis8_2.setBackgroundColor(Color.GRAY);
                SelectorChasis8_3.setBackgroundColor(Color.YELLOW);
                SelectorChasis8_4.setBackgroundColor(Color.GRAY);
                SelectorChasis8_5.setBackgroundColor(Color.GRAY);
                SelectorChasis8_6.setBackgroundColor(Color.GRAY);
                SelectorChasis8_7.setBackgroundColor(Color.GRAY);
                SelectorChasis8_8.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 3;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis8_3)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis8_3));
                }
            }
        });
        SelectorChasis8_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis8_1.setBackgroundColor(Color.GRAY);
                SelectorChasis8_2.setBackgroundColor(Color.GRAY);
                SelectorChasis8_3.setBackgroundColor(Color.GRAY);
                SelectorChasis8_4.setBackgroundColor(Color.YELLOW);
                SelectorChasis8_5.setBackgroundColor(Color.GRAY);
                SelectorChasis8_6.setBackgroundColor(Color.GRAY);
                SelectorChasis8_7.setBackgroundColor(Color.GRAY);
                SelectorChasis8_8.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 4;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis8_4)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis8_4));
                }
            }
        });
        SelectorChasis8_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis8_1.setBackgroundColor(Color.GRAY);
                SelectorChasis8_2.setBackgroundColor(Color.GRAY);
                SelectorChasis8_3.setBackgroundColor(Color.GRAY);
                SelectorChasis8_4.setBackgroundColor(Color.GRAY);
                SelectorChasis8_5.setBackgroundColor(Color.YELLOW);
                SelectorChasis8_6.setBackgroundColor(Color.GRAY);
                SelectorChasis8_7.setBackgroundColor(Color.GRAY);
                SelectorChasis8_8.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 5;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis8_5)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis8_5));
                }
            }
        });
        SelectorChasis8_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis8_1.setBackgroundColor(Color.GRAY);
                SelectorChasis8_2.setBackgroundColor(Color.GRAY);
                SelectorChasis8_3.setBackgroundColor(Color.GRAY);
                SelectorChasis8_4.setBackgroundColor(Color.GRAY);
                SelectorChasis8_5.setBackgroundColor(Color.GRAY);
                SelectorChasis8_6.setBackgroundColor(Color.YELLOW);
                SelectorChasis8_7.setBackgroundColor(Color.GRAY);
                SelectorChasis8_8.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 6;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis8_6)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis8_6));
                }
            }
        });
        SelectorChasis8_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis8_1.setBackgroundColor(Color.GRAY);
                SelectorChasis8_2.setBackgroundColor(Color.GRAY);
                SelectorChasis8_3.setBackgroundColor(Color.GRAY);
                SelectorChasis8_4.setBackgroundColor(Color.GRAY);
                SelectorChasis8_5.setBackgroundColor(Color.GRAY);
                SelectorChasis8_6.setBackgroundColor(Color.GRAY);
                SelectorChasis8_7.setBackgroundColor(Color.YELLOW);
                SelectorChasis8_8.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 7;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis8_7)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis8_7));
                }
            }
        });
        SelectorChasis8_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis8_1.setBackgroundColor(Color.GRAY);
                SelectorChasis8_2.setBackgroundColor(Color.GRAY);
                SelectorChasis8_3.setBackgroundColor(Color.GRAY);
                SelectorChasis8_4.setBackgroundColor(Color.GRAY);
                SelectorChasis8_5.setBackgroundColor(Color.GRAY);
                SelectorChasis8_6.setBackgroundColor(Color.GRAY);
                SelectorChasis8_7.setBackgroundColor(Color.GRAY);
                SelectorChasis8_8.setBackgroundColor(Color.YELLOW);
                BancoSeleccionado = 8;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis8_8)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis8_8));
                }
            }
        });

        return mView;
    }

    public void recibirPeso(String peso){
        switch (BancoSeleccionado){
            case 1:
                txtChasis8_1.setText(peso);
                break;
            case 2:
                txtChasis8_2.setText(peso);
                break;
            case 3:
                txtChasis8_3.setText(peso);
                break;
            case 4:
                txtChasis8_4.setText(peso);
                break;
            case 5:
                txtChasis8_5.setText(peso);
                break;
            case 6:
                txtChasis8_6.setText(peso);
                break;
            case 7:
                txtChasis8_7.setText(peso);
                break;
            case 8:
                txtChasis8_8.setText(peso);
                break;
        }
    }
    public void TotalXBancos(int totalXbancos)
    {
        CargaXBancos = totalXbancos;
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
