import java.time.LocalDate;

class RentalAgreement
{
    // Data fields.

    private Tool tool;
    private PriceRules priceRules;
    private int numRentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int numChargeDays;
    private int prediscountCharge;   // whole number of cents, to avoid floating point rounding errors
    private int discountPercent;     // percentage from 0-100
    private int discountAmount;      // whole number of cents, to avoid floating point rounding errors
    private int finalCharge;         // whole number of cents, to avoid floating point rounding errors

    // Constructors.

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

        // Calculate the prices.
        numChargeDays = numRentalDays;   // TODO: not the real number!  Need to consider free weekend days and holidays based on tool type.
        prediscountCharge = priceRules.getDailyCharge() * numChargeDays;
        discountPercent = request.getDiscountPercentage();
    
        float discount = ((float)(discountPercent)) / 100f;
        discountAmount = Math.round(discount * (float)prediscountCharge);

        finalCharge = prediscountCharge - discountAmount;
    }

    // Serialize/stringify methods.

    @Override
    public String toString() {
        String formatString = "          Tool Code: %s\n"
                            + "          Tool Type: %s\n"
                            + "         Tool Brand: %s\n"
                            + "        Rental Days: %d\n"
                            + "      Checkout Date: %s\n"
                            + "           Due Date: %s\n"
                            + "Daily Rental Charge: %d.%02d\n"
                            + "        Charge Days: %d\n"
                            + "Pre-Discount Charge: %d.%02d\n"
                            + "   Discount Percent: %d%%\n"
                            + "    Discount Amount: %d.%02d\n"
                            + "       Final Charge: %d.%02d\n";
        return formatString.formatted(
            tool.getToolCode(),
            tool.getToolType(),
            tool.getToolBrand(),
            numRentalDays,
            checkoutDate,
            dueDate,
            priceRules.getDailyCharge() / 100,
            priceRules.getDailyCharge() % 100,
            numChargeDays,
            prediscountCharge / 100,
            prediscountCharge % 100,
            discountPercent,
            discountAmount / 100,
            discountAmount % 100,
            finalCharge / 100,
            finalCharge % 100
        );
    }
};