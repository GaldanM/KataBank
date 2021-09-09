package entities;

public class BankAccount {
  public final String id;
  private Integer balance;

  public BankAccount(String id) {
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
