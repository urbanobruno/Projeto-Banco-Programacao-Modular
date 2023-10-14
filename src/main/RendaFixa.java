package main;

public class RendaFixa extends Conta {
    private double rendimentoContratado;
    private static final double IMPOSTO = 0.15; // 15%

    public RendaFixa(Cliente cliente, double rendimentoContratado) {
        super(cliente);
        this.rendimentoContratado = rendimentoContratado;
    }

    @Override
    public void atualizarSaldo() {
        double saldoAtual = getSaldo();
        double rendimento = saldoAtual * (rendimentoContratado / 100);
        double imposto = rendimento * IMPOSTO;
        saldoAtual += rendimento - imposto;
        setSaldo(saldoAtual);
    }
}
