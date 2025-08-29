package services;

import entities.Account;
import entities.Client;
import execptions.AccountNotFoundException;
import execptions.ClientNotFoundException;
import execptions.IsExistClientException;

import java.util.Map;
import java.util.Set;

public class BankService {
    private int numberAccount = 1000;

    public void addClient(Set<Client> clients, String name, String cpf) {
        checkCPF(cpf);
        if (!clients.add(new Client(name, cpf)))
            throw new IsExistClientException("Cliente já cadastrado.");

        System.out.println("Cliente " + name + " cadastrado com sucesso.");
    }

    public void createAccount(Set<Client> clients, Map<Integer, Account> accountMap, String cpf) {
        checkCPF(cpf);
        numberAccount++;
        Client client = clients.stream().filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new ClientNotFoundException("Cliente não tem cadastro."));
        Account account = new Account(numberAccount, client, 0.0);
        accountMap.put(numberAccount, account);
        System.out.println("Conta " + numberAccount + " criada com sucesso!!!");
    }

    public void deposit(Map<Integer, Account> accounts, int numberAccount, double value) {
        Account account = accounts.get(numberAccount);
        if (account == null) throw new AccountNotFoundException("Conta não tem cadastro.");
        account.deposit(value);
        System.out.println("Deposito realizado com sucesso");
    }

    public void withdraw(Map<Integer, Account> accounts, int numberAccount, double value) {
        Account account = accounts.get(numberAccount);
        if (account == null) throw new AccountNotFoundException("Conta não tem cadastro.");
        account.withdraw(value);
        System.out.println("Saque realizado com sucesso");
    }

    public void transfer(Map<Integer, Account> accounts, int numberFromAccount, int numberToAccount, double value) {
        Account fromAccout = accounts.get(numberFromAccount);
        Account toAccount = accounts.get(numberToAccount);
        if (fromAccout == null) {
            throw new AccountNotFoundException("Conta numero " + numberFromAccount + " não cadastrada!");
        }
        if (toAccount == null) {
            throw new AccountNotFoundException("Conta numero " + numberToAccount + " não cadastrada!");
        }
        fromAccout.transfer(value, toAccount);
        System.out.println("Transferência realizada com sucesso");
    }

    private void checkCPF(String cpf) {
        if (cpf.length() < 11 || cpf.isBlank())
            throw new IllegalArgumentException("CPF inválido, tente novamente.");

        if (!cpf.matches("\\d{11}"))
            throw new IllegalArgumentException("CPF deve conter apenas números. Tente novamente");

    }
}
