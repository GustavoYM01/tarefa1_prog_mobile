package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button numeroZero, numeroUm, numeroDois, numeroTres, numeroQuatro, numeroCinco,
    numeroSeis, numeroSete, numeroOito, numeroNove, ponto, soma, subtracao, multiplicacao,
    divisao, igual, btn_limpar;

    private TextView txtExpressao, txtResultado;

    private ImageView backspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IniciarComponentes();
        getSupportActionBar().hide();

        numeroZero.setOnClickListener(this);
        numeroUm.setOnClickListener(this);
        numeroDois.setOnClickListener(this);
        numeroTres.setOnClickListener(this);
        numeroQuatro.setOnClickListener(this);
        numeroCinco.setOnClickListener(this);
        numeroSeis.setOnClickListener(this);
        numeroSete.setOnClickListener(this);
        numeroOito.setOnClickListener(this);
        numeroNove.setOnClickListener(this);
        ponto.setOnClickListener(this);
        soma.setOnClickListener(this);
        subtracao.setOnClickListener(this);
        multiplicacao.setOnClickListener(this);
        divisao.setOnClickListener(this);

        btn_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtExpressao.setText("");
                txtResultado.setText("");
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView expressao = findViewById(R.id.txt_expressao);
                String txt = expressao.getText().toString();

                if (!txt.isEmpty())
                    expressao.setText(txt.substring(0, txt.length()-1));

                txtResultado.setText("");
            }
        });

        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Expression exp = new ExpressionBuilder(txtExpressao.getText().toString()).build();
                    double resultado = exp.evaluate();
                    long longResult = (long) resultado;

                    if (resultado == (double) longResult)
                        txtResultado.setText((CharSequence) String.valueOf(longResult));
                    else
                        txtResultado.setText((CharSequence) String.valueOf(resultado));
                } catch (Exception e) {
                    if (e.getMessage().toString().toLowerCase().contains("invalid number of operands")) {
                        Toast.makeText(MainActivity.this, "Operação inválida", Toast.LENGTH_SHORT).show();
                        txtExpressao.setText("");
                        txtResultado.setText("");
                    } else
                        Toast.makeText(MainActivity.this, "Ocorreu um erro..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void IniciarComponentes() {
        numeroZero = findViewById(R.id.numero_zero);
        numeroUm = findViewById(R.id.numero_um);
        numeroDois = findViewById(R.id.numero_dois);
        numeroTres = findViewById(R.id.numero_tres);
        numeroQuatro = findViewById(R.id.numero_quatro);
        numeroCinco = findViewById(R.id.numero_cinco);
        numeroSeis = findViewById(R.id.numero_seis);
        numeroSete = findViewById(R.id.numero_sete);
        numeroOito = findViewById(R.id.numero_oito);
        numeroNove = findViewById(R.id.numero_nove);
        ponto = findViewById(R.id.ponto);
        soma = findViewById(R.id.soma);
        subtracao = findViewById(R.id.subtracao);
        multiplicacao = findViewById(R.id.multiplicacao);
        divisao = findViewById(R.id.divisao);
        igual = findViewById(R.id.igual);
        btn_limpar = findViewById(R.id.bt_limpar);
        txtExpressao = findViewById(R.id.txt_expressao);
        txtResultado = findViewById(R.id.txt_resultado);
        backspace = findViewById(R.id.backspace);
    }

    public void AcrescentarExpressao(String expressao, boolean limpar_dados) {
        if (txtResultado.getText().equals(""))
            txtExpressao.setText(" ");
        if (limpar_dados) {
            txtResultado.setText(" ");
            txtExpressao.append(expressao);
        } else {
            txtExpressao.append(txtResultado.getText());
            txtExpressao.append(expressao);
            txtResultado.setText(" ");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.numero_zero:
                AcrescentarExpressao("0", true);
                break;
            case R.id.numero_um:
                AcrescentarExpressao("1", true);
                break;
            case R.id.numero_dois:
                AcrescentarExpressao("2", true);
                break;
            case R.id.numero_tres:
                AcrescentarExpressao("3", true);
                break;
            case R.id.numero_quatro:
                AcrescentarExpressao("4", true);
                break;
            case R.id.numero_cinco:
                AcrescentarExpressao("5", true);
                break;
            case R.id.numero_seis:
                AcrescentarExpressao("6", true);
                break;
            case R.id.numero_sete:
                AcrescentarExpressao("7", true);
                break;
            case R.id.numero_oito:
                AcrescentarExpressao("8", true);
                break;
            case R.id.numero_nove:
                AcrescentarExpressao("9", true);
                break;
            case R.id.ponto:
                AcrescentarExpressao(".", true);
                break;
            case R.id.soma:
                AcrescentarExpressao("+ ", false);
                break;
            case R.id.subtracao:
                AcrescentarExpressao("- ", false);
                break;
            case R.id.multiplicacao:
                AcrescentarExpressao("* ", false);
                break;
            case R.id.divisao:
                AcrescentarExpressao("/ ", false);
                break;
        }
    }
}