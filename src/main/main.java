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
                    if (!Cliente.cpfExiste(cpf)) {
                        Cliente novoCliente = new Cliente(nome, cpf);
                        Cliente.clientes.add(novoCliente);
                        System.out.println("Cliente criado com sucesso!");
                    } else {
                        System.out.println("CPF já cadastrado!");
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
                    // Lógica para depositar
                    break;
                case 6:
                    // Lógica para sacar
                    break;
                case 7:
                    // Lógica para transferir
                    break;
                case 8:
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
}