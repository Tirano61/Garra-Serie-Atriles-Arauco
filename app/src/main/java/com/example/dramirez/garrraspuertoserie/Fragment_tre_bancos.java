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


public class Fragment_tre_bancos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int CargaXBancos = 0;
    private EnvioDatos mListener;
    TextView txtChasis3_1, txtChasis3_2, txtChasis3_3,txtTotal3_1,txtTotal3_2,txtTotal3_3;
    ImageButton SelectorChasis3_1,SelectorChasis3_2,SelectorChasis3_3;
    int BancoSeleccionado =0;


    public Fragment_tre_bancos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_tre_bancos.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_tre_bancos newInstance(String param1, String param2) {
        Fragment_tre_bancos fragment = new Fragment_tre_bancos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void recibirPeso(String peso){
        switch (BancoSeleccionado){
            case 1:
                txtChasis3_1.setText(peso);
                break;
            case 2:
                txtChasis3_2.setText(peso);
                break;
            case 3:
                txtChasis3_3.setText(peso);
                break;
        }
    }
    public void TotalXBancos(int totalXbancos)
    {
        CargaXBancos = totalXbancos;
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
        View mView = inflater.inflate(R.layout.fragment_tre_bancos, container, false);


        SelectorChasis3_1 = (ImageButton) mView.findViewById(R.id.SelectorChasis3_1);
        SelectorChasis3_2 = (ImageButton) mView.findViewById(R.id.SelectorChasis3_2);
        SelectorChasis3_3 = (ImageButton) mView.findViewById(R.id.SelectorChasis3_3);
        txtChasis3_1 = (TextView) mView.findViewById(R.id.txtChasis3_1);
        txtChasis3_2 = (TextView) mView.findViewById(R.id.txtChasis3_2);
        txtChasis3_3 = (TextView) mView.findViewById(R.id.txtChasis3_3);
        txtTotal3_1 = (TextView) mView.findViewById(R.id.txtTotal3_1);
        txtTotal3_2 = (TextView) mView.findViewById(R.id.txtTotal3_2);
        txtTotal3_3 = (TextView) mView.findViewById(R.id.txtTotal3_3);
        SelectorChasis3_1.setBackgroundColor(Color.YELLOW);
        SelectorChasis3_2.setBackgroundColor(Color.GRAY);
        SelectorChasis3_3.setBackgroundColor(Color.GRAY);
        BancoSeleccionado = 1;
        mListener.lugarDeCarga(1);
        txtTotal3_1.setText(String.valueOf(CargaXBancos));
        txtTotal3_2.setText(String.valueOf(CargaXBancos));
        txtTotal3_3.setText(String.valueOf(CargaXBancos));
        SelectorChasis3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis3_1.setBackgroundColor(Color.YELLOW);
                SelectorChasis3_2.setBackgroundColor(Color.GRAY);
                SelectorChasis3_3.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 1;
                mListener.lugarDeCarga(1);
                if (!mListener.comprobarCero(txtChasis3_1)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis3_1));
                }
            }
        });
        SelectorChasis3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis3_1.setBackgroundColor(Color.GRAY);
                SelectorChasis3_2.setBackgroundColor(Color.YELLOW);
                SelectorChasis3_3.setBackgroundColor(Color.GRAY);
                BancoSeleccionado = 2;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis3_2)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis3_2));
                }
            }
        });
        SelectorChasis3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis3_1.setBackgroundColor(Color.GRAY);
                SelectorChasis3_2.setBackgroundColor(Color.GRAY);
                SelectorChasis3_3.setBackgroundColor(Color.YELLOW);
                BancoSeleccionado = 3;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis3_3)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis3_3));
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
