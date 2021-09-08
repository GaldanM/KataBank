import java.util.HashMap;
import java.util.Map;

public class BankAccountRepositoryInMemory implements BankAccountRepository {
  Map<Integer, Integer> accountsBalances;

  public BankAccountRepositoryInMemory() {
    this.accountsBalances = new HashMap<>();
  }

  @Override
  public Integer deposit(Integer bankAccountId, Integer amountToDeposit) {
    return this.accountsBalances.merge(bankAccountId, amountToDeposit, Integer::sum);
  }

  @Override
  public Integer withdraw(Integer bankAccountId, Integer amountToWithdraw) {
    return this.accountsBalances.merge(bankAccountId, -amountToWithdraw, Integer::sum);
  }
}
