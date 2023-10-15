public class RendaFixa extends Conta {
    private double rendimentoContratado;
    private static final double IMPOSTO = 0.15;

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

    public void sacar(double valor) {
    double saldoAtual = getSaldo();

    if (valor <= 0) {
        System.out.println("O valor do saque deve ser positivo.");
    } else {
        double rendimento = this.rendimentoContratado / 100; // Converta a taxa de rendimento contratada para a forma decimal
        double rendimentoDiario = saldoAtual * rendimento;
        double rendimentoTaxado = rendimentoDiario * IMPOSTO;

        if (saldoAtual >= valor + rendimentoTaxado) {
            saldoAtual -= (valor + rendimentoTaxado);
            setSaldo(saldoAtual);

            // Zere o rendimento após o saque
            this.rendimentoContratado = 0;

            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para realizar o saque após o desconto de imposto.");
        }
    }
}
}
