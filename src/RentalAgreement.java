import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * RentalAgreement contains all the data needed to describe a particular rental
 * agreement, whereby a customer will rent a particular tool for certain dates
 * at a certain price.
 * 
 * When a rental agreement object is created, it uses a given CheckoutRequest object
 * to populate all of its various data fields.  While not explicitly passed in,
 * it uses the ToolRegistry and PriceRegistry singletons to look up necessary
 * information not included in the CheckoutRequest.  All such data is copied into
 * itself.  Because of that, subsequent changes to the tool registry or price registry
 * (not possible in the current implementation but a possible future change) will not
 * retroactively change the terms of any pre-existing rental agreement. 
 */
class RentalAgreement {
    private Tool tool;
    private PriceRules priceRules;
    private int numRentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int numChargeDays;
    private int preDiscountCharge;   // whole number of cents, to avoid floating point rounding errors
    private int discountPercent;     // percentage from 0-100
    private int discountAmount;      // whole number of cents, to avoid floating point rounding errors
    private int finalCharge;         // whole number of cents, to avoid floating point rounding errors

    /**
     * Construct a new rental agreement.
     * @param request CheckoutRequest
     */
    public RentalAgreement(CheckoutRequest request) {
        // Initialize the tool object.
        ToolRegistry toolRegistry = ToolRegistry.INSTANCE;
        tool = toolRegistry.getToolByCode(request.getToolCode());

        // Initialize the price rules object.
        PriceRegistry priceRegistry = PriceRegistry.INSTANCE;
        priceRules = priceRegistry.getPriceRulesForToolType(tool.getToolType());

        // Initialize the other information.
        numRentalDays = request.getNumDays();
        checkoutDate = request.getCheckoutDate();
        dueDate = request.getDueDate();

        // Calculate the number of charge days.
        DayCounter dc = new DayCounter();
        dc.countDays(checkoutDate, numRentalDays);
        numChargeDays = dc.getChargeDays(priceRules);

        // Calculate the regular price, prior to any discounts.
        preDiscountCharge = priceRules.getDailyCharge() * numChargeDays;

        // Calculate the discount amount.
        discountPercent = request.getDiscountPercentage();
        float discount = ((float)(discountPercent)) / 100f;
        discountAmount = Math.round(discount * (float)preDiscountCharge);

        // Calculate the discounted price.
        finalCharge = preDiscountCharge - discountAmount;
    }

    /**
     * Get the tool code.
     * @return the tool code string
     */
    public String getToolCode() {
        return tool.getToolCode();
    }

    /**
     * Get the tool type.
     * @return the tool type string
     */
    public String getToolType() {
        return tool.getToolType();
    }

    /**
     * Get the tool brand.
     * @return the tool brand string
     */
    public String getToolBrand() {
        return tool.getToolBrand();
    }

    /**
     * Get the number of rental days.
     * @return the number of rental days
     */
    public int getNumRentalDays() {
        return numRentalDays;
    }

    /**
     * Get the checkout date.
     * @return the checkout date
     */
    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    /**
     * Get the due date.
     * @return the due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Get the daily charge.
     * @return the daily charge amount (as a whole number of cents)
     */
    public int getDailyCharge() {
        return priceRules.getDailyCharge();
    }

    /**
     * Get the number of charge days.
     * @return the number of charge days
     */
    public int getNumChargeDays() {
        return numChargeDays;
    }

    /**
     * Get the pre-discount charge.
     * @return the pre-discount charge amount (as a whole number of cents)
     */
    public int getPreDiscountCharge() {
        return preDiscountCharge;
    }

    /**
     * Get the discount percent.
     * @return the discount percent (as an integer between 0 and 100)
     */
    public int getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Get the discount amount.
     * @return the amount of the discount (as a whole number of cents)
     */
    public int getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Get the final charge.
     * @return the final charge amount (as a whole number of cents)
     */
    public int getFinalCharge() {
        return finalCharge;
    }

    /**
     * Return a human-readable string representing the rental agreement.
     * @return human-readable, multi-line string
     */
    @Override
    public String toString() {
        // Format the currency values.
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);                   // force $ rather than using current locale's currency
        String dailyChargeString = currencyFormatter.format(.01f * (float)priceRules.getDailyCharge()); // multiply by .01f to convert cents to decimal dollars
        String preDiscountChargeString = currencyFormatter.format(.01f * (float)preDiscountCharge);     // multiply by .01f to convert cents to decimal dollars
        String discountAmountString = currencyFormatter.format(.01f * (float)discountAmount);           // multiply by .01f to convert cents to decimal dollars
        String finalChargeString = currencyFormatter.format(.01f * (float)finalCharge);                 // multiply by .01f to convert cents to decimal dollars

        // Format the dates.
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String checkoutDateString = checkoutDate.format(dateFormatter);
        String dueDateString = dueDate.format(dateFormatter);

        // Construct the human-readable string.
        String formatString = "          Tool Code: %s\n"
                            + "          Tool Type: %s\n"
                            + "         Tool Brand: %s\n"
                            + "        Rental Days: %d\n"
                            + "      Checkout Date: %s\n"
                            + "           Due Date: %s\n"
                            + "Daily Rental Charge: %s\n"
                            + "        Charge Days: %d\n"
                            + "Pre-Discount Charge: %s\n"
                            + "   Discount Percent: %d%%\n"
                            + "    Discount Amount: %s\n"
                            + "       Final Charge: %s\n";
        return formatString.formatted(
            tool.getToolCode(),
            tool.getToolType(),
            tool.getToolBrand(),
            numRentalDays,
            checkoutDateString,
            dueDateString,
            dailyChargeString,
            numChargeDays,
            preDiscountChargeString,
            discountPercent,
            discountAmountString,
            finalChargeString
        );
    }
};