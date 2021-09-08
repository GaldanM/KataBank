import java.util.List;

public class BankAccount {
  BankAccountRepository bankAccountRepository;
  OperationRepository operationRepository;

  BankAccount(BankAccountRepository bankAccountRepository, OperationRepository operationRepository) {
    this.bankAccountRepository = bankAccountRepository;
    this.operationRepository = operationRepository;
  }

  public Integer deposit(Integer amountToDeposit) {
    Integer balance = this.bankAccountRepository.deposit(amountToDeposit);

    this.operationRepository.create(Operation.OperationType.DEPOSIT, amountToDeposit, balance);

    return balance;
  }

  public Integer withdraw(Integer amountToWithdraw) {
    Integer balance = this.bankAccountRepository.withdraw(amountToWithdraw);;

    this.operationRepository.create(Operation.OperationType.WITHDRAW, amountToWithdraw, balance);

    return balance;
  }

  public List<Operation> checkHistory() {
    return this.operationRepository.get();
  }
}
