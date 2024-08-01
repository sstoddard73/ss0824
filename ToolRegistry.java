public class ToolRegistry
{
    // Singleton.

    private static ToolRegistry instance;

    // (This is not thread-safe; add "synchronized" and double-locking if needed, but may be slower if not.)
    public static ToolRegistry getInstance() {
        if (instance == null) {
            instance = new ToolRegistry();
        }
        return instance;
    }

    // Data fields and constructors.

    static private Tool []toolRegistry;

    private ToolRegistry() {
        ToolRegistry.toolRegistry = new Tool[] {
            new Tool("CHNS", "Chainsaw", "Stihl"),
            new Tool("LADW", "Ladder", "Werner"),
            new Tool("JAKD", "Jackhammer", "DeWalt"),
            new Tool("JAKR", "Jackhammer", "Ridgid")
        };
    }

    @Override
    public String toString() {
        return "";
    }
};