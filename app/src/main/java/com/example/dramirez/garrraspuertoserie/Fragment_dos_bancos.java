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


public class Fragment_dos_bancos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int CargaXBancos = 0;
    private EnvioDatos mListener;
    TextView txtChasis2_1, txtChasis2_2,txtTotal2_1,txtTotal2_2;
    ImageButton SelectorChasis2_1,SelectorChasis2_2;
    int BancoSeleccionado =0;
    public Fragment_dos_bancos() {
        // Required empty public constructor
    }

    public void recibirPeso(String peso){
        switch (BancoSeleccionado){
            case 1:
                txtChasis2_1.setText(peso);
                break;
            case 2:
                txtChasis2_2.setText(peso);
                break;
        }
    }
    public void TotalXBancos(int totalXbancos)
    {
        CargaXBancos = totalXbancos;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_dos_bancos.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_dos_bancos newInstance(String param1, String param2) {
        Fragment_dos_bancos fragment = new Fragment_dos_bancos();
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
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_dos_bancos, container, false);

        SelectorChasis2_1 = (ImageButton) mView.findViewById(R.id.SelectorChasis2_1);
        SelectorChasis2_2 = (ImageButton) mView.findViewById(R.id.SelectorChasis2_2);

       /* Este es el original cambio el casteo en todos los bancos

        txtChasis2_1 = (TextView) mView.findViewById(R.id.txtChasis2_1);
        txtChasis2_2 = (TextView) mView.findViewById(R.id.txtChasis2_2);
        txtTotal2_1 = (TextView) mView.findViewById(R.id.txtTotal2_1);
        txtTotal2_2 = (TextView) mView.findViewById(R.id.txtTotal2_2);*/

        txtChasis2_1 = (TextView) mView.findViewById(R.id.txtTotal2_1);
        txtChasis2_2 = (TextView) mView.findViewById(R.id.txtTotal2_2);
        txtTotal2_1 = (TextView) mView.findViewById(R.id.txtChasis2_1);
        txtTotal2_2 = (TextView) mView.findViewById(R.id.txtChasis2_2);

        SelectorChasis2_1.setBackgroundColor(Color.YELLOW);
        SelectorChasis2_2.setBackgroundColor(Color.GRAY);
        mListener.lugarDeCarga(1);
        BancoSeleccionado = 1;
        txtTotal2_1.setText(String.valueOf(CargaXBancos));
        txtTotal2_2.setText(String.valueOf(CargaXBancos));
        SelectorChasis2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis2_1.setBackgroundColor(Color.YELLOW);
                SelectorChasis2_2.setBackgroundColor(Color.GRAY);
                mListener.lugarDeCarga(1);
                BancoSeleccionado = 1;
                if (!mListener.comprobarCero(txtChasis2_1)){
                    mListener.enviarCero(0);
                }else{

                    mListener.enviarCero(mListener.recabarPeso(txtChasis2_1));
                }
            }
        });
        SelectorChasis2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis2_1.setBackgroundColor(Color.GRAY);
                SelectorChasis2_2.setBackgroundColor(Color.YELLOW);
                BancoSeleccionado = 2;
                mListener.lugarDeCarga(2);
                if (!mListener.comprobarCero(txtChasis2_2)){
                    mListener.enviarCero(0);
                }else{
                    mListener.enviarCero(mListener.recabarPeso(txtChasis2_2));
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
