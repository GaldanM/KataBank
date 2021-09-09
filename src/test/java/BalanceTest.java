import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BalanceTest {
  @Test
  void deposit() {
    BankAccountService bankAccountService = new BankAccountService(new BankAccountRepositoryInMemory(), new OperationRepositoryInMemory());
    BankAccount bankAccount = bankAccountService.createAccount("account_1");

    Integer newBalance = bankAccountService.deposit(bankAccount.id, 1);

    assertThat(newBalance).isEqualTo(1);
  }

  @Test
  void depositTwice() {
    BankAccountService bankAccountService = new BankAccountService(new BankAccountRepositoryInMemory(), new OperationRepositoryInMemory());
    BankAccount bankAccount = bankAccountService.createAccount("account_1");

    bankAccountService.deposit(bankAccount.id, 1);
    Integer newBalance = bankAccountService.deposit(bankAccount.id, 1);

    assertThat(newBalance).isEqualTo(2);
  }

  @Test
  void withdraw() {
    BankAccountService bankAccountService = new BankAccountService(new BankAccountRepositoryInMemory(), new OperationRepositoryInMemory());
    BankAccount bankAccount = bankAccountService.createAccount("account_1");

    Integer newBalance = bankAccountService.withdraw(bankAccount.id, 1);

    assertThat(newBalance).isEqualTo(-1);
  }

  @Test
  void withdrawTwice() {
    BankAccountService bankAccountService = new BankAccountService(new BankAccountRepositoryInMemory(), new OperationRepositoryInMemory());
    BankAccount bankAccount = bankAccountService.createAccount("account_1");

    bankAccountService.withdraw(bankAccount.id, 1);
    Integer newBalance = bankAccountService.withdraw(bankAccount.id, 1);

    assertThat(newBalance).isEqualTo(-2);
  }

  @Test
  void depositTwiceOnTwoAccounts() {
    BankAccountService bankAccountService = new BankAccountService(new BankAccountRepositoryInMemory(), new OperationRepositoryInMemory());
    BankAccount firstBankAccount = bankAccountService.createAccount("account_1");
    BankAccount secondBankAccount = bankAccountService.createAccount("account_2");

    bankAccountService.deposit(firstBankAccount.id, 1);
    Integer balanceFirstBankAccount = bankAccountService.deposit(firstBankAccount.id, 1);
    bankAccountService.deposit(secondBankAccount.id, 2);
    Integer balanceSecondBankAccount = bankAccountService.deposit(secondBankAccount.id, 2);

    assertThat(balanceFirstBankAccount).isEqualTo(2);
    assertThat(balanceSecondBankAccount).isEqualTo(4);
  }

  @Test
  void withdrawTwiceOnTwoAccounts() {
    BankAccountService bankAccountService = new BankAccountService(new BankAccountRepositoryInMemory(), new OperationRepositoryInMemory());
    BankAccount firstBankAccount = bankAccountService.createAccount("account_1");
    BankAccount secondBankAccount = bankAccountService.createAccount("account_2");

    bankAccountService.withdraw(firstBankAccount.id, 1);
    Integer balanceFirstBankAccount = bankAccountService.withdraw(firstBankAccount.id, 1);
    bankAccountService.withdraw(secondBankAccount.id, 2);
    Integer balanceSecondBankAccount = bankAccountService.withdraw(secondBankAccount.id, 2);

    assertThat(balanceFirstBankAccount).isEqualTo(-2);
    assertThat(balanceSecondBankAccount).isEqualTo(-4);
  }
}


