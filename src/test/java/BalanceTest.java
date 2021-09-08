import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BalanceTest {
  @Test
  void deposit() {
    BankAccount bankAccount = new BankAccount();
    Integer balance = bankAccount.deposit(1);

    assertThat(balance).isEqualTo(1);
  }
}


