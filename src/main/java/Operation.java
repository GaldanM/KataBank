import java.util.Date;

public class Operation {
  final OperationType type;
  final Integer amount;
  final Integer balanceAfterOperation;
  final Date createdAt;

  Operation(OperationType type, Integer amount, Integer balanceAfterOperation) {
    this.type = type;
    this.amount = amount;
    this.balanceAfterOperation = balanceAfterOperation;
    this.createdAt = new Date();
  }

  public enum OperationType {
    DEPOSIT,
    WITHDRAW;
  }
}
