class Tool
{
    // Data fields.

    private String toolCode;
    private String toolType;
    private String toolBrand;

    // Constructors.

    public Tool() {
        toolCode = "";
        toolType = "";
        toolBrand = "";
    }

    public Tool(String toolCode, String toolType, String toolBrand) {
        setToolCode(toolCode);
        setToolType(toolType);
        setToolBrand(toolBrand);
    }

    // Getters and setters.

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    // Serialize/stringify methods.

    @Override
    public String toString() {
        String formatString = "               Tool: %s\n"
                            + "               Type: %s\n"
                            + "              Brand: %s\n";
        return formatString.formatted(toolCode, toolType, toolBrand);
    }
};