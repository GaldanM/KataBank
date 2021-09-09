package repositories;

import entities.BankAccount;

import java.util.HashMap;
import java.util.Map;

public class BankAccountRepositoryInMemory implements BankAccountRepository {
  private final Map<String, BankAccount> bankAccounts;

  public BankAccountRepositoryInMemory() {
    this.bankAccounts = new HashMap<>();
  }

  @Override
  public BankAccount create(String bankAccountId) {
    BankAccount newBankAccount = new BankAccount(bankAccountId);

    this.bankAccounts.put(bankAccountId, newBankAccount);

    return newBankAccount;
  }

  @Override
  public Integer deposit(String bankAccountId, Integer amountToDeposit) {
    BankAccount bankAccountToDeposit = this.bankAccounts.get(bankAccountId);

    bankAccountToDeposit.setBalance(bankAccountToDeposit.getBalance() + amountToDeposit);
    this.bankAccounts.put(bankAccountId, bankAccountToDeposit);

    return bankAccountToDeposit.getBalance();
  }

  @Override
  public Integer withdraw(String bankAccountId, Integer amountToWithdraw) {
    BankAccount bankAccountToDeposit = this.bankAccounts.get(bankAccountId);

    bankAccountToDeposit.setBalance(bankAccountToDeposit.getBalance() - amountToWithdraw);
    this.bankAccounts.put(bankAccountId, bankAccountToDeposit);

    return bankAccountToDeposit.getBalance();
  }
}
