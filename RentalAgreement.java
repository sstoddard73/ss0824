import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

class RentalAgreement
{
    // Data fields.

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
        preDiscountCharge = priceRules.getDailyCharge() * numChargeDays;
        discountPercent = request.getDiscountPercentage();
    
        float discount = ((float)(discountPercent)) / 100f;
        discountAmount = Math.round(discount * (float)preDiscountCharge);

        finalCharge = preDiscountCharge - discountAmount;
    }

    // Serialize/stringify methods.

    @Override
    public String toString() {
        // Format the currency values.
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        String dailyChargeString = currencyFormatter.format(.01f * (float)priceRules.getDailyCharge());
        String preDiscountChargeString = currencyFormatter.format(.01f * (float)preDiscountCharge);
        String discountAmountString = currencyFormatter.format(.01f * (float)discountAmount);
        String finalChargeString = currencyFormatter.format(.01f * (float)finalCharge);

        // Format the dates.
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String checkoutDateString = checkoutDate.format(dateFormatter);
        String dueDateString = dueDate.format(dateFormatter);

        // Display the data.
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