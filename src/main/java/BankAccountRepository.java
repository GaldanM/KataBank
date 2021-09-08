public interface BankAccountRepository {
  Integer deposit(Integer bankAccountId, Integer amountToDeposit);

  Integer withdraw(Integer bankAccountId, Integer amountToWithdraw);
}
