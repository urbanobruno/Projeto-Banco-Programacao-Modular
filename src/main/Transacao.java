package main;

import java.time.LocalDate;

public class Transacao {
    int id;
    double valorTransacao;
    LocalDate data;

    static int nextTransacaoId = 1;

    public Transacao(double valorTransacao) {
         this.id = nextTransacaoId;
         nextTransacaoId += 1;

        this.valorTransacao = valorTransacao;
        this.data = LocalDate.now();

    }

    public LocalDate getData() {
        return data;
    }

    public double getValorTransacao() {
        return valorTransacao;
    }

}
