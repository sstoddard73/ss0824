import java.util.NoSuchElementException;
import org.junit.*;

public class ToolRegistryTest {
    @Test
    public void ToolRegistry_Get() {
        ToolRegistry toolRegistry = ToolRegistry.INSTANCE;

        // A more robust ToolRegistry class would have a way to look up what's in it,
        // which would in turn allow this test not to have to hardcode specific tool codes
        // that do or do not exist in it.  Ideally this test wouldn't depend on that
        // knowledge.
        Assert.assertThrows(
            NoSuchElementException.class,
            () -> toolRegistry.getToolByCode("INVALID CODE")
        );
        
        // When we get a tool object, make sure it is for the tool code we asked for.
        // Don't check any other aspects of the tool object, because that would only further
        // tie the terms of this test to the contents of the tool registry.
        Assert.assertEquals("CHNS", toolRegistry.getToolByCode("CHNS").getToolCode());
    }
}