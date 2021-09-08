import java.util.List;

public class BankAccount {
  BankAccountRepository bankAccountRepository;
  OperationRepository operationRepository;
  Integer id;

  BankAccount(BankAccountRepository bankAccountRepository, OperationRepository operationRepository, Integer id) {
    this.bankAccountRepository = bankAccountRepository;
    this.operationRepository = operationRepository;
    this.id = id;
  }

  public Integer deposit(Integer amountToDeposit) {
    Integer balance = this.bankAccountRepository.deposit(this.id, amountToDeposit);

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
