package com.example.convertor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link darkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class darkFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view;

    private EditText number;
    private RadioButton RBinary;
    private RadioButton RDecimal;
    private RadioButton ROctal;
    private RadioButton RHexdecimal;

    private TextView binaryText;
    private TextView decimalText;
    private TextView octalText;
    private TextView hexdecimalText;

    /*
    * lable is used alternative for getCheckedRadioButtonId
    * */
    private String lable ="Binary";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public darkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment darkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static darkFragment newInstance(String param1, String param2) {
        darkFragment fragment = new darkFragment();
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
        view= inflater.inflate(R.layout.fragment_dark, container, false);
        number = view.findViewById(R.id.number_dark);

        RBinary = view.findViewById(R.id.RBinary);
        RDecimal = view.findViewById(R.id.RDecimal);
        ROctal =  view.findViewById(R.id.ROctal);
        RHexdecimal = view.findViewById(R.id.RHexdecimal);

        binaryText = view.findViewById(R.id.binaryText_dark);
        decimalText = view.findViewById(R.id.decimalText_dark);
        octalText = view.findViewById(R.id.octalText_dark);
        hexdecimalText = view.findViewById(R.id.hexdecimalText_dark);
        number.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(18) });
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty())
                {
                    String value=number.getText().toString();
                    switch (lable)
                    {
                        case "Binary":
                        {
                            if(value.length()>=62)
                                Toast.makeText(getActivity(),"You have reached the 62 digits maximum",Toast.LENGTH_LONG).show();
                            changeLable(systemConvert.convert("Binary",value));
                            break;
                        }
                        case "Decimal":
                        {
                            if(value.length()>=18)
                                Toast.makeText(getActivity(),"You have reached the 18 digits maximum",Toast.LENGTH_LONG).show();
                            changeLable(systemConvert.convert("Decimal",value));
                            break;
                        }
                        case "Octal":
                        {
                            if(value.length()>=20)
                                Toast.makeText(getActivity(),"You have reached the 20 digits maximum",Toast.LENGTH_LONG).show();
                            changeLable(systemConvert.convert("Octal",value));
                            break;
                        }
                        case "Hexdecimal":
                        {
                            if(value.length()>=15)
                                Toast.makeText(getActivity(),"You have reached the 15 digits maximum",Toast.LENGTH_LONG).show();
                            changeLable(systemConvert.convert("Hexdecimal",value));
                            break;
                        }
                    }
                }
            }
        });


        number.setKeyListener(DigitsKeyListener.getInstance("01"));

        RBinary.setOnClickListener(this);
        RDecimal.setOnClickListener(this);
        ROctal.setOnClickListener(this);
        RHexdecimal.setOnClickListener(this);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onClick(View view) {
        if (view == RBinary)
        {
            number.setKeyListener(DigitsKeyListener.getInstance("01"));
            number.setFilters(new InputFilter[] {new InputFilter.LengthFilter(62)});
            lable="Binary";
            if(number.getText().toString().isEmpty())
                return;
            if( !binaryText.getText().toString().isEmpty())
            {
                number.setText(binaryText.getText());
            }
            // do list function

            changeLable(systemConvert.convert("Binary",number.getText().toString()));

        }
        else if (view == RDecimal)
        {

            number.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
            number.setFilters(new InputFilter[] {new InputFilter.LengthFilter(18)});
            lable="Decimal";
            if(number.getText().toString().isEmpty())
                return;

            if( !decimalText.getText().toString().isEmpty())
            {
                number.setText(decimalText.getText());
            }

            // do list function

            changeLable(systemConvert.convert("Decimal",number.getText().toString()));

        }
        else if (view == ROctal)
        {
            number.setKeyListener(DigitsKeyListener.getInstance("01234567"));
            number.setFilters(new InputFilter[] {new InputFilter.LengthFilter(20)});
            lable="Octal";
            if(number.getText().toString().isEmpty())
                return;
            if(!octalText.getText().toString().isEmpty())
            {
                number.setText(octalText.getText());
            }

            // do list function
            changeLable(systemConvert.convert("Octal",number.getText().toString()));

        }
        else if (view == RHexdecimal)
        {
            number.setKeyListener(DigitsKeyListener.getInstance("0123456789abcedf"));
            number.setFilters(new InputFilter[] {new InputFilter.LengthFilter(15)});

            lable="Hexdecimal";
            if(number.getText().toString().isEmpty())
                return;
            if(!hexdecimalText.getText().toString().isEmpty())
            {
                number.setText(hexdecimalText.getText());
            }
            // do list function
            changeLable(systemConvert.convert("Hexdecimal",number.getText().toString()));
        }
    }

    private void changeLable(String [] temp)
    {
        binaryText.setText(temp[0]);
        decimalText.setText(temp[1]);
        octalText.setText(temp[2]);
        hexdecimalText.setText(temp[3]);
    }
}