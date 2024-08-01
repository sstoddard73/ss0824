public class ToolRegistry
{
    // Singleton.

    private static ToolRegistry instance;
    private ToolRegistry() {}

    // (This is not thread-safe; add "synchronized" and double-locking if needed, but may be slower if not.)
    public static ToolRegistry getInstance() {
        if (instance == null) {
            instance = new ToolRegistry();
        }
        return instance;
    }

    // Data fields and constructors.

    static final private Tool []toolRegistry = {
        new Tool("CHNS", "Chainsaw", "Stihl"),
        new Tool("LADW", "Ladder", "Werner"),
        new Tool("JAKD", "Jackhammer", "DeWalt"),
        new Tool("JAKR", "Jackhammer", "Ridgid")
    };

    // Retrieve data.

    public Tool getToolByCode(String toolCode) {
        for (int i = 0; i < toolRegistry.length; i++) {
            if (toolCode == toolRegistry[i].getToolCode()) {
                return toolRegistry[i];
            }
        }
        throw new RuntimeException("no such tool code");
    }

    @Override
    public String toString() {
        return "";
    }
};