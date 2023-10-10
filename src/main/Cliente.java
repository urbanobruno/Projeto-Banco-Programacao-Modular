package main;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    int id;
    String nome;
    String cpf;
    List<Conta> contas = new ArrayList<>();

    static List<Cliente> clientes = new ArrayList<>();
    static int nextClienteId = 1;

    public double consultarSaldo(Conta conta) {
        return conta.getSaldo();
    }

    public void consultarExtrato30Dias() {
        
    }

    public boolean depositar(double valor, Conta conta) {
        boolean depositoEfetuado = false;

        // Todo testar
        if (valor > 0) {
            conta.saldo += valor;
            depositoEfetuado = true;
        }

        return depositoEfetuado;

    }

    public boolean sacar(double valor, Conta conta) {
        
    }

    public boolean realizarTransferencia(double valor, Conta contaMandante, Conta contaRecebe) {
        boolean trasferenciaEfetuada = false;
        
        if (valor > 0) {
            contaMandante.saldo -= valor;
            contaRecebe.saldo += valor;
            trasferenciaEfetuada = true;

            contaMandante.registrarTransacao(0 - valor);
            contaRecebe.registrarTransacao(valor);
        }

        return trasferenciaEfetuada;

    }

    public List<Conta> getContas() {
        return contas;
    }

    public String getCpf() {
        return cpf;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
