import java.util.ArrayList;
import java.util.List;

public class BankAccount {
  ArrayList<Operation> operations;
  BankAccountRepository bankAccountRepository;

  BankAccount(BankAccountRepository bankAccountRepository) {
    this.bankAccountRepository = bankAccountRepository;
    this.operations = new ArrayList<>();
  }

  public Integer deposit(Integer amountToDeposit) {
    Integer balance = this.bankAccountRepository.deposit(amountToDeposit);

    this.operations.add(new Operation(Operation.OperationType.DEPOSIT, amountToDeposit, balance));

    return balance;
  }

  public Integer withdraw(Integer amountToWithdraw) {
    Integer balance = this.bankAccountRepository.withdraw(amountToWithdraw);;

    this.operations.add(new Operation(Operation.OperationType.WITHDRAW, amountToWithdraw, balance));

    return balance;
  }

  public List<Operation> checkHistory() {
    return this.operations;
  }
}
