import java.util.List;

public interface OperationRepository {
  void create(Operation.OperationType type, Integer amount, Integer balanceAfterOperation);

  List<Operation> get();
}
