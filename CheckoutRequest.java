import java.time.LocalDate;

class CheckoutRequest
{
    // Data fields.

    private String toolCode;
    private LocalDate checkoutDate;
    private int numDays;
    private int discountPercentage;

    // Constructors.

    public CheckoutRequest() {
        toolCode = "";
        checkoutDate = LocalDate.now();
        numDays = 0;
        discountPercentage = 0;
    }

    // Getters and setters.

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    // Getter-like methods to return data we can calculate.

    public LocalDate getDueDate() {
        return checkoutDate.plusDays(numDays);
    }

    // Serialize/stringify methods.

    @Override
    public String toString() {
        String formatString = "               Tool: %s\n"
                            + "      Checkout Date: %s\n"
                            + "     Number of Days: %d\n"
                            + "Discount Percentage: %d\n";
        return formatString.formatted(toolCode, checkoutDate, numDays, discountPercentage);
    }
};