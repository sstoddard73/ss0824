public enum PriceRegistry
{
    INSTANCE;

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
                return new PriceRules(priceRegistry[i]);
            }
        }
        throw new RuntimeException("no such tool type");
    }
};