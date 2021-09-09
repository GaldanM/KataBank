public class BankAccount {
  final String id;
  private Integer balance;

  BankAccount(String id) {
    this.id = id;
    this.balance = 0;
  }

  public Integer getBalance() {
    return balance;
  }

  public void setBalance(Integer newBalance) {
    this.balance = newBalance;
  }
}
