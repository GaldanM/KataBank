public class BankAccountRepositoryInMemory implements BankAccountRepository {
  Integer balance;

  public BankAccountRepositoryInMemory() {
    this.balance = 0;
  }

  @Override
  public Integer deposit(Integer amountToDeposit) {
    this.balance += amountToDeposit;

    return this.balance;
  }
}
