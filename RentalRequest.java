class RentalRequest
{
    // Data fields.

    private String toolCode;
    private String checkoutDate;
    private int numDays;
    private int discountPercentage;

    // Constructors.

    public RentalRequest() {
        this.toolCode = "";
        this.checkoutDate = "";
        this.numDays = 0;
        this.discountPercentage = 0;
    }

    // Methods to read in data from other sources.

    public void populateFromCommandLine(String []argv) {
        final int toolCodeIndex = 0;
        final int checkoutDateIndex = 1;
        final int numDaysIndex = 2;
        final int discountPercentageIndex = 3;
        final int maxArgumentsToConsider = 4;

        // Don't look at more arguments than is useful.
        int argumentsToConsider = Math.min(argv.length, maxArgumentsToConsider);

        // Look at the arguments and populate this object's data members accordingly.
        for (int i = 0; i < argumentsToConsider; i++) {
            switch (i) {
                case toolCodeIndex:
                    this.setToolCode(argv[i]);
                    break;

                case checkoutDateIndex:
                    this.setCheckoutDate(argv[i]);
                    break;

                case numDaysIndex:
                    int numDays = 0;
                    try {
                        numDays = Integer.parseInt(argv[i]);
                    } catch (NumberFormatException e) {
                        System.out.printf("Error: Invalid number of days: %s\n", argv[i]);
                        break;
                    }
                    this.setNumDays(numDays);
                    break;

                case discountPercentageIndex:
                    int discountPercentage = 0;
                    try {
                        discountPercentage = Integer.parseInt(argv[i]);
                    } catch (NumberFormatException e) {
                        System.out.printf("Error: Invalid discount percentage: %s\n", argv[i]);
                        break;
                    }
                    this.setDiscountPercentage(discountPercentage);
                    break;

                default:
                    // optimization: break out of not just the switch but the for loop as well
                    break;
            }
        }
    }

    // Getters and setters.

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
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

    // Output this object.

    public void display() {
        System.out.printf("               Tool: %s\n", toolCode);
        System.out.printf("      Checkout Date: %s\n", checkoutDate);
        System.out.printf("     Number of Days: %d\n", numDays);
        System.out.printf("Discount Percentage: %d\n", discountPercentage);
    }
};