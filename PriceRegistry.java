public class PriceRegistry
{
    // Singleton.

    private static PriceRegistry instance;

    // (This is not thread-safe; add "synchronized" and double-locking if needed, but may be slower if not.)
    public static PriceRegistry getInstance() {
        if (instance == null) {
            instance = new PriceRegistry();
        }
        return instance;
    }

    // Data fields and constructors.

    static private PriceRules []priceRegistry;

    private PriceRegistry() {
        PriceRegistry.priceRegistry = new PriceRules[] {
            new PriceRules("Chainsaw", 199, true, true, false),
            new PriceRules("Ladder", 149, true, false, true),
            new PriceRules("Jackhammer", 299, true, false, false)
        };
    }

    @Override
    public String toString() {
        return "";
    }
};