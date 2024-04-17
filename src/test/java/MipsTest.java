import dev.binarybrigade.mipsemulator.model.MemoryRow;
import dev.binarybrigade.mipsemulator.model.RegisterRow;
import org.junit.jupiter.api.Test;

public class MipsTest {
    @Test
    void memoryNumberConversions() {
        MemoryRow memoryRow = new MemoryRow(16, 16);
        System.out.println(memoryRow.getAddressAsBinary());
        System.out.println(memoryRow.getAddressAsDecimal());
        System.out.println(memoryRow.getAddressAsHex());
        System.out.println(memoryRow.getValueAsBinary());
        System.out.println(memoryRow.getValueAsDecimal());
        System.out.println(memoryRow.getValueAsHex());
    }
    @Test
    void registerNumberConversions() {
        RegisterRow registerRow = new RegisterRow("$t0", 7);
        System.out.println(registerRow.getValueAsBinary());
        System.out.println(registerRow.getValueAsDecimal());
        System.out.println(registerRow.getValueAsHex());
    }
}
