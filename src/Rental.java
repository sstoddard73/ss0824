import java.time.LocalDate;

/**
 * Temporary test harness for this project; only need this until JUnit tests are established.
 */
class Rental {
    public static void main(String []argv) {
        // Create and populate a checkout request.
        CheckoutRequest request = new CheckoutRequest();
        request.setCheckoutDate(LocalDate.now());
        request.setDiscountPercentage(5);
        request.setToolCode("JAKR");
        request.setNumDays(777);

        // Create a rental agreement.
        RentalAgreement agreement = new RentalAgreement(request);
    
        // Display the request.
        System.out.print(request.toString());

        // Display the agreement.
        System.out.println("");
        System.out.print(agreement.toString());

        // Test the day counter.
        DayCounter dc = new DayCounter();
        dc.countDays(LocalDate.of(2024, 9, 1), 3);
        System.out.printf("\nweekdays = %d, weekend = %d, holidays = %d\n", dc.getNumWeekdays(), dc.getNumWeekendDays(), dc.getNumHolidays());
    }
};