public interface BankAccountRepository {
  Integer deposit(Integer amountToDeposit);

  Integer withdraw(Integer amountToWithdraw);
}
