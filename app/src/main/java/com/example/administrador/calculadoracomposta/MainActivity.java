package com.example.administrador.calculadoracomposta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    // ## apagar os TOASTS do ENTER
    // REPLACE ALL
    // ARRUMAR BUGS DA VIRGULA


    // Declarando Variaveis
    Button bt_0, bt_1, bt_2, bt_3, bt_4, bt_5, bt_6, bt_7, bt_8, bt_9;
    Button bt_add, bt_subt, bt_division, bt_multi, bt_comma, bt_enter, bt_clear;
    TextView tv_Tela;
    ImageButton bt_back;

    String op = "";
    boolean num1conf, resetTela, num1comma, num2comma = false; // resetTela é para apagar a tela quando for digitado um numero após acabar uma operação
    Double num1, num2, resultado;
    String num2str = "";
    String x = ""; //variavel que substitui o numero nos eventos de clique


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Preenchendo variaveis
        bt_0 = (Button) findViewById(R.id.bt_0);
        bt_1 = (Button) findViewById(R.id.bt_1);
        bt_2 = (Button) findViewById(R.id.bt_2);
        bt_3 = (Button) findViewById(R.id.bt_3);
        bt_4 = (Button) findViewById(R.id.bt_4);
        bt_5 = (Button) findViewById(R.id.bt_5);
        bt_6 = (Button) findViewById(R.id.bt_6);
        bt_7 = (Button) findViewById(R.id.bt_7);
        bt_8 = (Button) findViewById(R.id.bt_8);
        bt_9 = (Button) findViewById(R.id.bt_9);

        bt_0.setOnClickListener(escutabotao);
        bt_1.setOnClickListener(escutabotao);
        bt_2.setOnClickListener(escutabotao);
        bt_3.setOnClickListener(escutabotao);
        bt_4.setOnClickListener(escutabotao);
        bt_5.setOnClickListener(escutabotao);
        bt_6.setOnClickListener(escutabotao);
        bt_7.setOnClickListener(escutabotao);
        bt_8.setOnClickListener(escutabotao);
        bt_9.setOnClickListener(escutabotao);

        bt_add = (Button) findViewById(R.id.bt_add);
        bt_subt = (Button) findViewById(R.id.bt_subt);
        bt_division = (Button) findViewById(R.id.bt_division);
        bt_multi = (Button) findViewById(R.id.bt_multiply);
        bt_comma = (Button) findViewById(R.id.bt_comma);
        bt_enter = (Button) findViewById(R.id.bt_enter);
        bt_back = (ImageButton) findViewById(R.id.bt_back);
        bt_clear = (Button) findViewById(R.id.bt_clear);

        bt_add.setOnClickListener(escutabotao);
        bt_subt.setOnClickListener(escutabotao);
        bt_division.setOnClickListener(escutabotao);
        bt_multi.setOnClickListener(escutabotao);

        tv_Tela = (TextView) findViewById(R.id.tv_Tela);

        tv_Tela.setText("0");

        //
        //
        //

        bt_comma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                x = ".";
                //if (!(tv_Tela.getText() == "0")) {
                    //verifica se há operação e se ja existe uma virgula
                    if ( (op.equals("")) && (!num1comma) ) {
                        tv_Tela.setText(tv_Tela.getText().toString().concat(x));
                        num1comma = true;
                    } else {
                        //verifica se o numero 2 esta vazio ( com 0 )
                        if (!(op.equals("")) && (!num2comma) && (!num2str.equals(""))) {
                            num2str = num2str + x;
                            tv_Tela.setText(tv_Tela.getText().toString().concat(x));
                            num2comma = true;
                        }
                    }
                //}
            }
        });


        //
        // OnClick Especiais
        //


        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_Tela.length() <= 1) {
                    tv_Tela.setText("0");
                } else {
                    // Verifica se a ultima char é uma operação, para zerar a variavel.
                    String lastE = String.valueOf(String.valueOf(tv_Tela.getText()).charAt(tv_Tela.length()-1));
                    if (lastE.equals(op)){
                        num1conf = false;
                        op = "";
                    }
                    if ( (lastE.equals(".")) && (op.equals(""))){
                        num1comma = false;
                    }
                    if ( (lastE.equals(".")) && (op.equals(""))){
                        num2comma = false;
                    }
                    // Apaga ultima char
                    String tv_TelaAux = String.valueOf(tv_Tela.getText()).substring(0, tv_Tela.length() - 1);
                    tv_Tela.setText(tv_TelaAux);
                }


            }
        });


        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparTela();
            }
        });



        bt_enter.setOnClickListener(new View.OnClickListener() {
            @Override //
            public void onClick(View view) {
                String lastE = String.valueOf(String.valueOf(tv_Tela.getText()).charAt(tv_Tela.length()-1));
                // verifica se há sinal para operação e se há 2 numeros
                if ( (op.equals("")) || (lastE.equals(op)) || (num2str.equals("")) ){
                    Toast.makeText(MainActivity.this, "Não é uma conta", Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(MainActivity.this, ".", Toast.LENGTH_SHORT).show();
                    num2 = Double.parseDouble(num2str);

                    //
                    // Operações
                    //

                    switch (op) {
                        case "+":
                            resultado = num1 + num2;
                            break;
                        case "-":
                            resultado = num1 - num2;
                            break;
                        case "/":
                            if (!num2.equals(0.0)) {
                                resultado = num1 / num2;
                            } else {
                                Toast.makeText(MainActivity.this, "Impossivel dividir por zero!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "*":
                            resultado = num1 * num2;
                            break;
                        case "":
                            Toast.makeText(MainActivity.this, "Ocorreu um Erro inesperado!", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    if (resultado%1 == 0){
                        tv_Tela.setText(String.format("%.0f", resultado));
                    } else {
                        tv_Tela.setText(String.format("%.2f", resultado));
                    }

                    resetTela = true;
                    num1conf = false;
                    num1 = null;
                    num2 = null;
                    num2str = "";
                    num1comma = false;
                    num2comma = false;

                }
            }
        });
    }


    //
    // METODOS EXTERNOS
    //

    // Limpa todas variaveis e operações.
    public void limparTela() {
        tv_Tela.setText("0");
        num1conf = false;
        num1 = null;
        num2 = null;
        num2str = "";
        num1comma = false;
        num2comma = false;
        op = "";
    }

    //  Adiciona numeros na Tela
    public void clickBtn(String numBotao){
        if (resetTela){
            limparTela();
            resetTela = false;
        }

        //verifica se a tela esta vazia ( com 0 )
        Log.i("tv_Tela", tv_Tela.getText().toString());
        if (tv_Tela.getText().toString().equals("0")) {
            tv_Tela.setText(numBotao);
        }else{
            //verifica se há operação
            if(op.equals("")){
                tv_Tela.setText(tv_Tela.getText().toString().concat(numBotao));
            } else {
                //verifica se o numero 2 esta vazio ( com 0 )
                if (num2str.equals("0")){
                    num2str = numBotao;
                }else{
                    num2str = num2str + numBotao;
                    tv_Tela.setText(tv_Tela.getText().toString().concat(numBotao));
                }
            }
        }
    }

    // Adiciona Operações na Tela
    public void clickOperacao(String botaoOp){
        // Atribui a variavel LastE o ultimo elemento da tela
        String lastE = String.valueOf(String.valueOf(tv_Tela.getText()).charAt(tv_Tela.length()-1));

        // Confere se o ultimo elemento é uma operação
        if ( (op.equals("")) && (!(lastE.equals("."))) ) {
            num1 = Double.parseDouble(String.valueOf(tv_Tela.getText()));
            num1conf = true;
            tv_Tela.setText(tv_Tela.getText().toString().concat(botaoOp));
            op = botaoOp;
        } else {
            if ((!botaoOp.equals(op)) && (!op.equals(""))) {
            // || (botaoOp == "-") || (botaoOp == "*") || (botaoOp == "/")\+\-\*\/
                tv_Tela.setText(tv_Tela.getText().toString().replaceAll( "[*^/+-]",botaoOp) );
//                tv_Tela.setText(tv_Tela.getText().toString().replace( "+" , botaoOp));
//                tv_Tela.setText(tv_Tela.getText().toString().replace( "-" , botaoOp));
//                tv_Tela.setText(tv_Tela.getText().toString().replace( "/" , botaoOp));
//                tv_Tela.setText(tv_Tela.getText().toString().replace( "*" , botaoOp));
                op = botaoOp;
            }
        }
    }

    // Verifica qual botão foi apertado
    private View.OnClickListener escutabotao = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Button botaoNum = (Button) findViewById(v.getId());
            if ((botaoNum == bt_add) || (botaoNum == bt_subt) || (botaoNum == bt_division) || (botaoNum == bt_multi)){
                clickOperacao((String) botaoNum.getText());
            }else {
                clickBtn((String) botaoNum.getText());
            }
        }
    };

}