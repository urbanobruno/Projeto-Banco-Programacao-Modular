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

    public void sacar(double valor) {
        double saldoAtual = getSaldo();
        double limiteSaqueEspecial = getLimiteSaqueEspecial();

        if (valor <= 0) {
            System.out.println("O valor do saque deve ser positivo.");
        } else if (saldoAtual + limiteSaqueEspecial >= valor) {
            if (saldoAtual >= valor) {
                saldoAtual -= valor;
            } else {
                double saqueEspecial = valor - saldoAtual;
                saldoAtual = 0;
                System.out.println("Saque de R$" + saldoAtual + " realizado com sucesso.");
                System.out.println("Saque especial de R$" + saqueEspecial + " realizado com sucesso.");
            }
            setSaldo(saldoAtual);
        } else {
            System.out.println("Saldo e limite de saque especial insuficientes para realizar o saque.");
        }
    }
}
