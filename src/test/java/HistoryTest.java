import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HistoryTest {
  @Test
  void emptyHistory() {
    BankAccount bankAccount = createBankAccount();

    List<Operation> operations = bankAccount.checkHistory();

    assertThat(operations).hasSize(0);
  }

  @Test
  void oneDepositHistory() {
    BankAccount bankAccount = createBankAccount();

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
    BankAccount bankAccount = createBankAccount();

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
    BankAccount bankAccount = createBankAccount();

    bankAccount.withdraw(1);
    List<Operation> operations = bankAccount.checkHistory();

    assertThat(operations).hasSize(1);
    Operation operationToCheck = operations.get(0);
    assertThat(operationToCheck.type).isEqualTo(Operation.OperationType.WITHDRAW);
    assertThat(operationToCheck.amount).isEqualTo(1);
    assertThat(operationToCheck.balanceAfterOperation).isEqualTo(-1);
  }

  @Test
  void twoAccountsHistory() {
    BankAccountRepository bankAccountRepository = new BankAccountRepositoryInMemory();
    OperationRepositoryInMemory operationRepository = new OperationRepositoryInMemory();

    BankAccount firstBankAccount = new BankAccount(bankAccountRepository, operationRepository, 1);
    BankAccount secondBankAccount = new BankAccount(bankAccountRepository, operationRepository, 2);

    firstBankAccount.withdraw(1);
    firstBankAccount.withdraw(1);
    secondBankAccount.deposit(2);
    secondBankAccount.deposit(2);

    List<Operation> operationsFirstBankAccount = firstBankAccount.checkHistory();
    List<Operation> operationsSecondBankAccount = secondBankAccount.checkHistory();

    assertThat(operationsFirstBankAccount).hasSize(2);
    boolean allMatch = operationsFirstBankAccount.stream().allMatch(operation -> Operation.OperationType.WITHDRAW.equals(operation.type) && operation.amount.equals(1));
    assertThat(allMatch).isTrue();
    assertThat(operationsFirstBankAccount.get(0).balanceAfterOperation).isEqualTo(-1);
    assertThat(operationsFirstBankAccount.get(1).balanceAfterOperation).isEqualTo(-2);

    assertThat(operationsSecondBankAccount).hasSize(2);
    allMatch = operationsSecondBankAccount.stream().allMatch(operation -> Operation.OperationType.DEPOSIT.equals(operation.type) && operation.amount.equals(2));
    assertThat(allMatch).isTrue();
    assertThat(operationsSecondBankAccount.get(0).balanceAfterOperation).isEqualTo(2);
    assertThat(operationsSecondBankAccount.get(1).balanceAfterOperation).isEqualTo(4);
  }

  private BankAccount createBankAccount() {
    return new BankAccount(new BankAccountRepositoryInMemory(), new OperationRepositoryInMemory(), 1);
  }
}
