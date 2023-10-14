package main;

public class Poupanca extends Conta {
    private static final double RENDIMENTO_MENSAL = 0.005; // 0.5% ao mês

    public Poupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void atualizarSaldo() {
        double saldoAtual = getSaldo();
        saldoAtual += saldoAtual * RENDIMENTO_MENSAL;
        setSaldo(saldoAtual);
    }
}
