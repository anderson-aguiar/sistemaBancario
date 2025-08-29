package entities;

public enum TransactionType {
    DEPOSIT("Depósito"),
    WITHDRAW("Saque"),
    TRANSFER("Tranferência");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
