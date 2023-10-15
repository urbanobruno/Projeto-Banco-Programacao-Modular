package main;

public class ClienteGold extends Cliente {
    private static final double TAXA_MENSAL = 10.0;
    private static final int PONTOS_FIDELIDADE_MENSAL = 10;
    private static final int PONTOS_POR_SALDO = 10; // 10 pontos para cada R$1.000 de saldo

    public ClienteGold(String nome, String cpf) {
        super(nome, cpf);
    }

    public boolean pagarTaxaMensal(Conta conta) {
        return conta.saque(TAXA_MENSAL);
    }

    public void acumularPontosFidelidade(Conta conta) {
        int pontos = PONTOS_FIDELIDADE_MENSAL;
        double saldo = conta.getSaldo();
        pontos += (int) (saldo / 1000) * PONTOS_POR_SALDO;
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

