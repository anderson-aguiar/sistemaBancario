package entities;

import execptions.InvalidTransactionException;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int number;
    private Client client;
    private double balance;
    private List<Transaction> statements = new ArrayList<>();

    public Account(int number, Client client, double balance) {
        this.number = number;
        this.client = client;
        this.balance = balance;
        client.addAccount(this);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double value) {
        if (value > 0) {
            this.balance += value;
            this.statements.add(new Transaction(TransactionType.DEPOSIT, value, 0, this.number));
            System.out.println("Deposito feito com sucesso!");
        } else {
            throw new InvalidTransactionException("Valor precisa ser positivo");
        }
    }

    public void withdraw(double value) {
        if (value <= this.balance && value > 0) {
            this.balance -= value;
            this.statements.add(new Transaction(TransactionType.WITHDRAW, value, this.number, 0));
            System.out.println("Saque feito com sucesso!");
        } else if (value <= 0) {
            throw new InvalidTransactionException("Informar um valor positivo");
        } else {
            throw new InvalidTransactionException("Saldo insuficiente!");
        }
    }

    public void transfer(double value, Account toAccount) {
        if (value <= 0) {
            throw new InvalidTransactionException("Informar um valor positivo");
        }
        if (value > this.balance) {
            throw new InvalidTransactionException("Saldo insuficiente!");
        }

        this.balance -= value;
        toAccount.balance += value;

        this.statements.add(new Transaction(TransactionType.TRANSFER, value, this.number, toAccount.getNumber()));

        toAccount.statements.add(new Transaction(TransactionType.TRANSFER, value, this.number, toAccount.getNumber()));

        System.out.println("Transferência feita com sucesso!");
    }

    public void printStatement() {
        statements.forEach(System.out::println);
    }

}
