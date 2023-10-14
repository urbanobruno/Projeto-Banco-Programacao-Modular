package main;

import java.util.Random;

public class Investimento extends Conta {
    private static final double IMPOSTO = 0.15; 
    private static final double TAXA = 0.015; 

    public Investimento(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void atualizarSaldo() {
        double saldoAtual = getSaldo();


        double variacaoDiaria = (new Random().nextDouble() - 0.5) / 10.0;
        
    
        double rendimentoDiario = saldoAtual * variacaoDiaria;
        
    
        double imposto = rendimentoDiario * IMPOSTO;
        
    
        double taxa = rendimentoDiario * TAXA;
        

        saldoAtual += rendimentoDiario - imposto - taxa;
        setSaldo(saldoAtual);
    }
}
