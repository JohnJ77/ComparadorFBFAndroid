package com.example.johnguisao.comparadorfbfmovil.Vista;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnguisao.comparadorfbfmovil.Modelo.FBF;
import com.example.johnguisao.comparadorfbfmovil.R;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnValidar, btnParentIzq, btnParentDer, btnCondicional, btnNegacion, btnBicondicional, btnDisyuncion, btnConjuncion;
    TextView tvFBF1PolacaInversa, tvFBF2PolacaInversa;
    EditText etFBF1, etFBF2;
    private Integer ic_Size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvFBF1PolacaInversa = (TextView)findViewById(R.id.tv_fbf1_polaca_inversa);
        tvFBF2PolacaInversa = (TextView)findViewById(R.id.tv_fbf2_polaca_inversa);
        etFBF1 = (EditText) findViewById(R.id.et_fbf1);
        etFBF2 = (EditText) findViewById(R.id.et_fbf2);
        btnValidar = (Button)findViewById(R.id.btn_comparar);
        btnParentIzq = (Button)findViewById(R.id.btn_parent_izq);
        btnParentDer = (Button)findViewById(R.id.btn_parent_der);
        btnCondicional = (Button)findViewById(R.id.btn_condicional);
        btnNegacion = (Button)findViewById(R.id.btn_negacion);
        btnBicondicional = (Button)findViewById(R.id.btn_bicondicional);
        btnDisyuncion = (Button)findViewById(R.id.btn_disyuncion);
        btnConjuncion = (Button)findViewById(R.id.btn_conjuncion);
        btnValidar.setOnClickListener(this);
        btnParentIzq.setOnClickListener(this);
        btnParentDer.setOnClickListener(this);
        btnCondicional.setOnClickListener(this);
        btnNegacion.setOnClickListener(this);
        btnBicondicional.setOnClickListener(this);
        btnDisyuncion.setOnClickListener(this);
        btnConjuncion.setOnClickListener(this);
        DisplayMetrics dm = new DisplayMetrics();
        MainActivity.this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        ic_Size = (int)(20 * (dm.densityDpi / 160f));
        etFBF1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    String expresion = etFBF1.getText().toString();
                    FBF f=new FBF(expresion);
                    etFBF1.getBackground().setColorFilter(Color.rgb(200,230,201), PorterDuff.Mode.SRC_IN);
                }catch(Exception e){
                    etFBF1.getBackground().setColorFilter(Color.rgb(255,102,102), PorterDuff.Mode.SRC_IN);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    String expresion = etFBF1.getText().toString();
                    FBF f=new FBF(expresion);
                    Bitmap resourceImg = BitmapFactory.decodeResource(getResources(), R.drawable.ic_checkmark);
                    Bitmap resizedImg = Bitmap.createScaledBitmap(resourceImg, ic_Size, ic_Size, true);
                    Drawable drawableImg = new BitmapDrawable(getResources(), resizedImg);
                    etFBF1.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableImg, null);
                    btnValidar.setEnabled(true);
                }catch(Exception e){
                    Bitmap resourceImg = BitmapFactory.decodeResource(getResources(), R.drawable.ic_error);
                    Bitmap resizedImg = Bitmap.createScaledBitmap(resourceImg, ic_Size, ic_Size, true);
                    Drawable drawableImg = new BitmapDrawable(getResources(), resizedImg);
                    etFBF1.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableImg, null);
                    btnValidar.setEnabled(false);
                }
            }
        });

        etFBF2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    String expresion = etFBF2.getText().toString();
                    FBF f=new FBF(expresion);
                    etFBF2.getBackground().setColorFilter(Color.rgb(200,230,201), PorterDuff.Mode.SRC_IN);
                }catch(Exception e){
                    etFBF2.getBackground().setColorFilter(Color.rgb(255,102,102), PorterDuff.Mode.SRC_IN);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    String expresion = etFBF2.getText().toString();
                    FBF f=new FBF(expresion);
                    Bitmap resourceImg = BitmapFactory.decodeResource(getResources(), R.drawable.ic_checkmark);
                    Bitmap resizedImg = Bitmap.createScaledBitmap(resourceImg, ic_Size, ic_Size, true);
                    Drawable drawableImg = new BitmapDrawable(getResources(), resizedImg);
                    etFBF2.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableImg, null);
                    btnValidar.setEnabled(true);
                }catch(Exception e){
                    Bitmap resourceImg = BitmapFactory.decodeResource(getResources(), R.drawable.ic_error);
                    Bitmap resizedImg = Bitmap.createScaledBitmap(resourceImg, ic_Size, ic_Size, true);
                    Drawable drawableImg = new BitmapDrawable(getResources(), resizedImg);
                    etFBF2.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableImg, null);
                    btnValidar.setEnabled(false);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_comparar:
                String strFBF1 = etFBF1.getText().toString();
                String strFBF2 = etFBF2.getText().toString();
                String strFBF1PolacaInversa = convertirANotacionPolacaInversa(strFBF1);
                String strFBF2PolacaInversa = convertirANotacionPolacaInversa(strFBF2);
                tvFBF1PolacaInversa.setText(strFBF1PolacaInversa);
                tvFBF2PolacaInversa.setText(strFBF2PolacaInversa);
                if(strFBF1PolacaInversa.equals(strFBF2PolacaInversa)){
                    Toast toast = Toast.makeText(getApplicationContext(),"Las fbfs son equivalentes",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Toast toast =Toast.makeText(getApplicationContext(),"Las fbfs no son equivalentes",Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.btn_parent_izq:
                if(etFBF1.isFocused()){
                    etFBF1.getText().insert(etFBF1.getSelectionStart(), "(");
                }
                else if(etFBF2.isFocused()) {
                    etFBF2.getText().insert(etFBF2.getSelectionStart(), "(");
                }
                break;
            case R.id.btn_parent_der:
                if(etFBF1.isFocused()){
                    etFBF1.getText().insert(etFBF1.getSelectionStart(), ")");
                }
                else if(etFBF2.isFocused()) {
                    etFBF2.getText().insert(etFBF2.getSelectionStart(), ")");
                }
                break;
            case R.id.btn_condicional:
                if(etFBF1.isFocused()){
                    etFBF1.getText().insert(etFBF1.getSelectionStart(), "→");
                }
                else if(etFBF2.isFocused()) {
                    etFBF2.getText().insert(etFBF2.getSelectionStart(), "→");
                }
                break;
            case R.id.btn_negacion:
                if(etFBF1.isFocused()){
                    etFBF1.getText().insert(etFBF1.getSelectionStart(), "¬");
                }
                else if(etFBF2.isFocused()) {
                    etFBF2.getText().insert(etFBF2.getSelectionStart(), "¬");
                }
                break;
            case R.id.btn_bicondicional:
                if(etFBF1.isFocused()){
                    etFBF1.getText().insert(etFBF1.getSelectionStart(), "⇔");
                }
                else if(etFBF2.isFocused()) {
                    etFBF2.getText().insert(etFBF2.getSelectionStart(), "⇔");
                }
                break;
            case R.id.btn_disyuncion:
                if(etFBF1.isFocused()){
                    etFBF1.getText().insert(etFBF1.getSelectionStart(), "∨");
                }
                else if(etFBF2.isFocused()) {
                    etFBF2.getText().insert(etFBF2.getSelectionStart(), "∨");
                }
                break;
            case R.id.btn_conjuncion:
                if(etFBF1.isFocused()){
                    etFBF1.getText().insert(etFBF1.getSelectionStart(), "∧");
                }
                else if(etFBF2.isFocused()) {
                    etFBF2.getText().insert(etFBF2.getSelectionStart(), "∧");
                }
                break;
            default:
                break;
        }
    }

    public String convertirANotacionPolacaInversa(String expresion) {
        // TODO code application logic here
        String expresionResultante = "";
        Stack<String> pilaOperadores = new Stack<String>();
        for (int i = 0; i < expresion.length(); i++) {
            char cExpresion = expresion.charAt(i);
            boolean agregado = false;
            switch (cExpresion) {
                case '(':
                    pilaOperadores.push(String.valueOf(cExpresion));
                    break;
                case '¬'://Operador Negación.
                    pilaOperadores.push(String.valueOf(cExpresion));
                    break;
                case '∧'://Operador And (Conjunción)
                    while(!agregado){
                        if(peticionAgregarOperadorPila(pilaOperadores,cExpresion)){
                            pilaOperadores.push(String.valueOf(cExpresion));
                            agregado = true;
                        }
                        else {
                            expresionResultante += pilaOperadores.pop();
                        }
                    }
                    break;
                case '∨'://Operador Or(Disyunción)
                    while(!agregado){
                        if(peticionAgregarOperadorPila(pilaOperadores,cExpresion)){
                            pilaOperadores.push(String.valueOf(cExpresion));
                            agregado = true;
                        }
                        else {
                            expresionResultante += pilaOperadores.pop();
                        }
                    }
                    break;
                case '→'://Operador condicional
                    while(!agregado){
                        if(peticionAgregarOperadorPila(pilaOperadores,cExpresion)){
                            pilaOperadores.push(String.valueOf(cExpresion));
                            agregado = true;
                        }
                        else {
                            expresionResultante += pilaOperadores.pop();
                        }
                    }
                    break;
                case '⇔'://Operador bicondicional
                    while(!agregado){
                        if(peticionAgregarOperadorPila(pilaOperadores,cExpresion)){
                            pilaOperadores.push(String.valueOf(cExpresion));
                            agregado = true;
                        }
                        else {
                            expresionResultante += pilaOperadores.pop();
                        }
                    }
                    break;
                case ')':
                    boolean cierreParentesis = false;
                    while(!cierreParentesis){
                        char operadorAIngresar = pilaOperadores.pop().charAt(0);
                        if(operadorAIngresar == '('){
                            cierreParentesis = true;
                        }
                        else {
                            expresionResultante += operadorAIngresar;
                        }
                    }
                    break;
                default://Cualquier letra representando una proposición.
                    expresionResultante += cExpresion;
                    break;
            }
        }
        while(!pilaOperadores.empty()) {
            expresionResultante += pilaOperadores.pop();
        }
        return (expresionResultante);

    }

    public boolean peticionAgregarOperadorPila(Stack<String> pilaOperadores, char operadorAIngresar) {
        boolean permiso = false;
        if (pilaOperadores.empty()) {
            permiso = true;
        } else {
            char peekPila = pilaOperadores.peek().charAt(0);
            if (encontrarPrioridad(operadorAIngresar) > encontrarPrioridad(peekPila)) {
                permiso = true;
            }
        }
        return permiso;
    }

    public int encontrarPrioridad(char operadorAux) {
        int prioridadOperadorAux = 0;
        switch (operadorAux) {
            case '¬'://Operador Negación.
                prioridadOperadorAux = 4;
                break;
            case '∧'://Operador And
                prioridadOperadorAux = 3;
                break;
            case '∨'://Operador Or
                prioridadOperadorAux = 3;
                break;
            case '→'://Operador condicional
                prioridadOperadorAux = 2;
                break;
            case '⇔'://Operador bicondicional
                prioridadOperadorAux = 1;
                break;
            default:
                prioridadOperadorAux = 0;
                break;
        }
        return prioridadOperadorAux;
    }
}
