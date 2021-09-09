package repositories;

import entities.BankAccount;

public interface BankAccountRepository {
  BankAccount create(String bankAccountId);

  Integer deposit(String bankAccountId, Integer amountToDeposit);

  Integer withdraw(String bankAccountId, Integer amountToWithdraw);
}
