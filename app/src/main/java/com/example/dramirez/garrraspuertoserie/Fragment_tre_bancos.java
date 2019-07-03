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
 * {@link Fragment_tre_bancos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_tre_bancos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_tre_bancos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    TextView txtChasis3_1, txtChasis3_2, txtChasis3_3;
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

        SelectorChasis3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis3_1.setBackgroundColor(Color.RED);
                SelectorChasis3_2.setBackgroundColor(Color.BLUE);
                SelectorChasis3_3.setBackgroundColor(Color.BLUE);
                BancoSeleccionado = 1;
                mListener.onFragmentInteraction();
            }
        });
        SelectorChasis3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis3_1.setBackgroundColor(Color.BLUE);
                SelectorChasis3_2.setBackgroundColor(Color.RED);
                SelectorChasis3_3.setBackgroundColor(Color.BLUE);
                BancoSeleccionado = 2;
                mListener.onFragmentInteraction();
            }
        });
        SelectorChasis3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectorChasis3_1.setBackgroundColor(Color.BLUE);
                SelectorChasis3_2.setBackgroundColor(Color.BLUE);
                SelectorChasis3_3.setBackgroundColor(Color.RED);
                BancoSeleccionado = 3;
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
