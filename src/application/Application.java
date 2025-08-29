package application;

import entities.Account;
import entities.Client;
import execptions.AccountNotFoundException;
import execptions.ClientNotFoundException;
import execptions.InvalidTransactionException;
import execptions.IsExistClientException;
import services.BankService;

import java.util.*;

public class Application {
    private static Set<Client> clients = new HashSet<>();
    private static Map<Integer, Account> accountMap = new HashMap<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        BankService bankService = new BankService();
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== MENU BANCO DIGITAL ===");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Conta");
            System.out.println("3 - Depósito");
            System.out.println("4 - Saque");
            System.out.println("5 - Transferência");
            System.out.println("6 - Consultar Saldo");
            System.out.println("7 - Extrato");
            System.out.println("8 - Listar Clientes");
            System.out.println("9 - Sair");
            System.out.print("Escolha: ");

            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String name = sc.nextLine();
                    while (true) {
                        try {
                            System.out.print("CPF: ");
                            String cpf = sc.nextLine();
                            bankService.addClient(clients, name, cpf);
                            break;
                        } catch (IllegalArgumentException | IsExistClientException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Informe CPF: ");
                        String cpf = sc.nextLine();

                        bankService.createAccount(clients, accountMap, cpf);
                    } catch (IllegalArgumentException | ClientNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        int number = readInt("Informe o número da conta: ");
                        double value = readDouble("Valor para depósito: ");

                        bankService.deposit(accountMap, number, value);
                    } catch (AccountNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        int number = readInt("Informe o número da conta: ");
                        double value = readDouble("Valor para saque: ");

                        bankService.withdraw(accountMap, number, value);
                    } catch (AccountNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        int fromAccount = readInt("Informe a conta de origem: ");
                        int toAccount = readInt("Infomer a conta de destino: ");
                        double value = readDouble("Informe o valor: R$ ");
                        bankService.transfer(accountMap, fromAccount, toAccount, value);
                    } catch (AccountNotFoundException | InvalidTransactionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    consultarSaldo(sc);
                    break;
                case 7:
                    extrato(sc);
                    break;
                case 8:
                    listarClientes();
                    break;
                case 9:
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor válido. Tente novamente.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor válido. Tente novamente.");
            }
        }

    }
}
