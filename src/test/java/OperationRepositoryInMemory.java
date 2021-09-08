import java.util.ArrayList;
import java.util.List;

public class OperationRepositoryInMemory implements OperationRepository {
  ArrayList<Operation> operations;

  OperationRepositoryInMemory() {
    this.operations = new ArrayList<>();
  }

  @Override
  public void create(Operation.OperationType type, Integer amount, Integer balanceAfterOperation) {
    Operation newOperation = new Operation(type, amount, balanceAfterOperation);

    this.operations.add(newOperation);
  }

  @Override
  public List<Operation> get() {
    return this.operations;
  }
}
