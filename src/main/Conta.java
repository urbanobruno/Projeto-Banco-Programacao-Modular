package main;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    int id;
    String numeroConta;
    Cliente cliente;
    double saldo;
    List<Transacao> extrato = new ArrayList<>(); 

    static List<Conta> contas = new ArrayList<>();
    static int nextContaId = 1;

    public void registrarTransacao(double valor) {

        Transacao novaTransacao = new Transacao(valor);
        extrato.add(novaTransacao);

    }

    public List<Transacao> getExtrato() {
        return extrato;
    }

    public int getId() {
        return id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
