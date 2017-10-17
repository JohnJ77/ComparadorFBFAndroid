package com.example.johnguisao.comparadorfbfmovil;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnValidar = (Button)findViewById(R.id.btn_comparar);
        final TextView tvFBF1PolacaInversa = (TextView)findViewById(R.id.tv_fbf1_polaca_inversa);
        final TextView tvFBF2PolacaInversa = (TextView)findViewById(R.id.tv_fbf2_polaca_inversa);
        final EditText etFBF1 = (EditText) findViewById(R.id.et_fbf1);
        final EditText etFBF2 = (EditText) findViewById(R.id.et_fbf2);

        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

    }

    public String convertirANotacionPolacaInversa(String expresion) {
        // TODO code application logic here
        //String expresion = "(r$¬(s#(¬(t))))?q";
        String expresionResultante = "";
        Stack<String> pilaOperadores = new Stack<String>();
        for (int i = 0; i < expresion.length(); i++) {
            char cExpresion = expresion.charAt(i);
            boolean agregado = false;
            switch (cExpresion) {
                case '(':
                    pilaOperadores.add(String.valueOf(cExpresion));
                    break;
                case '~'://Operador Negación.
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
                case '?'://Operador And
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
                case '$'://Operador Or
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
                case '%'://Operador condicional
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
                case '#'://Operador bicondicional
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
            case '~'://Operador Negación.
                prioridadOperadorAux = 4;
                break;
            case '?'://Operador And
                prioridadOperadorAux = 3;
                break;
            case '$'://Operador Or
                prioridadOperadorAux = 3;
                break;
            case '%'://Operador condicional
                prioridadOperadorAux = 2;
                break;
            case '#'://Operador bicondicional
                prioridadOperadorAux = 1;
                break;
            default:
                prioridadOperadorAux = 0;
                break;
        }
        return prioridadOperadorAux;
    }
}
