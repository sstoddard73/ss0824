import org.junit.*;
import java.time.LocalDate;

public class CheckoutRequestTest {
    @Test
    public void CheckoutRequest_DefaultConstructor() {
        // Remember the current time so we can check it later.
        LocalDate before = LocalDate.now();

        // Instantiate a CheckoutRequest.
        CheckoutRequest cr = new CheckoutRequest();

        // Make sure the checkout date is set to the time of this test execution.
        Assert.assertFalse(before.isAfter(cr.getCheckoutDate()));
        Assert.assertFalse(LocalDate.now().isBefore(cr.getCheckoutDate()));

        // Verify all the other fields.
        Assert.assertEquals("", cr.getToolCode());
        Assert.assertEquals(0, cr.getNumDays());
        Assert.assertEquals(0, cr.getDiscountPercentage());
    }

    @Test
    public void CheckoutRequest_ConstructorWithInitialValues() {
        CheckoutRequest cr = new CheckoutRequest("TOOL CODE", LocalDate.of(2024, 5, 6), 3, 15);
        Assert.assertEquals("TOOL CODE", cr.getToolCode());
        Assert.assertEquals(LocalDate.of(2024, 5, 6), cr.getCheckoutDate());
        Assert.assertEquals(3, cr.getNumDays());
        Assert.assertEquals(15, cr.getDiscountPercentage());
    }

    @Test
    public void CheckoutRequest_GettersAndSetters() {
        CheckoutRequest cr = new CheckoutRequest();

        // Test tool code.
        cr.setToolCode("MY FAVORITE TOOL CODE");
        Assert.assertEquals("MY FAVORITE TOOL CODE", cr.getToolCode());

        // Test checkout date.
        cr.setCheckoutDate(LocalDate.of(2024, 7, 8));
        Assert.assertEquals(LocalDate.of(2024, 7, 8), cr.getCheckoutDate());

        // Test number of days.
        cr.setNumDays(18);
        Assert.assertEquals(18, cr.getNumDays());

        // Test discount percentage.
        cr.setDiscountPercentage(77);
        Assert.assertEquals(77, cr.getDiscountPercentage());
    }

    @Test
    public void CheckoutRequest_NumDays_CheckRange() {
        // Make sure the constructor doesn't allow non-positive numDays.
        Assert.assertThrows(
            IllegalArgumentException.class,
            () -> new CheckoutRequest("TOOL", LocalDate.now(), 0, 15)
        );
        Assert.assertThrows(
            IllegalArgumentException.class,
            () -> new CheckoutRequest("TOOL", LocalDate.now(), -9, 15)
        );

        // Make sure the setter doesn't allow non-positive numDays.
        CheckoutRequest cr = new CheckoutRequest();
        Assert.assertThrows(
            IllegalArgumentException.class,
            () -> cr.setNumDays(0)
        );
        Assert.assertThrows(
            IllegalArgumentException.class,
            () -> cr.setNumDays(-11)
        );
    }

    @Test
    public void CheckoutRequest_DiscountPercentage_CheckRange() {
        // First make sure that values within the valid range are okay.
        CheckoutRequest cr = new CheckoutRequest();
        cr.setDiscountPercentage(0);
        cr.setDiscountPercentage(1);
        cr.setDiscountPercentage(44);
        cr.setDiscountPercentage(100);

        // Make sure the constructor doesn't allow percentages outside the 0-100 range.
        Assert.assertThrows(
            IllegalArgumentException.class,
            () -> new CheckoutRequest("TOOL", LocalDate.now(), 2, -1)
        );
        Assert.assertThrows(
            IllegalArgumentException.class,
            () -> new CheckoutRequest("TOOL", LocalDate.now(), 2, 101)
        );

        // Make sure the setter doesn't allow non-positive numDays.
        Assert.assertThrows(
            IllegalArgumentException.class,
            () -> cr.setDiscountPercentage(-1)
        );
        Assert.assertThrows(
            IllegalArgumentException.class,
            () -> cr.setDiscountPercentage(101)
        );
    }

    @Test
    public void CheckoutRequest_DueDateCalculation() {
        // Create a new CheckoutRequest with a checkout date and rental days.
        CheckoutRequest cr = new CheckoutRequest();
        cr.setCheckoutDate(LocalDate.of(2024, 5, 6));
        cr.setNumDays(3);

        // Make sure the due date is correctly calculated.
        Assert.assertEquals(LocalDate.of(2024, 5, 9), cr.getDueDate());
    }
}

