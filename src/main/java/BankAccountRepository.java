public interface BankAccountRepository {
  Integer deposit(Integer bankAccountId, Integer amountToDeposit);

  Integer withdraw(Integer amountToWithdraw);
}
