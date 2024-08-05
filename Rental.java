import java.util.Scanner;
import java.time.LocalDate;

class Rental {
    public static void main(String []argv) {
        // Create and populate a checkout request.
        CheckoutRequest request = new CheckoutRequest();
        request.setCheckoutDate(LocalDate.now());
        request.setDiscountPercentage(5);
        request.setToolCode("JAKR");
        request.setNumDays(7);

        // Create a rental agreement.
        RentalAgreement agreement = new RentalAgreement(request);
    
        // Display the request.
        System.out.print(request.toString());

        // Display the agreement.
        System.out.println("");
        System.out.print(agreement.toString());

        // Test the day counter.
        DayCounter dc = new DayCounter();
        dc.countDays(LocalDate.of(2024, 8, 5), 7);
        System.out.printf("\nweekdays = %d, weekend = %d, holidays = %d\n", dc.getNumWeekdays(), dc.getNumWeekendDays(), dc.getNumHolidays());
    }
};