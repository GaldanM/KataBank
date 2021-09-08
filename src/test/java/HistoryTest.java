import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HistoryTest {
  @Test
  void emptyHistory() {
    BankAccount bankAccount = new BankAccount(new BankAccountRepositoryInMemory());

    List<Operation> operations = bankAccount.checkHistory();

    assertThat(operations).hasSize(0);
  }

  @Test
  void oneDepositHistory() {
    BankAccount bankAccount = new BankAccount(new BankAccountRepositoryInMemory());

    bankAccount.deposit(1);
    List<Operation> operations = bankAccount.checkHistory();

    assertThat(operations).hasSize(1);
    Operation operationToCheck = operations.get(0);
    assertThat(operationToCheck.type).isEqualTo(Operation.OperationType.DEPOSIT);
    assertThat(operationToCheck.amount).isEqualTo(1);
    assertThat(operationToCheck.balanceAfterOperation).isEqualTo(1);
  }

  @Test
  void twoDepositsHistory() {
    BankAccount bankAccount = new BankAccount(new BankAccountRepositoryInMemory());

    bankAccount.deposit(1);
    bankAccount.deposit(1);
    List<Operation> operations = bankAccount.checkHistory();

    assertThat(operations).hasSize(2);
    boolean allMatch = operations.stream().allMatch(operation -> Operation.OperationType.DEPOSIT.equals(operation.type) && operation.amount.equals(1));
    assertThat(allMatch).isTrue();
    assertThat(operations.get(0).balanceAfterOperation).isEqualTo(1);
    assertThat(operations.get(1).balanceAfterOperation).isEqualTo(2);
  }

  @Test
  void withdrawalHistory() {
    BankAccount bankAccount = new BankAccount(new BankAccountRepositoryInMemory());

    bankAccount.withdraw(1);
    List<Operation> operations = bankAccount.checkHistory();

    assertThat(operations).hasSize(1);
    Operation operationToCheck = operations.get(0);
    assertThat(operationToCheck.type).isEqualTo(Operation.OperationType.WITHDRAW);
    assertThat(operationToCheck.amount).isEqualTo(1);
    assertThat(operationToCheck.balanceAfterOperation).isEqualTo(-1);
  }
}
