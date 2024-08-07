import java.time.LocalDate;

/**
 * CheckoutRequest contains the data for a checkout request.
 * Additionally, it can calculate the due date for you, given that it already contains
 * the checkout date and the number of rental days.
 */
class CheckoutRequest {
    private String toolCode;
    private LocalDate checkoutDate;
    private int numDays;
    private int discountPercentage;

    /**
     * Construct a "blank" checkout request.
     */
    public CheckoutRequest() {
        toolCode = "";
        checkoutDate = LocalDate.now();
        numDays = 0;
        discountPercentage = 0;
    }

    /**
     * Construct a checkout request with the given values.
     * @param toolCode string representing the tool code
     * @param checkoutDate LocalDate object representing the checkout date
     * @param numDays the number of days to rent the tool for (at least 1)
     * @param discountPercentage the percentage discount applied to the total charge (0-100)
     */
    public CheckoutRequest(String toolCode, LocalDate checkoutDate, int numDays, int discountPercentage) {
        setToolCode(toolCode);
        setCheckoutDate(checkoutDate);
        setNumDays(numDays);
        setDiscountPercentage(discountPercentage);
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
     * Get the checkout date.
     * @return the checkout date
     */
    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    /**
     * Set the checkout date.
     * @param checkoutDate LocalDate object
     */
    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    /**
     * Get the number of rental days requested.
     * @return the number of rental days
     */
    public int getNumDays() {
        return numDays;
    }

    /**
     * Set the number of rental days.
     * @param numDays - must be a positive integer
     */
    public void setNumDays(int numDays) {
        if (numDays < 1) {
            throw new IllegalArgumentException("numDays cannot be negative");
        }
        this.numDays = numDays;
    }

    /**
     * Get the discount percentage.
     * @return the discount percentage as an integer between 0 and 100
     */
    public int getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * Set the discount percentage.
     * @param discountPercentage - integer between 0 and 100
     */
    public void setDiscountPercentage(int discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("discountPercentage must be between 0 and 100");
        }
        this.discountPercentage = discountPercentage;
    }

    /**
     * Get the due date of this checkout request, as calculated from the checkout date and
     * number of rental days.
     * @return the due date as a LocalDate object
     */
    public LocalDate getDueDate() {
        return checkoutDate.plusDays(numDays);
    }

    /**
     * Return a human-readable string representing the checkout request.
     * @return human-readable, multi-line string
     */
    @Override
    public String toString() {
        String formatString = "               Tool: %s\n"
                            + "      Checkout Date: %s\n"
                            + "     Number of Days: %d\n"
                            + "Discount Percentage: %d\n";
        return formatString.formatted(toolCode, checkoutDate, numDays, discountPercentage);
    }
};