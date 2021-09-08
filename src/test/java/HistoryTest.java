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
}
