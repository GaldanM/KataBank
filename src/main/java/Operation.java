import java.util.Date;

public class Operation {
  OperationType type;
  Integer amount;
  Integer balanceAfterOperation;
  Date createdAt;

  Operation(OperationType type, Integer amount, Integer balanceAfterOperation) {
    this.type = type;
    this.amount = amount;
    this.balanceAfterOperation = balanceAfterOperation;
    this.createdAt = new Date();
  }

  public enum OperationType {
    DEPOSIT
  }
}
