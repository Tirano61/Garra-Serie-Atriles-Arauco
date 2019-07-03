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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_seis_bancos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_seis_bancos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_seis_bancos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    TextView txtChasis6_1, txtChasis6_2, txtChasis6_3, txtChasis6_4, txtChasis6_5, txtChasis6_6;
    ImageButton SelectorChasis6_1,SelectorChasis6_2,SelectorChasis6_3,SelectorChasis6_4,SelectorChasis6_5,SelectorChasis6_6;
    int BancoSeleccionado =0;
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
                break;
            case 2:
                txtChasis6_2.setText(peso);
                break;
            case 3:
                txtChasis6_3.setText(peso);
                break;
            case 4:
                txtChasis6_4.setText(peso);
                break;
            case 5:
                txtChasis6_5.setText(peso);
                break;
            case 6:
                txtChasis6_6.setText(peso);
                break;
        }
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
        txtChasis6_1 = (TextView) mView.findViewById(R.id.txtChasis6_1);
        txtChasis6_2 = (TextView) mView.findViewById(R.id.txtChasis6_2);
        txtChasis6_3 = (TextView) mView.findViewById(R.id.txtChasis6_3);
        txtChasis6_4 = (TextView) mView.findViewById(R.id.txtChasis6_4);
        txtChasis6_5 = (TextView) mView.findViewById(R.id.txtChasis6_5);
        txtChasis6_6 = (TextView) mView.findViewById(R.id.txtChasis6_6);

        SelectorChasis6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis6_1.setBackgroundColor(Color.RED);
                SelectorChasis6_2.setBackgroundColor(Color.BLUE);
                SelectorChasis6_3.setBackgroundColor(Color.BLUE);
                SelectorChasis6_4.setBackgroundColor(Color.BLUE);
                SelectorChasis6_5.setBackgroundColor(Color.BLUE);
                SelectorChasis6_6.setBackgroundColor(Color.BLUE);
                BancoSeleccionado =1;
                mListener.onFragmentInteraction();
            }
        });
        SelectorChasis6_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis6_1.setBackgroundColor(Color.BLUE);
                SelectorChasis6_2.setBackgroundColor(Color.RED);
                SelectorChasis6_3.setBackgroundColor(Color.BLUE);
                SelectorChasis6_4.setBackgroundColor(Color.BLUE);
                SelectorChasis6_5.setBackgroundColor(Color.BLUE);
                SelectorChasis6_6.setBackgroundColor(Color.BLUE);
                BancoSeleccionado = 2;
                mListener.onFragmentInteraction();
            }
        });
        SelectorChasis6_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis6_1.setBackgroundColor(Color.BLUE);
                SelectorChasis6_2.setBackgroundColor(Color.BLUE);
                SelectorChasis6_3.setBackgroundColor(Color.RED);
                SelectorChasis6_4.setBackgroundColor(Color.BLUE);
                SelectorChasis6_5.setBackgroundColor(Color.BLUE);
                SelectorChasis6_6.setBackgroundColor(Color.BLUE);
                BancoSeleccionado = 3;
                mListener.onFragmentInteraction();
            }
        });
        SelectorChasis6_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis6_1.setBackgroundColor(Color.BLUE);
                SelectorChasis6_2.setBackgroundColor(Color.BLUE);
                SelectorChasis6_3.setBackgroundColor(Color.BLUE);
                SelectorChasis6_4.setBackgroundColor(Color.RED);
                SelectorChasis6_5.setBackgroundColor(Color.BLUE);
                SelectorChasis6_6.setBackgroundColor(Color.BLUE);
                BancoSeleccionado = 4;
                mListener.onFragmentInteraction();
            }
        });
        SelectorChasis6_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis6_1.setBackgroundColor(Color.BLUE);
                SelectorChasis6_2.setBackgroundColor(Color.BLUE);
                SelectorChasis6_3.setBackgroundColor(Color.BLUE);
                SelectorChasis6_4.setBackgroundColor(Color.BLUE);
                SelectorChasis6_5.setBackgroundColor(Color.RED);
                SelectorChasis6_6.setBackgroundColor(Color.BLUE);
                BancoSeleccionado = 5;
                mListener.onFragmentInteraction();
            }
        });
        SelectorChasis6_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis6_1.setBackgroundColor(Color.BLUE);
                SelectorChasis6_2.setBackgroundColor(Color.BLUE);
                SelectorChasis6_3.setBackgroundColor(Color.BLUE);
                SelectorChasis6_4.setBackgroundColor(Color.BLUE);
                SelectorChasis6_5.setBackgroundColor(Color.BLUE);
                SelectorChasis6_6.setBackgroundColor(Color.RED);
                BancoSeleccionado = 6;
                mListener.onFragmentInteraction();
            }
        });

        return mView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }
}
