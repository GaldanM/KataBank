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

  private BankAccount createBankAccount() {
    return new BankAccount(new BankAccountRepositoryInMemory(), new OperationRepositoryInMemory());
  }
}


