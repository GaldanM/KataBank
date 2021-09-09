package repositories;

import entities.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationRepositoryInMemory implements OperationRepository {
  private final Map<String, List<Operation>> accountsOperations;

  public OperationRepositoryInMemory() {
    this.accountsOperations = new HashMap<>();
  }

  @Override
  public void create(
      String bankAccountId,
      Operation.OperationType type,
      Integer amount,
      Integer balanceAfterOperation
  ) {
    Operation newOperation = new Operation(type, amount, balanceAfterOperation);

    this.accountsOperations.computeIfAbsent(bankAccountId, k -> new ArrayList<>()).add(newOperation);
  }

  @Override
  public List<Operation> get(String bankAccountId) {
    List<Operation> operations = this.accountsOperations.get(bankAccountId);

    if (operations == null) {
      return new ArrayList<>();
    }

    return this.accountsOperations.get(bankAccountId);
  }
}
