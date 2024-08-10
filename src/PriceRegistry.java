import java.util.NoSuchElementException;

/**
 * Singleton containing the price registry.
 * The price registry is an array of all the different tool types that may be rented.
 * Each tool type has pricing rules associated with it.  See the PriceRules class for more information.
 * To use this registry, create a reference to the singleton, then call getPriceRulesForToolType(toolType)
 * to get a PriceRules object for the specified type.  A NoSuchElementException will be thrown if
 * there is no such tool type.
 * 
 * Further Improvements:
 * 
 * In production code, you wouldn't want a hardcoded list.  Instead you'd use a database and probably a
 * caching layer and replace this registry class with data models supporting full CRUD operations.
 *
 * When using a database, it would be natural and correct to use a numerical ID field as the primary key,
 * rather than, as here, the human-readable string name of the tool type.  Even without using a database
 * but keeping this registry architecture, using an int or enum as the primary key would be an improvement,
 * but for the purpose of this exercise, I chose to keep the architecture simple and more navigable and
 * just use the type name string.
 */
public enum PriceRegistry {
    INSTANCE;

    static final private PriceRules []priceRegistry = {
        new PriceRules("Chainsaw", 199, true, true, false),
        new PriceRules("Ladder", 149, true, false, true),
        new PriceRules("Jackhammer", 299, true, false, false)
    };

    /**
     * Get the PriceRules object for the specified tool type.
     * If the given tool type is not found, throw a NoSuchElementException error.
     * @param toolType
     * @return PriceRules
     * @throws NoSuchElementException
     */
    public PriceRules getPriceRulesForToolType(String toolType) {
        // Iterate through the array until we find the tool type we are looking for.
        for (int i = 0; i < priceRegistry.length; i++) {
            if (toolType == priceRegistry[i].getToolType()) {
                return new PriceRules(priceRegistry[i]);
            }
        }

        // We didn't find the tool type in the array, so throw an exception.
        throw new NoSuchElementException("no such tool type");
    }
};