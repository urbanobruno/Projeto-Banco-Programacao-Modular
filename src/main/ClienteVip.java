package main;

public class ClienteVip extends Cliente {
    private static final double MENSALIDADE = 30.0;
    private static final int PONTOS_FIDELIDADE_MENSAL = 35;
    private static final int PONTOS_POR_SALDO = 30; // 30 pontos para cada R$2.000 de saldo

    public ClienteVip(String nome, String cpf) {
        super(nome, cpf);
    }

    public boolean pagarMensalidade(Conta conta) {
        return conta.saque(MENSALIDADE);
    }

    public void acumularPontosFidelidade(Conta conta) {
        int pontos = PONTOS_FIDELIDADE_MENSAL;
        double saldo = conta.getSaldo();
        pontos += (int) (saldo / 2000) * PONTOS_POR_SALDO;
        conta.setPontosFidelidade(conta.getPontosFidelidade() + pontos);
    }

    public void trocarPontosPorRecompensas(Conta conta) {
        int pontos = conta.getPontosFidelidade();
        double valorRecompensa = pontos / 10.0; // Cada 10 pontos equivalem a R$1 em recompensa

        if (pontos >= 10) {
            if (conta.saque(valorRecompensa)) {
                conta.setPontosFidelidade(pontos - 10);
                System.out.println("Recompensa resgatada no valor de R$" + valorRecompensa);
            } else {
                System.out.println("Saldo insuficiente para resgatar recompensa.");
            }
        }
    }
}

