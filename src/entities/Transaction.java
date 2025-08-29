package entities;

import java.time.LocalDateTime;

public class Transaction {
    private TransactionType transactionType;
    private double value;
    private LocalDateTime dateTime;
    private int fromAccount, toAccount;

}
