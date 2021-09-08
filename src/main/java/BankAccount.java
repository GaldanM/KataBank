import java.util.ArrayList;
import java.util.List;

public class BankAccount {
  Operation operation;
  BankAccountRepository bankAccountRepository;

  BankAccount(BankAccountRepository bankAccountRepository) {
    this.bankAccountRepository = bankAccountRepository;
    this.operation = null;
  }

  public Integer deposit(Integer amountToDeposit) {
    Integer balance = this.bankAccountRepository.deposit(amountToDeposit);

    this.operation = new Operation(Operation.OperationType.DEPOSIT, amountToDeposit, balance);

    return balance;
  }

  public Integer withdraw(Integer amountToWithdraw) {
    return this.bankAccountRepository.withdraw(amountToWithdraw);
  }

  public List<Operation> checkHistory() {
    ArrayList<Operation> operations = new ArrayList<>();

    if (this.operation != null) {
      operations.add(this.operation);
    }

    return operations;
  }
}
