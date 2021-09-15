package com.example.bigint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.Stack;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import java.lang.reflect.Array;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private Stack<String> resultado = new Stack<String>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void adicionando(View view) {

        EditText fator1 = (EditText) findViewById(R.id.editTextNumber01);
        resultado.push(fator1.getText().toString());
        alterar();
    }

    public void retirando(View view) {
        resultado.pop();
        alterar();
    }

    public void adicao(View view) {

        char[] aux1,aux2;

        int produto[] = new int[30];
        String saida = "";
        String  line = "",secline = "";
        String pop2 = resultado.pop();
        String pop1 = resultado.pop();
        aux1 = pop1.toCharArray();
        aux2 = pop2.toCharArray();

        for(int i = pop1.length()-1; i >= 0; i--){
            secline += aux2[i];
        }

        for(int i = pop2.length()-1; i >= 0; i--){
            line += aux1[i];
        }

        aux2 = secline.toCharArray();
        aux1 = line.toCharArray();

        for(int i = 0; i < pop1.length(); i++){
            produto[i] =  produto[i] + Character.getNumericValue(aux2[i]);
        }

        for(int i = 0; i < pop2.length(); i++){
            produto[i] =  produto[i] + Character.getNumericValue(aux1[i]);
            produto[i + 1] =  produto[i + 1] + ( produto[i] / 10);
            produto[i] =  produto[i] - (10 * ( produto[i] / 10));
        }

        if(pop1.length() > pop2.length()){

            if( produto[pop1.length()] != 0){
                saida += Integer.toString( produto[pop1.length()]);
            }

            for(int i = pop1.length()-1; i >= 0; i--){
                saida += Integer.toString( produto[i]);
            }

        } else {

            if( produto[pop2.length()] != 0){
                saida += Integer.toString( produto[pop2.length()]);
            }

            for(int i = pop2.length()-1; i >= 0; i--){
                saida += Integer.toString( produto[i]);
            }
        }

        resultado.push(saida);
        alterar();
    }

    public void multiplicacao(View view) {

        char[] aux1, aux2;

        int produto[] = new int[30];
        String saida = "";
        String line = resultado.pop();
        String secline = resultado.pop();
        aux2 = line.toCharArray();
        aux1 = secline.toCharArray();

        for(int i = secline.length()-1; i >= 0; i--){
            for(int j = line.length()-1; j >= 0; j--){
                produto[j+i] = produto[j+i] + Character.getNumericValue(aux1[i]) * Character.getNumericValue(aux2[j]);

                if(j+i-1 >= 0) {
                    produto[j + i - 1] = produto[j + i - 1] + (produto[j + i] / 10);
                    produto[j + i] = produto[j + i] - (10 * (produto[j + i] / 10));
                }
            }
        }

        for (int i = 0; i <= line.length() + secline.length() - 2; i++){
            saida += Integer.toString(produto[i]);
        }

        resultado.push(saida);
        alterar();

    }

    public void alterar(){
        TextView response = (TextView) findViewById(R.id.TextResultado);
        response.setText(resultado.toString());
    }

}