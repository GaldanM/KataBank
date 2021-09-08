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
}
