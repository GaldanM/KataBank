import java.util.List;

public interface OperationRepository {
  void create(Integer bankAccountId, Operation.OperationType type, Integer amount, Integer balanceAfterOperation);

  List<Operation> get(Integer bankAccountId);
}
