public class BankAccount {
  Integer balance;

  BankAccount() {
    this.balance = 0;
  }

  public Integer deposit(Integer amountToDeposit) {
    this.balance += amountToDeposit;

    return this.balance;
  }
}
