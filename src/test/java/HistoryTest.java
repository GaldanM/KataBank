import entities.BankAccount;
import entities.Operation;
import org.junit.jupiter.api.Test;
import repositories.BankAccountRepositoryInMemory;
import repositories.OperationRepositoryInMemory;
import services.BankAccountService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HistoryTest {
  @Test
  void emptyHistory() {
    BankAccountService bankAccountService = new BankAccountService(new BankAccountRepositoryInMemory(), new OperationRepositoryInMemory());
    BankAccount bankAccount = bankAccountService.createAccount("account_1");

    List<Operation> operations = bankAccountService.checkHistory(bankAccount.id);

    assertThat(operations).hasSize(0);
  }

  @Test
  void oneDepositHistory() {
    BankAccountService bankAccountService = new BankAccountService(new BankAccountRepositoryInMemory(), new OperationRepositoryInMemory());
    BankAccount bankAccount = bankAccountService.createAccount("account_1");

    bankAccountService.deposit(bankAccount.id, 1);
    List<Operation> operations = bankAccountService.checkHistory(bankAccount.id);

    assertThat(operations).hasSize(1);
    Operation operationToCheck = operations.get(0);
    assertThat(operationToCheck.type).isEqualTo(Operation.OperationType.DEPOSIT);
    assertThat(operationToCheck.amount).isEqualTo(1);
    assertThat(operationToCheck.balanceAfterOperation).isEqualTo(1);
  }

  @Test
  void twoDepositsHistory() {
    BankAccountService bankAccountService = new BankAccountService(new BankAccountRepositoryInMemory(), new OperationRepositoryInMemory());
    BankAccount bankAccount = bankAccountService.createAccount("account_1");

    bankAccountService.deposit(bankAccount.id, 1);
    bankAccountService.deposit(bankAccount.id, 1);
    List<Operation> operations = bankAccountService.checkHistory(bankAccount.id);

    assertThat(operations).hasSize(2);
    boolean allMatch = operations.stream().allMatch(operation -> Operation.OperationType.DEPOSIT.equals(operation.type) && operation.amount.equals(1));
    assertThat(allMatch).isTrue();
    assertThat(operations.get(0).balanceAfterOperation).isEqualTo(1);
    assertThat(operations.get(1).balanceAfterOperation).isEqualTo(2);
  }

  @Test
  void withdrawalHistory() {
    BankAccountService bankAccountService = new BankAccountService(new BankAccountRepositoryInMemory(), new OperationRepositoryInMemory());
    BankAccount bankAccount = bankAccountService.createAccount("account_1");

    bankAccountService.withdraw(bankAccount.id, 1);
    List<Operation> operations = bankAccountService.checkHistory(bankAccount.id);

    assertThat(operations).hasSize(1);
    Operation operationToCheck = operations.get(0);
    assertThat(operationToCheck.type).isEqualTo(Operation.OperationType.WITHDRAW);
    assertThat(operationToCheck.amount).isEqualTo(1);
    assertThat(operationToCheck.balanceAfterOperation).isEqualTo(-1);
  }

  @Test
  void twoAccountsHistory() {
    BankAccountService bankAccountService = new BankAccountService(new BankAccountRepositoryInMemory(), new OperationRepositoryInMemory());
    BankAccount firstBankAccount = bankAccountService.createAccount("account_1");
    BankAccount secondBankAccount = bankAccountService.createAccount("account_2");

    bankAccountService.withdraw(firstBankAccount.id, 1);
    bankAccountService.withdraw(firstBankAccount.id, 1);
    bankAccountService.deposit(secondBankAccount.id, 2);
    bankAccountService.deposit(secondBankAccount.id, 2);

    List<Operation> operationsFirstBankAccount = bankAccountService.checkHistory(firstBankAccount.id);
    List<Operation> operationsSecondBankAccount = bankAccountService.checkHistory(secondBankAccount.id);

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
}
