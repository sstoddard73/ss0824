public enum ToolRegistry
{
    INSTANCE;

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
};