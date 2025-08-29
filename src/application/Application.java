package application;

import entities.Account;
import entities.Client;

import java.util.*;

public class Application {
    private static Set<Client> clientes = new HashSet<>();
    private static Map<Integer, Account> contas = new HashMap<>();
    private static int numeroConta = 1001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
                case 1: cadastrarCliente(sc); break;
                case 2: cadastrarConta(sc); break;
                case 3: deposito(sc); break;
                case 4: saque(sc); break;
                case 5: transferencia(sc); break;
                case 6: consultarSaldo(sc); break;
                case 7: extrato(sc); break;
                case 8: listarClientes(); break;
                case 9: rodando = false; break;
                default: System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }

}
