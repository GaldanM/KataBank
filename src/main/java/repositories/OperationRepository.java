package repositories;

import entities.Operation;

import java.util.List;

public interface OperationRepository {
  void create(String bankAccountId, Operation.OperationType type, Integer amount, Integer balanceAfterOperation);

  List<Operation> get(String bankAccountId);
}
