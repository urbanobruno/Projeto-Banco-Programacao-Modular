package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private String cpf;

    List<Conta> contas = new ArrayList<>();
    public static List<Cliente> clientes = new ArrayList<>();
    private static int nextClienteId = 1;

    public Cliente(String nome, String cpf) {
        this.id = nextClienteId;

        this.nome = nome;
        this.cpf = cpf;
    }

    public static boolean cpfExiste(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public double consultarSaldo(Conta conta) {
        return conta.getSaldo();
    }

    public void consultarExtrato30Dias() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate trintaDiasAtras = dataAtual.minusDays(30);
    
        System.out.println("Extrato dos últimos 30 dias para o cliente " + nome + ":");
        
        for (Conta conta : contas) {
            System.out.println("Conta: " + conta.getNumeroConta());
            for (Transacao transacao : conta.getExtrato()) {
                if (!transacao.getData().isBefore(trintaDiasAtras)) {
                    System.out.println("Data: " + transacao.getData() + ", Valor: " + transacao.getValorTransacao());
                }
            }
            System.out.println("-----"); // Uma linha separadora para cada conta
        }
    }

    public boolean depositar(double valor, Conta conta) {
        boolean depositoEfetuado = false;

        // Todo testar
        if (valor > 0) {
            conta.setSaldo(conta.getSaldo() + valor);
            depositoEfetuado = true;

            conta.registrarTransacao(valor);
        }

        return depositoEfetuado;

    }

    public boolean realizarTransferencia(double valor, Conta contaMandante, Conta contaRecebe) {
        boolean trasferenciaEfetuada = false;
        
        if (valor > 0) {
            contaMandante.setSaldo(contaMandante.getSaldo() - valor);
            contaRecebe.setSaldo(contaRecebe.getSaldo() - valor);

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

    public static Cliente buscarClientePorCPF(String cpf) {
        for (Cliente cliente : Cliente.clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

}
