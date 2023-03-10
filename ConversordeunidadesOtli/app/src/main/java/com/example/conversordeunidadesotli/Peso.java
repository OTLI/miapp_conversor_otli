package com.example.conversordeunidadesotli;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Peso extends AppCompatActivity {

    private EditText editText_1, editText_2;
    private boolean editText_1_IsFocus=false, editText_2_IsFocus=false;
    private String itemSelected_1="",itemSelected_2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peso);
        editText_1 = (EditText) findViewById(R.id.et_1);
        //Agregar el evento de onTextChanged
        editText_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("onTextChanged et_1: "+s);
                if(editText_1.length()>0){
                    if (Double.parseDouble(editText_1.getText().toString())>0){
                        if (editText_1_IsFocus){
                            convertirPeso(1);
                        }
                    }
                }else {
                    if (editText_1_IsFocus){
                        editText_2.setText("");
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        //Agregar el evento de onFocus
        editText_1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // El EditText ha obtenido el foco
                    editText_1_IsFocus=true;
                    editText_1.setBackgroundColor(Color.WHITE);
                    //System.out.println("edit text 1 is focus");
                } else {
                    // El EditText ha perdido el foco
                    editText_1_IsFocus=false;
                    editText_1.setBackgroundColor(Color.rgb(147,140,177));
                    //System.out.println("edit text 1 is NOT focus");
                }
            }
        });
        //this.editText_1=editText_1;

        editText_2 = (EditText) findViewById(R.id.et_2);
        //Agregar el evento de onTextChanged
        editText_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("onTextChanged et_2: "+s);
                if(editText_2.length()>0){
                    if (Double.parseDouble(editText_2.getText().toString())>0){
                        if (editText_2_IsFocus){
                            convertirPeso(2);
                        }
                    }
                }else {
                    if (editText_2_IsFocus){
                        editText_1.setText("");
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        //Agregar el evento de onFocus
        editText_2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // El EditText ha obtenido el foco
                    editText_2_IsFocus=true;
                    editText_2.setBackgroundColor(Color.WHITE);
                    //System.out.println("edit text 2 is focus");
                } else {
                    // El EditText ha perdido el foco
                    editText_2_IsFocus=false;
                    editText_2.setBackgroundColor(Color.rgb(147,140,177));

                    //System.out.println("edit text 2 is NOT focus");
                }
            }
        });
        //this.editText_2=editText_2;

        Spinner spinner_1 = (Spinner) findViewById(R.id.spinner_1);
        spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String opcionSeleccionada = (String) parent.getItemAtPosition(position);
                itemSelected_1=opcionSeleccionada;
                if(editText_1.length()>0){
                    if (Double.parseDouble(editText_1.getText().toString())>0){
                        if (editText_1_IsFocus || editText_2_IsFocus){
                            convertirPeso(1);
                        }
                    }
                }
                System.out.println("Spinner 1: "+opcionSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        Spinner spinner_2 = (Spinner) findViewById(R.id.spinner_2);
        spinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String opcionSeleccionada = (String) parent.getItemAtPosition(position);
                itemSelected_2=opcionSeleccionada;
                if(editText_2.length()>0 && editText_1.length()>0){
                    if (Double.parseDouble(editText_2.getText().toString())>0 || Double.parseDouble(editText_1.getText().toString())>0){
                        if (editText_1_IsFocus || editText_2_IsFocus){
                            System.out.println("spinner 2");
                            convertirPeso(2);
                        }
                    }
                }
                System.out.println("Spinner 2: "+opcionSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opciones_Peso, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter);
        spinner_2.setAdapter(adapter);

        editText_1.setText(formatearNumero(editText_1.getText().toString()));
        editText_2.setText(formatearNumero(editText_2.getText().toString()));
    }

    public void convertirPeso(int et){

        double et_num1, et_num2;

        if (editText_1.length() == 0 || Double.isNaN(et_num1 = Double.parseDouble(editText_1.getText().toString())) || et_num1 == 0) {
            et_num1 = 0;
        } else if (EditTextTieneLetras(editText_1.getText().toString())) {
            editText_1.setText("");
            convertirPeso(1);
            return;
        }

        if (editText_2.length() == 0 || Double.isNaN(et_num2 = Double.parseDouble(editText_2.getText().toString())) || et_num2 == 0) {
            et_num2 = 0;
        } else if (EditTextTieneLetras(editText_1.getText().toString())) {
            editText_2.setText("");
            convertirPeso(2);
            return;
        }

        double res = 0;

        String itemSelected =  editText_1_IsFocus ? itemSelected_1 + itemSelected_2 : itemSelected_2 + itemSelected_1;

        res = (et == 1 && editText_1_IsFocus) || (et == 2 && !editText_2_IsFocus) ? et_num1 : et_num2;

        switch (itemSelected){
            case "ToneladaTonelada":
            case "KilogramoKilogramo":
            case "GramoGramo":
            case "MiligramoMiligramo":
            case "MicrogramoMicrogramo":
                if (et == 1) {
                    res = editText_1_IsFocus ? et_num1 : et_num2;
                } else if (et == 2) {
                    res = editText_2_IsFocus ? et_num2 : et_num1;
                }
                break;
            case "ToneladaKilogramo":
            case "KilogramoGramo":
            case "MiligramoMicrogramo":
                res *= 1000;
                break;

            case "KilometroCentimetro":
                res *= 100000;

                break;
            case "ToneladaGramo":
            case "KilogramoMiligramo":
            case "GramoMiligramo":
                res *= 1000000;

                break;
            case "ToneladaMiligramo":
            case "KilogramoMicrogramo":
            case "GramoMicrogramo":
                res *= 1000000000;
                break;

            case "ToneladaMicrogramo":
                res *= 1000000000000.0;
                break;

            case "KilogramoTonelada":
            case "GramoKilogramo":
            case "MiligramoGramo":
            case "MicrogramoMiligramo":
                res /= 1000;
                break;

            case "GramoTonelada_":
                res /= 100000;
                break;

            case "GramoTonelada":
            case "MiligramoKilogramo":
            case "MicrogramoGramo":
                res /= 1000000;
                break;

            case "MiligramoTonelada":
            case "MicrogramoKilogramo":
                res /= 1000000000;
                break;

            case "MicrogramoTonelada":
                res /=  1000000000000.0;
                break;

            default:
                System.out.println("a");
                break;
        }

        EditText etFocused = editText_1;

        if (et == 1){
            etFocused = editText_1_IsFocus ? editText_2 : editText_1;
        }

        if (et == 2){
            etFocused = editText_2_IsFocus ? editText_1 : editText_2;
        }

        etFocused.setText(formatearNumero(String.valueOf(res)));

    }

    public void borrarTextoDeEditText(View view){
        editText_1.setText("");
        editText_2.setText("");
    }

    public String formatearNumero(String num){
        return num.replaceFirst("\\.0$", "");
    }

    public boolean EditTextTieneLetras(String num){
        return num.matches(".*\\p{L}.*");
    }
}