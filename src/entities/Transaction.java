package entities;

import java.time.LocalDateTime;

public class Transaction {
    private final TransactionType transactionType;
    private final double value;
    private final LocalDateTime dateTime;
    private final int fromAccount;
    private final int toAccount;

    public Transaction(TransactionType transactionType, double value, int fromAccount, int toAccount) {
        this.transactionType = transactionType;
        this.value = value;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append(dateTime).append(" | ");
        stb.append(transactionType.getDescription()).append(" | ");
        stb.append("Valor: R$ ").append(value);
        if (this.fromAccount != 0) {
            stb.append(" | Origem: ").append(fromAccount);
        }
        if (this.toAccount != 0) {
            stb.append(" | Destino: ").append(toAccount);
        }
        stb.append(".");
        return stb.toString();
    }
}
