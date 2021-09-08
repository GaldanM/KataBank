import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BalanceTest {
  @Test
  void deposit() {
    BankAccount bankAccount = new BankAccount(new BankAccountRepositoryInMemory());
    Integer balance = bankAccount.deposit(1);

    assertThat(balance).isEqualTo(1);
  }

  @Test
  void depositTwice() {
    BankAccount bankAccount = new BankAccount(new BankAccountRepositoryInMemory());

    bankAccount.deposit(2);
    Integer balance = bankAccount.deposit(2);

    assertThat(balance).isEqualTo(4);
  }

  @Test
  void withdraw() {
    BankAccount bankAccount = new BankAccount(new BankAccountRepositoryInMemory());

    Integer balance = bankAccount.withdraw(1);

    assertThat(balance).isEqualTo(-1);
  }
}


