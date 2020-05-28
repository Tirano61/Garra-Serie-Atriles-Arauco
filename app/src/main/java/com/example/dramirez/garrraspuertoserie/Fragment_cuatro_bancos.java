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


public class Fragment_cuatro_bancos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int CargaXBancos = 0;
    private EnvioDatos mListener;
    TextView txtChasis4_1, txtChasis4_2, txtChasis4_3, txtChasis4_4,txtTotal4_1,txtTotal4_2,txtTotal4_3,txtTotal4_4;
    ImageButton SelectorChasis4_1,SelectorChasis4_2,SelectorChasis4_3,SelectorChasis4_4;
    int BancoSeleccionado = 0;
    public Fragment_cuatro_bancos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_cuatro_bancos.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_cuatro_bancos newInstance(String param1, String param2) {
        Fragment_cuatro_bancos fragment = new Fragment_cuatro_bancos();
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
                txtChasis4_1.setText(peso);
                if (Integer.valueOf(txtChasis4_1.getText().toString()) < Integer.valueOf(txtTotal4_1.getText().toString())){
                    txtTotal4_1.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal4_1.getText().toString()) ){
                    txtTotal4_1.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis4_1.getText().toString()) > Integer.valueOf(txtTotal4_1.getText().toString())){
                    txtTotal4_1.setBackgroundColor(Color.RED);
                }
                break;
            case 2:
                txtChasis4_2.setText(peso);
                if (Integer.valueOf(txtChasis4_2.getText().toString()) < Integer.valueOf(txtTotal4_2.getText().toString())){
                    txtTotal4_2.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal4_2.getText().toString()) ){
                    txtTotal4_2.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis4_2.getText().toString()) > Integer.valueOf(txtTotal4_2.getText().toString())){
                    txtTotal4_2.setBackgroundColor(Color.RED);
                }
                break;
            case 3:
                txtChasis4_3.setText(peso);
                if (Integer.valueOf(txtChasis4_3.getText().toString()) < Integer.valueOf(txtTotal4_3.getText().toString())){
                    txtTotal4_3.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal4_3.getText().toString()) ){
                    txtTotal4_3.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis4_3.getText().toString()) > Integer.valueOf(txtTotal4_3.getText().toString())){
                    txtTotal4_3.setBackgroundColor(Color.RED);
                }
                break;
            case 4:
                txtChasis4_4.setText(peso);
                if (Integer.valueOf(txtChasis4_4.getText().toString()) < Integer.valueOf(txtTotal4_4.getText().toString())){
                    txtTotal4_4.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal4_4.getText().toString()) ){
                    txtTotal4_4.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis4_4.getText().toString()) > Integer.valueOf(txtTotal4_4.getText().toString())){
                    txtTotal4_4.setBackgroundColor(Color.RED);
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
        View mView = inflater.inflate(R.layout.fragment_cuatro_bancos, container, false);
        SelectorChasis4_1 = (ImageButton) mView.findViewById(R.id.SelectorChasis4_1);
        SelectorChasis4_2 = (ImageButton) mView.findViewById(R.id.SelectorChasis4_2);
        SelectorChasis4_3 = (ImageButton) mView.findViewById(R.id.SelectorChasis4_3);
        SelectorChasis4_4 = (ImageButton) mView.findViewById(R.id.SelectorChasis4_4);

        txtChasis4_1 = (TextView) mView.findViewById(R.id.txtTotal4_1);
        txtChasis4_2 = (TextView) mView.findViewById(R.id.txtTotal4_2);
        txtChasis4_3 = (TextView) mView.findViewById(R.id.txtTotal4_3);
        txtChasis4_4 = (TextView) mView.findViewById(R.id.txtTotal4_4);
        txtTotal4_1 = (TextView) mView.findViewById(R.id.txtChasis4_1);
        txtTotal4_2 = (TextView) mView.findViewById(R.id.txtChasis4_2);
        txtTotal4_3 = (TextView) mView.findViewById(R.id.txtChasis4_3);
        txtTotal4_4 = (TextView) mView.findViewById(R.id.txtChasis4_4);

        SelectorChasis4_1.setBackgroundColor(Color.YELLOW);
        SelectorChasis4_2.setBackgroundColor(Color.GRAY);
        SelectorChasis4_3.setBackgroundColor(Color.GRAY);
        SelectorChasis4_4.setBackgroundColor(Color.GRAY);
        BancoSeleccionado = 1;
        mListener.lugarDeCarga(1);
        txtTotal4_1.setText(String.valueOf(CargaXBancos));
        txtTotal4_2.setText(String.valueOf(CargaXBancos));
        txtTotal4_3.setText(String.valueOf(CargaXBancos));
        txtTotal4_4.setText(String.valueOf(CargaXBancos));
        SelectorChasis4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis4_1.setBackgroundColor(Color.YELLOW);
                SelectorChasis4_2.setBackgroundColor(Color.GRAY);
                SelectorChasis4_3.setBackgroundColor(Color.GRAY);
                SelectorChasis4_4.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 1;
                mListener.lugarDeCarga(1);
                if (!mListener.comprobarCero(txtChasis4_1)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis4_1));
                }
            }
        });
        SelectorChasis4_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis4_1.setBackgroundColor(Color.GRAY);
                SelectorChasis4_2.setBackgroundColor(Color.YELLOW);
                SelectorChasis4_3.setBackgroundColor(Color.GRAY);
                SelectorChasis4_4.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 2;
                mListener.lugarDeCarga(1);
                if (!mListener.comprobarCero(txtChasis4_2)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis4_2));
                }
            }
        });
        SelectorChasis4_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis4_1.setBackgroundColor(Color.GRAY);
                SelectorChasis4_2.setBackgroundColor(Color.GRAY);
                SelectorChasis4_3.setBackgroundColor(Color.YELLOW);
                SelectorChasis4_4.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 3;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis4_3)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis4_3));
                }
            }
        });
        SelectorChasis4_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis4_1.setBackgroundColor(Color.GRAY);
                SelectorChasis4_2.setBackgroundColor(Color.GRAY);
                SelectorChasis4_3.setBackgroundColor(Color.GRAY);
                SelectorChasis4_4.setBackgroundColor(Color.YELLOW);
                BancoSeleccionado = 4;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis4_4)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis4_4));
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
