package main;

import java.util.Random;

public class Investimento extends Conta {
    private static final double IMPOSTO = 0.15; 
    private static final double TAXA = 0.015; 

    private double rendimento = 0;

    public Investimento(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void atualizarSaldo() {
        double saldoAtual = getSaldo();

        double variacaoDiaria = (new Random().nextDouble() - 0.5) / 10.0;

        double saldoAnterior = saldoAtual; // Saldo anterior antes da variação

        // Calcule o rendimento diário como a diferença entre o saldo atual após a variação e o saldo anterior
        double rendimentoDiario = (saldoAtual * variacaoDiaria) - saldoAnterior;

        this.rendimento += rendimentoDiario;

        saldoAtual += rendimentoDiario;
        setSaldo(saldoAtual);
    }
}


 public void sacar(double valor) {
        double saldoAtual = getSaldo();

        if (valor <= 0) {
            System.out.println("O valor do saque deve ser positivo.");
        } else {
            double rendimento = this.rendimento;
            double imposto = rendimento * IMPOSTO;

            if (saldoAtual >= valor + imposto) {
                saldoAtual -= (valor + imposto);
                setSaldo(saldoAtual);
                System.out.println("Saque de R$" + valor + " realizado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para realizar o saque após o desconto de imposto.");
            }
        }
    }
}







