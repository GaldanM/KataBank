package services;

import entities.BankAccount;
import entities.Operation;
import repositories.BankAccountRepository;
import repositories.OperationRepository;

import java.util.List;

public class BankAccountService {
  private final BankAccountRepository bankAccountRepository;
  private final OperationRepository operationRepository;

  public BankAccountService(BankAccountRepository bankAccountRepository, OperationRepository operationRepository) {
    this.bankAccountRepository = bankAccountRepository;
    this.operationRepository = operationRepository;
  }

  public BankAccount createAccount(String bankAccountId) {
    return this.bankAccountRepository.create(bankAccountId);
  }

  public Integer deposit(String bankAccountId, Integer amountToDeposit) {
    Integer balance = this.bankAccountRepository.deposit(bankAccountId, amountToDeposit);

    this.operationRepository.create(bankAccountId, Operation.OperationType.DEPOSIT, amountToDeposit, balance);

    return balance;
  }

  public Integer withdraw(String bankAccountId, Integer amountToWithdraw) {
    Integer balance = this.bankAccountRepository.withdraw(bankAccountId, amountToWithdraw);

    this.operationRepository.create(bankAccountId, Operation.OperationType.WITHDRAW, amountToWithdraw, balance);

    return balance;
  }

  public List<Operation> checkHistory(String bankAccountId) {
    return this.operationRepository.get(bankAccountId);
  }
}
