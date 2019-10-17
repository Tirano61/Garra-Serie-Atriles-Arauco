package com.example.dramirez.garrraspuertoserie;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dramirez.garrraspuertoserie.FragmentInterfaces.EnvioDatos;



public class Fragment_cinco_bancos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int CargaXBancos = 0;
    private EnvioDatos mListener;

    ImageButton SelectorChasis5_1,SelectorChasis5_2,SelectorChasis5_3,SelectorChasis5_4,SelectorChasis5_5;
    TextView txtChasis5_1, txtChasis5_2, txtChasis5_3, txtChasis5_4, txtChasis5_5,
            txtTotal5_1,txtTotal5_2,txtTotal5_3,txtTotal5_4,txtTotal5_5;
    int BancoSeleccionado =0;
    public Fragment_cinco_bancos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_cinco_bancos.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_cinco_bancos newInstance(String param1, String param2) {
        Fragment_cinco_bancos fragment = new Fragment_cinco_bancos();
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
                txtChasis5_1.setText(peso);
                break;
            case 2:
                txtChasis5_2.setText(peso);
                break;
            case 3:
                txtChasis5_3.setText(peso);
                break;
            case 4:
                txtChasis5_4.setText(peso);
                break;
            case 5:
                txtChasis5_5.setText(peso);
                break;
        }
    }
    public void TotalXBancos(int totalXbancos)
    {
        CargaXBancos = totalXbancos;
    }
    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        txtChasis5_1.setText("0");
        txtChasis5_2.setText("0");
        txtChasis5_3.setText("0");
        txtChasis5_4.setText("0");
        txtChasis5_5.setText("0");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_cinco_bancos, container, false);

        SelectorChasis5_1 = (ImageButton) mView.findViewById(R.id.SelectorChasis5_1);
        SelectorChasis5_2 = (ImageButton) mView.findViewById(R.id.SelectorChasis5_2);
        SelectorChasis5_3 = (ImageButton) mView.findViewById(R.id.SelectorChasis5_3);
        SelectorChasis5_4 = (ImageButton) mView.findViewById(R.id.SelectorChasis5_4);
        SelectorChasis5_5 = (ImageButton) mView.findViewById(R.id.SelectorChasis5_5);

        txtChasis5_1 = (TextView) mView.findViewById(R.id.txtTotal5_1);
        txtChasis5_2 = (TextView) mView.findViewById(R.id.txtTotal5_2);
        txtChasis5_3 = (TextView) mView.findViewById(R.id.txtTotal5_3);
        txtChasis5_4 = (TextView) mView.findViewById(R.id.txtTotal5_4);
        txtChasis5_5 = (TextView) mView.findViewById(R.id.txtTotal5_5);
        txtTotal5_1 = (TextView) mView.findViewById(R.id.txtChasis5_1);
        txtTotal5_2 = (TextView) mView.findViewById(R.id.txtChasis5_2);
        txtTotal5_3 = (TextView) mView.findViewById(R.id.txtChasis5_3);
        txtTotal5_4 = (TextView) mView.findViewById(R.id.txtChasis5_4);
        txtTotal5_5 = (TextView) mView.findViewById(R.id.txtChasis5_5);

        SelectorChasis5_1.setBackgroundColor(Color.YELLOW);
        SelectorChasis5_2.setBackgroundColor(Color.GRAY);
        SelectorChasis5_3.setBackgroundColor(Color.GRAY);
        SelectorChasis5_4.setBackgroundColor(Color.GRAY);
        SelectorChasis5_5.setBackgroundColor(Color.GRAY);
        BancoSeleccionado = 1;
        mListener.lugarDeCarga(1);
        txtTotal5_1.setText(String.valueOf(CargaXBancos));
        txtTotal5_2.setText(String.valueOf(CargaXBancos));
        txtTotal5_3.setText(String.valueOf(CargaXBancos));
        txtTotal5_4.setText(String.valueOf(CargaXBancos));
        txtTotal5_5.setText(String.valueOf(CargaXBancos));
        SelectorChasis5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis5_1.setBackgroundColor(Color.YELLOW);
                SelectorChasis5_2.setBackgroundColor(Color.GRAY);
                SelectorChasis5_3.setBackgroundColor(Color.GRAY);
                SelectorChasis5_4.setBackgroundColor(Color.GRAY);
                SelectorChasis5_5.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 1;
                mListener.lugarDeCarga(1);
                if (!mListener.comprobarCero(txtChasis5_1)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis5_1));
                }
            }
        });
        SelectorChasis5_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis5_1.setBackgroundColor(Color.GRAY);
                SelectorChasis5_2.setBackgroundColor(Color.YELLOW);
                SelectorChasis5_3.setBackgroundColor(Color.GRAY);
                SelectorChasis5_4.setBackgroundColor(Color.GRAY);
                SelectorChasis5_5.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 2;
                mListener.lugarDeCarga(1);
                if (!mListener.comprobarCero(txtChasis5_2)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis5_2));
                }
            }
        });
        SelectorChasis5_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis5_1.setBackgroundColor(Color.GRAY);
                SelectorChasis5_2.setBackgroundColor(Color.GRAY);
                SelectorChasis5_3.setBackgroundColor(Color.YELLOW);
                SelectorChasis5_4.setBackgroundColor(Color.GRAY);
                SelectorChasis5_5.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 3;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis5_3)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis5_3));
                }
            }
        });
        SelectorChasis5_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis5_1.setBackgroundColor(Color.GRAY);
                SelectorChasis5_2.setBackgroundColor(Color.GRAY);
                SelectorChasis5_3.setBackgroundColor(Color.GRAY);
                SelectorChasis5_4.setBackgroundColor(Color.YELLOW);
                SelectorChasis5_5.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 4;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis5_4)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis5_4));
                }
            }
        });
        SelectorChasis5_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis5_1.setBackgroundColor(Color.GRAY);
                SelectorChasis5_2.setBackgroundColor(Color.GRAY);
                SelectorChasis5_3.setBackgroundColor(Color.GRAY);
                SelectorChasis5_4.setBackgroundColor(Color.GRAY);
                SelectorChasis5_5.setBackgroundColor(Color.YELLOW);
                BancoSeleccionado = 5;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis5_5)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis5_5));
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
