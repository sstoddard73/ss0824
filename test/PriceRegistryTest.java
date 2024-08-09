import java.util.NoSuchElementException;
import org.junit.*;

public class PriceRegistryTest {
    @Test
    public void PriceRegistry_Get() {
        PriceRegistry priceRegistry = PriceRegistry.INSTANCE;

        // A more robust PriceRegistry class would have a way to look up what's in it,
        // which would in turn allow this test not to have to hardcode specific tool types
        // that do or do not exist in it.  Ideally this test wouldn't depend on that
        // knowledge.
        Assert.assertThrows(
            NoSuchElementException.class,
            () -> priceRegistry.getPriceRulesForToolType("invalid tool type")
        );
        
        // When we get price rules back for a tool type, make sure the price rules are for that tool type.
        // Don't check any other aspects of the price rules object, because that would only further
        // tie the terms of this test to the contents of the price registry.
        Assert.assertEquals("Chainsaw", priceRegistry.getPriceRulesForToolType("Chainsaw").getToolType());
    }
}