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



public class Fragment_nueve_bancos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int BancoSeleccionado;
    private EnvioDatos mListener;

    TextView txtChasis9_1, txtChasis9_2, txtChasis9_3,txtChasis9_4,txtChasis9_5,txtChasis9_6,txtChasis9_7,txtChasis9_8,txtChasis9_9,
            txtTotal9_1,txtTotal9_2,txtTotal9_3,txtTotal9_4,txtTotal9_5,txtTotal9_6,txtTotal9_7,txtTotal9_8,txtTotal9_9;
    ImageButton SelectorChasis9_1,SelectorChasis9_2,SelectorChasis9_3,SelectorChasis9_4,
            SelectorChasis9_5,SelectorChasis9_6,SelectorChasis9_7,SelectorChasis9_8,SelectorChasis9_9;
    int CargaXBancos = 0;
    public Fragment_nueve_bancos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_nueve_bancos.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_nueve_bancos newInstance(String param1, String param2) {
        Fragment_nueve_bancos fragment = new Fragment_nueve_bancos();
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
        View mView = inflater.inflate(R.layout.fragment_nueve_bancos, container, false);

        SelectorChasis9_1 = (ImageButton) mView.findViewById(R.id.SelectorChasis9_1);
        SelectorChasis9_2 = (ImageButton) mView.findViewById(R.id.SelectorChasis9_2);
        SelectorChasis9_3 = (ImageButton) mView.findViewById(R.id.SelectorChasis9_3);
        SelectorChasis9_4 = (ImageButton) mView.findViewById(R.id.SelectorChasis9_4);
        SelectorChasis9_5 = (ImageButton) mView.findViewById(R.id.SelectorChasis9_5);
        SelectorChasis9_6 = (ImageButton) mView.findViewById(R.id.SelectorChasis9_6);
        SelectorChasis9_7 = (ImageButton) mView.findViewById(R.id.SelectorChasis9_7);
        SelectorChasis9_8 = (ImageButton) mView.findViewById(R.id.SelectorChasis9_8);
        SelectorChasis9_9 = (ImageButton) mView.findViewById(R.id.SelectorChasis9_9);

        txtChasis9_1 = (TextView) mView.findViewById(R.id.txtTotal9_1);
        txtChasis9_2 = (TextView) mView.findViewById(R.id.txtTotal9_2);
        txtChasis9_3 = (TextView) mView.findViewById(R.id.txtTotal9_3);
        txtChasis9_4 = (TextView) mView.findViewById(R.id.txtTotal9_4);
        txtChasis9_5 = (TextView) mView.findViewById(R.id.txtTotal9_5);
        txtChasis9_6 = (TextView) mView.findViewById(R.id.txtTotal9_6);
        txtChasis9_7 = (TextView) mView.findViewById(R.id.txtTotal9_7);
        txtChasis9_8 = (TextView) mView.findViewById(R.id.txtTotal9_8);
        txtChasis9_9 = (TextView) mView.findViewById(R.id.txtTotal9_9);

        txtTotal9_1 = (TextView) mView.findViewById(R.id.txtChasis9_1);
        txtTotal9_2 = (TextView) mView.findViewById(R.id.txtChasis9_2);
        txtTotal9_3 = (TextView) mView.findViewById(R.id.txtChasis9_3);
        txtTotal9_4 = (TextView) mView.findViewById(R.id.txtChasis9_4);
        txtTotal9_5 = (TextView) mView.findViewById(R.id.txtChasis9_5);
        txtTotal9_6 = (TextView) mView.findViewById(R.id.txtChasis9_6);
        txtTotal9_7 = (TextView) mView.findViewById(R.id.txtChasis9_7);
        txtTotal9_8 = (TextView) mView.findViewById(R.id.txtChasis9_8);
        txtTotal9_9 = (TextView) mView.findViewById(R.id.txtChasis9_9);

        txtTotal9_1.setText(String.valueOf(CargaXBancos));
        txtTotal9_2.setText(String.valueOf(CargaXBancos));
        txtTotal9_3.setText(String.valueOf(CargaXBancos));
        txtTotal9_4.setText(String.valueOf(CargaXBancos));
        txtTotal9_5.setText(String.valueOf(CargaXBancos));
        txtTotal9_6.setText(String.valueOf(CargaXBancos));
        txtTotal9_7.setText(String.valueOf(CargaXBancos));
        txtTotal9_8.setText(String.valueOf(CargaXBancos));
        txtTotal9_9.setText(String.valueOf(CargaXBancos));

        SelectorChasis9_1.setBackgroundColor(Color.YELLOW);
        SelectorChasis9_2.setBackgroundColor(Color.GRAY);
        SelectorChasis9_3.setBackgroundColor(Color.GRAY);
        SelectorChasis9_4.setBackgroundColor(Color.GRAY);
        SelectorChasis9_5.setBackgroundColor(Color.GRAY);
        SelectorChasis9_6.setBackgroundColor(Color.GRAY);
        SelectorChasis9_7.setBackgroundColor(Color.GRAY);
        SelectorChasis9_8.setBackgroundColor(Color.GRAY);
        SelectorChasis9_9.setBackgroundColor(Color.GRAY);
        BancoSeleccionado = 1;
        mListener.lugarDeCarga(1);

        SelectorChasis9_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis9_1.setBackgroundColor(Color.YELLOW);
                SelectorChasis9_2.setBackgroundColor(Color.GRAY);
                SelectorChasis9_3.setBackgroundColor(Color.GRAY);
                SelectorChasis9_4.setBackgroundColor(Color.GRAY);
                SelectorChasis9_5.setBackgroundColor(Color.GRAY);
                SelectorChasis9_6.setBackgroundColor(Color.GRAY);
                SelectorChasis9_7.setBackgroundColor(Color.GRAY);
                SelectorChasis9_8.setBackgroundColor(Color.GRAY);
                SelectorChasis9_9.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 1;
                mListener.lugarDeCarga(1);
                if (!mListener.comprobarCero(txtChasis9_1)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis9_1));
                }
            }
        });
        SelectorChasis9_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis9_1.setBackgroundColor(Color.GRAY);
                SelectorChasis9_2.setBackgroundColor(Color.YELLOW);
                SelectorChasis9_3.setBackgroundColor(Color.GRAY);
                SelectorChasis9_4.setBackgroundColor(Color.GRAY);
                SelectorChasis9_5.setBackgroundColor(Color.GRAY);
                SelectorChasis9_6.setBackgroundColor(Color.GRAY);
                SelectorChasis9_7.setBackgroundColor(Color.GRAY);
                SelectorChasis9_8.setBackgroundColor(Color.GRAY);
                SelectorChasis9_9.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 2;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis9_2)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis9_2));
                }
            }
        });
        SelectorChasis9_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis9_1.setBackgroundColor(Color.GRAY);
                SelectorChasis9_2.setBackgroundColor(Color.GRAY);
                SelectorChasis9_3.setBackgroundColor(Color.YELLOW);
                SelectorChasis9_4.setBackgroundColor(Color.GRAY);
                SelectorChasis9_5.setBackgroundColor(Color.GRAY);
                SelectorChasis9_6.setBackgroundColor(Color.GRAY);
                SelectorChasis9_7.setBackgroundColor(Color.GRAY);
                SelectorChasis9_8.setBackgroundColor(Color.GRAY);
                SelectorChasis9_9.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 3;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis9_3)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis9_3));
                }
            }
        });
        SelectorChasis9_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis9_1.setBackgroundColor(Color.GRAY);
                SelectorChasis9_2.setBackgroundColor(Color.GRAY);
                SelectorChasis9_3.setBackgroundColor(Color.GRAY);
                SelectorChasis9_4.setBackgroundColor(Color.YELLOW);
                SelectorChasis9_5.setBackgroundColor(Color.GRAY);
                SelectorChasis9_6.setBackgroundColor(Color.GRAY);
                SelectorChasis9_7.setBackgroundColor(Color.GRAY);
                SelectorChasis9_8.setBackgroundColor(Color.GRAY);
                SelectorChasis9_9.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 4;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis9_4)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis9_4));
                }
            }
        });
        SelectorChasis9_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis9_1.setBackgroundColor(Color.GRAY);
                SelectorChasis9_2.setBackgroundColor(Color.GRAY);
                SelectorChasis9_3.setBackgroundColor(Color.GRAY);
                SelectorChasis9_4.setBackgroundColor(Color.GRAY);
                SelectorChasis9_5.setBackgroundColor(Color.YELLOW);
                SelectorChasis9_6.setBackgroundColor(Color.GRAY);
                SelectorChasis9_7.setBackgroundColor(Color.GRAY);
                SelectorChasis9_8.setBackgroundColor(Color.GRAY);
                SelectorChasis9_9.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 5;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis9_5)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis9_5));
                }
            }
        });
        SelectorChasis9_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis9_1.setBackgroundColor(Color.GRAY);
                SelectorChasis9_2.setBackgroundColor(Color.GRAY);
                SelectorChasis9_3.setBackgroundColor(Color.GRAY);
                SelectorChasis9_4.setBackgroundColor(Color.GRAY);
                SelectorChasis9_5.setBackgroundColor(Color.GRAY);
                SelectorChasis9_6.setBackgroundColor(Color.YELLOW);
                SelectorChasis9_7.setBackgroundColor(Color.GRAY);
                SelectorChasis9_8.setBackgroundColor(Color.GRAY);
                SelectorChasis9_9.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 6;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis9_6)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis9_6));
                }
            }
        });
        SelectorChasis9_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis9_1.setBackgroundColor(Color.GRAY);
                SelectorChasis9_2.setBackgroundColor(Color.GRAY);
                SelectorChasis9_3.setBackgroundColor(Color.GRAY);
                SelectorChasis9_4.setBackgroundColor(Color.GRAY);
                SelectorChasis9_5.setBackgroundColor(Color.GRAY);
                SelectorChasis9_6.setBackgroundColor(Color.GRAY);
                SelectorChasis9_7.setBackgroundColor(Color.YELLOW);
                SelectorChasis9_8.setBackgroundColor(Color.GRAY);
                SelectorChasis9_9.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 7;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis9_7)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis9_7));
                }
            }
        });
        SelectorChasis9_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis9_1.setBackgroundColor(Color.GRAY);
                SelectorChasis9_2.setBackgroundColor(Color.GRAY);
                SelectorChasis9_3.setBackgroundColor(Color.GRAY);
                SelectorChasis9_4.setBackgroundColor(Color.GRAY);
                SelectorChasis9_5.setBackgroundColor(Color.GRAY);
                SelectorChasis9_6.setBackgroundColor(Color.GRAY);
                SelectorChasis9_7.setBackgroundColor(Color.GRAY);
                SelectorChasis9_8.setBackgroundColor(Color.YELLOW);
                SelectorChasis9_9.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 8;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis9_8)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis9_8));
                }
            }
        });
        SelectorChasis9_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis9_1.setBackgroundColor(Color.GRAY);
                SelectorChasis9_2.setBackgroundColor(Color.GRAY);
                SelectorChasis9_3.setBackgroundColor(Color.GRAY);
                SelectorChasis9_4.setBackgroundColor(Color.GRAY);
                SelectorChasis9_5.setBackgroundColor(Color.GRAY);
                SelectorChasis9_6.setBackgroundColor(Color.GRAY);
                SelectorChasis9_7.setBackgroundColor(Color.GRAY);
                SelectorChasis9_8.setBackgroundColor(Color.GRAY);
                SelectorChasis9_9.setBackgroundColor(Color.YELLOW);
                BancoSeleccionado = 9;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis9_9)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis9_9));
                }
            }
        });

        return mView;
    }

    public void recibirPeso(String peso){
        switch (BancoSeleccionado){
            case 1:
                txtChasis9_1.setText(peso);
                if (Integer.valueOf(txtChasis9_1.getText().toString()) < Integer.valueOf(txtTotal9_1.getText().toString())){
                    txtTotal9_1.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal9_1.getText().toString()) ){

                    txtTotal9_1.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis9_1.getText().toString()) > Integer.valueOf(txtTotal9_1.getText().toString())){

                    txtTotal9_1.setBackgroundColor(Color.RED);
                }
                break;
            case 2:
                txtChasis9_2.setText(peso);
                if (Integer.valueOf(txtChasis9_2.getText().toString()) < Integer.valueOf(txtTotal9_2.getText().toString())){
                    txtTotal9_2.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal9_2.getText().toString()) ){

                    txtTotal9_2.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis9_2.getText().toString()) > Integer.valueOf(txtTotal9_2.getText().toString())){

                    txtTotal9_2.setBackgroundColor(Color.RED);
                }
                break;
            case 3:
                txtChasis9_3.setText(peso);
                if (Integer.valueOf(txtChasis9_3.getText().toString()) < Integer.valueOf(txtTotal9_3.getText().toString())){
                    txtTotal9_3.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal9_3.getText().toString()) ){

                    txtTotal9_3.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis9_3.getText().toString()) > Integer.valueOf(txtTotal9_3.getText().toString())){

                    txtTotal9_3.setBackgroundColor(Color.RED);
                }
                break;
            case 4:
                txtChasis9_4.setText(peso);
                if (Integer.valueOf(txtChasis9_4.getText().toString()) < Integer.valueOf(txtTotal9_4.getText().toString())){
                    txtTotal9_4.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal9_4.getText().toString()) ){

                    txtTotal9_4.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis9_4.getText().toString()) > Integer.valueOf(txtTotal9_4.getText().toString())){

                    txtTotal9_4.setBackgroundColor(Color.RED);
                }
                break;
            case 5:
                txtChasis9_5.setText(peso);
                if (Integer.valueOf(txtChasis9_5.getText().toString()) < Integer.valueOf(txtTotal9_5.getText().toString())){
                    txtTotal9_5.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal9_5.getText().toString()) ){

                    txtTotal9_5.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis9_5.getText().toString()) > Integer.valueOf(txtTotal9_5.getText().toString())){

                    txtTotal9_5.setBackgroundColor(Color.RED);
                }
                break;
            case 6:
                txtChasis9_6.setText(peso);
                if (Integer.valueOf(txtChasis9_6.getText().toString()) < Integer.valueOf(txtTotal9_6.getText().toString())){
                    txtTotal9_6.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal9_6.getText().toString()) ){

                    txtTotal9_6.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis9_6.getText().toString()) > Integer.valueOf(txtTotal9_6.getText().toString())){

                    txtTotal9_6.setBackgroundColor(Color.RED);
                }
                break;
            case 7:
                txtChasis9_7.setText(peso);
                if (Integer.valueOf(txtChasis9_7.getText().toString()) < Integer.valueOf(txtTotal9_7.getText().toString())){
                    txtTotal9_7.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal9_7.getText().toString()) ){

                    txtTotal9_7.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis9_7.getText().toString()) > Integer.valueOf(txtTotal9_7.getText().toString())){

                    txtTotal9_7.setBackgroundColor(Color.RED);
                }
                break;
            case 8:
                txtChasis9_8.setText(peso);
                if (Integer.valueOf(txtChasis9_8.getText().toString()) < Integer.valueOf(txtTotal9_8.getText().toString())){
                    txtTotal9_8.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal9_8.getText().toString()) ){

                    txtTotal9_8.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis9_8.getText().toString()) > Integer.valueOf(txtTotal9_8.getText().toString())){

                    txtTotal9_8.setBackgroundColor(Color.RED);
                }
                break;
            case 9:
                txtChasis9_9.setText(peso);
                if (Integer.valueOf(txtChasis9_9.getText().toString()) < Integer.valueOf(txtTotal9_9.getText().toString())){
                    txtTotal9_9.setBackgroundColor(Color.BLUE);
                }else if(peso.equals(txtTotal9_9.getText().toString()) ){

                    txtTotal9_9.setBackgroundColor(Color.GREEN);
                }else if(Integer.valueOf(txtChasis9_9.getText().toString()) > Integer.valueOf(txtTotal9_9.getText().toString())){

                    txtTotal9_9.setBackgroundColor(Color.RED);
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
