public class BankAccount {
  BankAccountRepository bankAccountRepository;

  BankAccount(BankAccountRepository bankAccountRepository) {
    this.bankAccountRepository = bankAccountRepository;
  }

  public Integer deposit(Integer amountToDeposit) {
    return this.bankAccountRepository.deposit(amountToDeposit);
  }
}
