public class PriceRegistry
{
    // Singleton.

    private static PriceRegistry instance;
    private PriceRegistry() {}

    // (This is not thread-safe; add "synchronized" and double-locking if needed, but may be slower if not.)
    public static PriceRegistry getInstance() {
        if (instance == null) {
            instance = new PriceRegistry();
        }
        return instance;
    }

    // Data fields and constructors.

    static final private PriceRules []priceRegistry = {
        new PriceRules("Chainsaw", 199, true, true, false),
        new PriceRules("Ladder", 149, true, false, true),
        new PriceRules("Jackhammer", 299, true, false, false)
    };

    // Retrieve data.

    public PriceRules getPriceRulesForToolType(String toolType) {
        for (int i = 0; i < priceRegistry.length; i++) {
            if (toolType == priceRegistry[i].getToolType()) {
                return priceRegistry[i];
            }
        }
        throw new RuntimeException("no such tool type");
    }

    @Override
    public String toString() {
        return "";
    }
};