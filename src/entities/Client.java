package entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Client {
    private String name;
    private final String cpf;
    private Set<Account> accounts = new HashSet<>();

    public Client(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public Set<Account> getAccounts() {
        return Collections.unmodifiableSet(accounts);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(cpf, client.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }

    @Override
    public String toString() {
        return name + " (CPF: " + cpf + ")";
    }
}
