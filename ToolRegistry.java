import java.util.NoSuchElementException;

/**
 * Singleton containing the tool registry.
 * The tool registry is an array of all the different tools that may be rented.
 * To use this registry, create a reference to the singleton, then call getToolByCode(toolCode)
 * to get a tool in it; a NoSuchElementException will be thrown if there is no such tool.
 * 
 * (NOTE:  In production code, you'd probably want some more methods in here, such as to
 * iterate through the list, or to do lookups on other fields.  Also the list would probably
 * be in a database rather than hardcoded here as an array.)
 */
public enum ToolRegistry {
    INSTANCE;

    final private Tool []toolRegistry = {
        new Tool("CHNS", "Chainsaw", "Stihl"),
        new Tool("LADW", "Ladder", "Werner"),
        new Tool("JAKD", "Jackhammer", "DeWalt"),
        new Tool("JAKR", "Jackhammer", "Ridgid")
    };

    /**
     * Get the Tool object for the specified tool code.
     * If the given tool code is not found, throw a NoSuchElementException error.
     * @param toolCode
     * @return a Tool object
     * @throws NoSuchElementException
     */
    public Tool getToolByCode(String toolCode) {
        // Iterate through the array until we find the tool code we are looking for.
        for (int i = 0; i < toolRegistry.length; i++) {
            if (toolCode == toolRegistry[i].getToolCode()) {
                return new Tool(toolRegistry[i]);
            }
        }

        // We didn't find the tool code in the array, so throw an exception.
        throw new NoSuchElementException("no such tool code");
    }
};