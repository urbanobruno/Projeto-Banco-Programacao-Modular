package main;

public class Poupanca extends Conta {
    private static final double RENDIMENTO_MENSAL = 0.005; // 0,5% ao mês

    public Poupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void atualizarSaldo() {
        double saldoAtual = getSaldo();
        saldoAtual += saldoAtual * RENDIMENTO_MENSAL;
        setSaldo(saldoAtual);
    }

    @Override
    public void sacar(double valor) {
        double saldoAtual = getSaldo();

        if (valor <= 0) {
            System.out.println("O valor do saque deve ser positivo.");
        } else if (saldoAtual >= valor) {
            java.util.Calendar cal = java.util.Calendar.getInstance();
            int diaAtual = cal.get(java.util.Calendar.DAY_OF_MONTH);

            if (diaAtual == 5) {
                double rendimento = saldoAtual * RENDIMENTO_MENSAL;
                saldoAtual += rendimento;
            }

            saldoAtual -= valor;
            setSaldo(saldoAtual);
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para realizar o saque.");
        }
    }
}
