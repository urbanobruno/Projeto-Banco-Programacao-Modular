package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Menu XuBank:");
            System.out.println("1. Criar Cliente");
            System.out.println("2. Criar Conta");
            System.out.println("3. Mostrar todos os Clientes");
            System.out.println("4. Mostrar todas as Contas");

            System.out.println("5. Consultar Saldo");
            System.out.println("6. Consultar Extrato dos últimos 30 dias");
            System.out.println("7. Depositar");
            System.out.println("8. Sacar");
            System.out.println("9. Transferir");
            System.out.println("10. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    // Lógica para criar cliente

                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.next();
                    System.out.print("Digite o CPF do cliente: ");
                    String cpf = scanner.next();

                    // Solicite o tipo de cliente
                    System.out.println("Escolha o tipo de cliente:");
                    System.out.println("1. Regular");
                    System.out.println("2. Gold");
                    System.out.println("3. Vip");
                    int tipoCliente = scanner.nextInt();

                    Cliente novoCliente = null;

                    switch (tipoCliente) {
                        case 1:
                            novoCliente = new Cliente(nome, cpf);
                            break;
                        case 2:
                            novoCliente = new ClienteGold(nome, cpf);
                            break;
                        case 3:
                            novoCliente = new ClienteVip(nome, cpf);
                            break;
                        default:
                            System.out.println("Tipo de cliente inválido.");
                            break;
                    }

                    if (novoCliente != null) {
                        if (!Cliente.cpfExiste(cpf)) {
                            Cliente.clientes.add(novoCliente);
                            System.out.println("Cliente criado com sucesso!");
                        } else {
                            System.out.println("CPF já cadastrado!");
                        }
                    }

                    break;
                case 2:
                    // Lógica para criar conta

                    System.out.println("Digite o CPF do cliente para criar a conta: ");
                    cpf = scanner.next();
                    Cliente cliente = Cliente.buscarClientePorCPF(cpf);
                    if (cliente != null) {
                        Conta novaConta = new Conta(cliente);
                        cliente.contas.add(novaConta);
                        Conta.contas.add(novaConta);
                        System.out.println("Conta criada com sucesso!");
                    } else {
                        System.out.println("Cliente não encontrado!");
                    }

                    break;
                case 3:
                    mostrarTodosOsClientes();
                    break;
                case 4:
                    mostrarTodasAsContas();
                    break;
                case 5:
                    System.out.println("Digite o CPF do cliente para consultar o saldo: ");
                    cpf = scanner.next();
                    cliente = Cliente.buscarClientePorCPF(cpf);
                    if (cliente != null) {
                        for (Conta conta : cliente.getContas()) {
                            System.out.println("Conta: " + conta.getNumeroConta() + ", Saldo: " + conta.getSaldo());
                        }
                    } else {
                        System.out.println("Cliente não encontrado!");
                    }
                    break;

                case 6:
                    System.out.println("Digite o CPF do cliente para consultar o extrato: ");
                    cpf = scanner.next();
                    cliente = Cliente.buscarClientePorCPF(cpf);
                    if (cliente != null) {
                        cliente.consultarExtrato30Dias();
                    } else {
                        System.out.println("Cliente não encontrado!");
                    }
                    break;

                case 7:
                    System.out.print("Digite o CPF do cliente: ");
                    String cpfDeposito = scanner.next();
                    Cliente clienteDeposito = Cliente.buscarClientePorCPF(cpfDeposito);
                    if (clienteDeposito != null) {
                        System.out.print("Digite o número da conta: ");
                        String numeroContaDeposito = scanner.next();
                        Conta contaDeposito = Conta.buscarContaPorNumero(numeroContaDeposito);
                        if (contaDeposito != null) {
                            System.out.print("Digite o valor a ser depositado: ");
                            double valorDeposito = scanner.nextDouble();
                            if (clienteDeposito.depositar(valorDeposito, contaDeposito)) {
                                System.out.println("Depósito realizado com sucesso!");
                            } else {
                                System.out.println("Depósito falhou!");
                            }
                        } else {
                            System.out.println("Conta não encontrada!");
                        }
                    } else {
                        System.out.println("Cliente não encontrado!");
                    }
                    break;

                case 8:
                    // Lógica para sacar
                    break;
                case 9:

                    System.out.print("Digite o CPF do cliente que está transferindo: ");
                    String cpfTransferencia = scanner.next();
                    Cliente clienteTransferencia = Cliente.buscarClientePorCPF(cpfTransferencia);
                    if (clienteTransferencia != null) {
                        System.out.print("Digite o número da conta de origem: ");
                        String numeroContaOrigem = scanner.next();
                        Conta contaOrigem = Conta.buscarContaPorNumero(numeroContaOrigem);
                        if (contaOrigem != null) {
                            System.out.print("Digite o número da conta de destino: ");
                            String numeroContaDestino = scanner.next();
                            Conta contaDestino = Conta.buscarContaPorNumero(numeroContaDestino);
                            if (contaDestino != null) {
                                System.out.print("Digite o valor a ser transferido: ");
                                double valorTransferencia = scanner.nextDouble();
                                if (clienteTransferencia.realizarTransferencia(valorTransferencia, contaOrigem,
                                        contaDestino)) {
                                    System.out.println("Transferência realizada com sucesso!");
                                } else {
                                    System.out.println("Transferência falhou!");
                                }
                            } else {
                                System.out.println("Conta de destino não encontrada!");
                            }
                        } else {
                            System.out.println("Conta de origem não encontrada!");
                        }
                    } else {
                        System.out.println("Cliente não encontrado!");
                    }
                    break;

                case 10:
                    visaoDiretoria();
                    break;

                case 11:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 8);

        scanner.close();
    }

    public static void mostrarTodosOsClientes() {
        if (Cliente.clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println("Lista de Clientes:");
        for (Cliente cliente : Cliente.clientes) {
            System.out
                    .println("ID: " + cliente.getId() + ", Nome: " + cliente.getNome() + ", CPF: " + cliente.getCpf());
        }
    }

    public static void mostrarTodasAsContas() {
        if (Conta.contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        System.out.println("Lista de Contas:");
        for (Conta conta : Conta.contas) {
            System.out.println("Número da Conta: " + conta.getNumeroConta() + ", Cliente: "
                    + conta.getCliente().getNome() + ", Saldo: " + conta.getSaldo());
        }
    }

    public static void visaoDiretoria() {
        Scanner sc = new Scanner(System.in);
        int opcaoDiretoria;

        do {
            System.out.println("Menu Visão de Diretoria:");
            System.out.println("1. Total em custódia para cada tipo de conta");
            System.out.println("2. Saldo médio de todas as contas");
            System.out.println("3. Número de clientes com saldo total negativo");
            System.out.println("4. Clientes com maior e menor saldo total");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoDiretoria = sc.nextInt();

            switch (opcaoDiretoria) {
                case 1:
                    mostrarTotalCustodiaPorTipoConta();
                    break;
                case 2:
                    mostrarSaldoMedioContas();
                    break;
                case 3:
                    mostrarClientesSaldoNegativo();
                    break;
                case 4:
                    mostrarClientesMaiorMenorSaldo();
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcaoDiretoria != 5);
    }

    public static void mostrarTotalCustodiaPorTipoConta() {
        double totalContaCorrente = 0;
        double totalContaPoupanca = 0;
        double totalContaRendaFixa = 0;
        double totalContaInvestimento = 0;

        for (Conta conta : Conta.contas) {
            if (conta instanceof ContaCorrente) {
                totalContaCorrente += conta.getSaldo();
            } else if (conta instanceof Poupanca) {
                totalContaPoupanca += conta.getSaldo();
            } else if (conta instanceof RendaFixa) {
                totalContaRendaFixa += conta.getSaldo();
            } else if (conta instanceof Investimento) {
                totalContaInvestimento += conta.getSaldo();
            }
        }

        System.out.println("Total em custódia:");
        System.out.println("Conta Corrente: R$" + totalContaCorrente);
        System.out.println("Conta Poupança: R$" + totalContaPoupanca);
        System.out.println("Conta Renda Fixa: R$" + totalContaRendaFixa);
        System.out.println("Conta Investimento: R$" + totalContaInvestimento);
    }

    public static void mostrarSaldoMedioContas() {
        double totalSaldo = 0;

        for (Conta conta : Conta.contas) {
            totalSaldo += conta.getSaldo();
        }

        double saldoMedio = totalSaldo / Conta.contas.size();
        System.out.println("Saldo médio de todas as contas: R$" + saldoMedio);
    }

    public static void mostrarClientesSaldoNegativo() {
        int countClientesNegativos = 0;

        for (Cliente cliente : Cliente.clientes) {
            double saldoTotalCliente = 0;
            for (Conta conta : cliente.getContas()) {
                saldoTotalCliente += conta.getSaldo();
            }
            if (saldoTotalCliente < 0) {
                countClientesNegativos++;
            }
        }

        System.out.println("Número de clientes com saldo total negativo: " + countClientesNegativos);
    }

    public static void mostrarClientesMaiorMenorSaldo() {
        Cliente clienteMaiorSaldo = null;
        Cliente clienteMenorSaldo = null;
        double maiorSaldo = Double.MIN_VALUE;
        double menorSaldo = Double.MAX_VALUE;

        for (Cliente cliente : Cliente.clientes) {
            double saldoTotalCliente = 0;
            for (Conta conta : cliente.getContas()) {
                saldoTotalCliente += conta.getSaldo();
            }
            if (saldoTotalCliente > maiorSaldo) {
                maiorSaldo = saldoTotalCliente;
                clienteMaiorSaldo = cliente;
            }
            if (saldoTotalCliente < menorSaldo) {
                menorSaldo = saldoTotalCliente;
                clienteMenorSaldo = cliente;
            }
        }

        System.out.println("Cliente com maior saldo: " + clienteMaiorSaldo.getNome() + ", Saldo: R$" + maiorSaldo);
        System.out.println("Cliente com menor saldo: " + clienteMenorSaldo.getNome() + ", Saldo: R$" + menorSaldo);
    }
}