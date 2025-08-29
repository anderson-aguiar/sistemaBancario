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

        }else{
            throw new InvalidTransactionException("Valor precisa ser positivo");
        }
    }

}
