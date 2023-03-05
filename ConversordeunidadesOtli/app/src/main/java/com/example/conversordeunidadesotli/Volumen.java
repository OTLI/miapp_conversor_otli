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

public class Volumen extends AppCompatActivity {

    private EditText editText_1, editText_2;
    private boolean editText_1_IsFocus=false, editText_2_IsFocus=false;
    private String itemSelected_1="",itemSelected_2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumen);
        editText_1 = (EditText) findViewById(R.id.et_1);
        //Agregar el evento de onTextChanged
        editText_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editText_1.length()>0){
                    if (Double.parseDouble(editText_1.getText().toString())>0){
                        if (editText_1_IsFocus){
                            convertirVolumen(1);
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
                    editText_1_IsFocus=true;
                    editText_1.setBackgroundColor(Color.WHITE);
                } else {
                    editText_1_IsFocus=false;
                    editText_1.setBackgroundColor(Color.rgb(147,140,177));
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
                if(editText_2.length()>0){
                    if (Double.parseDouble(editText_2.getText().toString())>0){
                        if (editText_2_IsFocus){
                            convertirVolumen(2);
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
                    editText_2_IsFocus=true;
                    editText_2.setBackgroundColor(Color.WHITE);
                } else {
                    editText_2_IsFocus=false;
                    editText_2.setBackgroundColor(Color.rgb(147,140,177));
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
                            convertirVolumen(1);
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
                            convertirVolumen(2);
                        }
                    }
                }
                System.out.println("Spinner 2: "+opcionSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opciones_Volumen, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter);
        spinner_2.setAdapter(adapter);

        editText_1.setText(formatearNumero(editText_1.getText().toString()));
        editText_2.setText(formatearNumero(editText_2.getText().toString()));
    }

    public void convertirVolumen(int et){

        double et_num1, et_num2;

        if (editText_1.length() == 0 || Double.isNaN(et_num1 = Double.parseDouble(editText_1.getText().toString())) || et_num1 == 0) {
            et_num1 = 0;
        } else if (EditTextTieneLetras(editText_1.getText().toString())) {
            editText_1.setText("");
            convertirVolumen(1);
            return;
        }

        if (editText_2.length() == 0 || Double.isNaN(et_num2 = Double.parseDouble(editText_2.getText().toString())) || et_num2 == 0) {
            et_num2 = 0;
        } else if (EditTextTieneLetras(editText_1.getText().toString())) {
            editText_2.setText("");
            convertirVolumen(2);
            return;
        }

        double res = 0;
        String itemSelected =  editText_1_IsFocus ? itemSelected_1 + itemSelected_2 : itemSelected_2 + itemSelected_1;

        res = (et == 1 && editText_1_IsFocus) || (et == 2 && !editText_2_IsFocus) ? et_num1 : et_num2;

        switch (itemSelected){
            case "Metro cubicoMetro cubico":
            case "Decimetro cubicoDecimetro cubico":
            case "Centimetro cubicoCentimetro cubico":
            case "Milimetro cubicoMilimetro cubico":
                if (et == 1) {
                    res = editText_1_IsFocus ? et_num1 : et_num2;
                } else if (et == 2) {
                    res = editText_2_IsFocus ? et_num2 : et_num1;
                }
                break;

            case "Metro cubicoDecimetro cubico":
            case "Decimetro cubicoCentimetro cubico":
            case "Centimetro cubicoMilimetro cubico":
                res *=1000;
                break;

            case "Metro cubicoCentimetro cubico":
            case "Decimetro cubicoMilimetro cubico":
            case "GramoMiligramo":
                res *= 1000000;
                break;

            case "Metro cubicoMilimetro cubico":
                res *= 1000000000;
                break;

            case "Decimetro cubicoMetro cubico":
            case "Centimetro cubicoDecimetro cubico":
                res /= 1000;
                break;

            case "Centimetro cubicoMetro cubico":
                res /= 1000000;
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