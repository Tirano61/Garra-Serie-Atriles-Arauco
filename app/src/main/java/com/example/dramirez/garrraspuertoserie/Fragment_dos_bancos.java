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
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_dos_bancos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_dos_bancos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_dos_bancos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private enviarBancoSeleccionado enviarBancoSeleccionado;
    private OnFragmentInteractionListener mListener;
    TextView txtChasis2_1, txtChasis2_2;
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

        txtChasis2_1 = (TextView) mView.findViewById(R.id.txtChasis2_1);
        txtChasis2_2 = (TextView) mView.findViewById(R.id.txtChasis2_2);


        SelectorChasis2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis2_1.setBackgroundColor(Color.RED);
                SelectorChasis2_2.setBackgroundColor(Color.BLUE);

                BancoSeleccionado = 1;
                mListener.onFragmentInteraction();
            }
        });
        SelectorChasis2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis2_1.setBackgroundColor(Color.BLUE);
                SelectorChasis2_2.setBackgroundColor(Color.RED);

                mListener.onFragmentInteraction();
                BancoSeleccionado = 2;

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
