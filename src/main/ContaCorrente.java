package main;

public class ContaCorrente extends Conta {
    private static final double TAXA_MENSAL = 20.0;
    private static final double LIMITE_SAQUE_ESPECIAL = 200.0;

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void atualizarSaldo() {
        double saldoAtual = getSaldo();
        double taxaMensal = getSaldo() < 0 ? 0 : TAXA_MENSAL;
        saldoAtual -= taxaMensal;
        setSaldo(saldoAtual);
    }

    public double getLimiteSaqueEspecial() {
        return LIMITE_SAQUE_ESPECIAL;
    }
}
