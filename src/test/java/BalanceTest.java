import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BalanceTest {
  @Test
  void deposit() {
    BankAccount bankAccount = createBankAccount();
    Integer balance = bankAccount.deposit(1);

    assertThat(balance).isEqualTo(1);
  }

  @Test
  void depositTwice() {
    BankAccount bankAccount = createBankAccount();

    bankAccount.deposit(2);
    Integer balance = bankAccount.deposit(2);

    assertThat(balance).isEqualTo(4);
  }

  @Test
  void withdraw() {
    BankAccount bankAccount = createBankAccount();

    Integer balance = bankAccount.withdraw(1);

    assertThat(balance).isEqualTo(-1);
  }

  @Test
  void withdrawTwice() {
    BankAccount bankAccount = createBankAccount();

    bankAccount.withdraw(2);
    Integer balance = bankAccount.withdraw(2);

    assertThat(balance).isEqualTo(-4);
  }

  @Test
  void depositTwiceOnTwoAccounts() {
    BankAccountRepository bankAccountRepository = new BankAccountRepositoryInMemory();
    OperationRepositoryInMemory operationRepository = new OperationRepositoryInMemory();

    BankAccount firstBankAccount = new BankAccount(bankAccountRepository, operationRepository, 1);
    BankAccount secondBankAccount = new BankAccount(bankAccountRepository, operationRepository, 2);

    firstBankAccount.deposit(1);
    Integer balanceFirstBankAccount = firstBankAccount.deposit(1);
    secondBankAccount.deposit(2);
    Integer balanceSecondBankAccount = secondBankAccount.deposit(2);

    assertThat(balanceFirstBankAccount).isEqualTo(2);
    assertThat(balanceSecondBankAccount).isEqualTo(4);
  }

  private BankAccount createBankAccount() {
    return new BankAccount(new BankAccountRepositoryInMemory(), new OperationRepositoryInMemory(), 1);
  }
}


