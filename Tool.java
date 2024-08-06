/**
 * Tool is a POJO containing data fields pertaining to a particular make and model of tool.
 * Specifically it keeps track of:
 *    - the tool code (identifier for the make and model)
 *    - the tool type (e.g., a hammer)
 *    - the tool brand
 * 
 * (Note:  In a more scalable, production-ready project, this would probably be a model
 * class representing an entry in a database and contain a database ID.  Probably the
 * type would be an ID for a type table; ditto for the brand.)
 */
class Tool {
    private String toolCode;
    private String toolType;
    private String toolBrand;

    /**
     * Construct a "blank" tool.
     */
    public Tool() {
        toolCode = "";
        toolType = "";
        toolBrand = "";
    }

    /**
     * Construct a tool with the given values.
     * @param toolCode string representing the tool code
     * @param toolType string representing the tool type
     * @param toolBrand string representing the tool brand
     */
    public Tool(String toolCode, String toolType, String toolBrand) {
        setToolCode(toolCode);
        setToolType(toolType);
        setToolBrand(toolBrand);
    }

    /**
     * Copy constructor.
     * @param tool
     */
    public Tool(Tool tool) {
        setToolCode(tool.getToolCode());
        setToolType(tool.getToolType());
        setToolBrand(tool.getToolBrand());
    }

    /**
     * Get the tool code.
     * @return the tool code string
     */
    public String getToolCode() {
        return toolCode;
    }

    /**
     * Set the tool code.
     * @param toolCode
     */
    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    /**
     * Get the tool type.
     * @return the tool type string
     */
    public String getToolType() {
        return toolType;
    }

    /**
     * Set the tool type.
     * @param toolType
     */
    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    /**
     * Get the tool brand.
     * @return the tool brand string
     */
    public String getToolBrand() {
        return toolBrand;
    }

    /**
     * Set the tool brand.
     * @param toolBrand
     */
    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    /**
     * Return a human-readable string representing the tool data.
     * @return human-readable, multi-line string
     */
    @Override
    public String toString() {
        String formatString = "               Tool: %s\n"
                            + "               Type: %s\n"
                            + "              Brand: %s\n";
        return formatString.formatted(toolCode, toolType, toolBrand);
    }
};