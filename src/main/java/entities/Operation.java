package entities;

import java.util.Date;

public class Operation {
  public final OperationType type;
  public final Integer amount;
  public final Integer balanceAfterOperation;
  public final Date createdAt;

  public Operation(OperationType type, Integer amount, Integer balanceAfterOperation) {
    this.type = type;
    this.amount = amount;
    this.balanceAfterOperation = balanceAfterOperation;
    this.createdAt = new Date();
  }

  public enum OperationType {
    DEPOSIT,
    WITHDRAW
  }
}
