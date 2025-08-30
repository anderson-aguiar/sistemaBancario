package services;

import entities.Account;
import entities.Client;
import exceptions.AccountNotFoundException;
import exceptions.ClientNotFoundException;
import exceptions.IsExistClientException;

import java.util.Map;
import java.util.Set;

public class BankService {
    private int accountNumber = 1000;

    public void addClient(Set<Client> clients, String name, String cpf) {
        checkCPF(cpf);
        if (!clients.add(new Client(name, cpf)))
            throw new IsExistClientException("Cliente já cadastrado.");

        System.out.println("Cliente " + name + " cadastrado com sucesso.");
    }

    public void createAccount(Set<Client> clients, Map<Integer, Account> accountMap, String cpf) {
        checkCPF(cpf);
        this.accountNumber++;
        Client client = clients.stream().filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new ClientNotFoundException("Cliente não tem cadastro."));

        Account account = new Account(this.accountNumber, client, 0.0);
        accountMap.put(this.accountNumber, account);
        System.out.println("Conta " + this.accountNumber + " criada com sucesso!!!");
    }

    public void deposit(Map<Integer, Account> accounts, int accountNumber, double value) {
        Account account = accounts.get(accountNumber);
        if (account == null) throw new AccountNotFoundException("Conta não tem cadastro.");
        account.deposit(value);
        System.out.println("Deposito realizado com sucesso.");
    }

    public void withdraw(Map<Integer, Account> accounts, int accountNumber, double value) {
        Account account = accounts.get(accountNumber);
        if (account == null) throw new AccountNotFoundException("Conta não tem cadastro.");
        account.withdraw(value);
        System.out.println("Saque realizado com sucesso.");
    }

    public void transfer(Map<Integer, Account> accounts, int fromAccountNumber, int toAccountNumber, double value) {
        Account fromAccout = accounts.get(fromAccountNumber);
        Account toAccount = accounts.get(toAccountNumber);
        if (fromAccout == null) {
            throw new AccountNotFoundException("Conta numero " + fromAccountNumber + " não cadastrada!");
        }
        if (toAccount == null) {
            throw new AccountNotFoundException("Conta numero " + toAccountNumber + " não cadastrada!");
        }
        fromAccout.transfer(value, toAccount);
        System.out.println("Transferência realizada com sucesso.");
    }

    public double getBalance(Map<Integer, Account> accounts, int accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            return account.getBalance();
        } else {
            throw new AccountNotFoundException("Conta numero " + accountNumber + " não cadastrada!");
        }
    }

    public void showStatements(Map<Integer, Account> accounts, int accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.printStatement();
        } else {
            throw new AccountNotFoundException("Conta numero " + accountNumber + " não cadastrada!");
        }
    }

    public void showClients(Set<Client> clients) {
        if (clients.isEmpty()) {
            throw new ClientNotFoundException("Não existe clientes cadastrados");
        }
        clients.forEach(System.out::println);
    }

    private void checkCPF(String cpf) {
        if (cpf.length() < 11 || cpf.isBlank())
            throw new IllegalArgumentException("CPF inválido, tente novamente.");

        if (!cpf.matches("\\d{11}"))
            throw new IllegalArgumentException("CPF deve conter apenas números. Tente novamente");

    }
}
