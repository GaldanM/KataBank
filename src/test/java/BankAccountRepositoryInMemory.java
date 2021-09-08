import java.util.HashMap;
import java.util.Map;

public class BankAccountRepositoryInMemory implements BankAccountRepository {
  Map<Integer, Integer> accountsBalances;
  Integer balance;

  public BankAccountRepositoryInMemory() {
    this.accountsBalances = new HashMap<>();
    this.balance = 0;
  }

  @Override
  public Integer deposit(Integer bankAccountId, Integer amountToDeposit) {
    return this.accountsBalances.merge(bankAccountId, amountToDeposit, Integer::sum);
  }

  @Override
  public Integer withdraw(Integer amountToWithdraw) {
    this.balance -= amountToWithdraw;

    return this.balance;
  }
}
