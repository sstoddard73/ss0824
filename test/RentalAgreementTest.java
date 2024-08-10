import org.junit.*;
import java.time.LocalDate;

// This test file is lean only because in an ordinary project the tests in RequiredTests.java would also live here.

public class RentalAgreementTest {
    @Test
    public void RentalAgreement_ConstructFromCheckoutRequest() {
        CheckoutRequest cr = new CheckoutRequest("JAKR", LocalDate.of(2024, 4, 2), 5, 15);
        RentalAgreement ra = new RentalAgreement(cr);

        Assert.assertEquals("JAKR", ra.getToolCode());
        Assert.assertEquals("Jackhammer", ra.getToolType());
        Assert.assertEquals("Ridgid", ra.getToolBrand());
        Assert.assertEquals(5, ra.getNumRentalDays());
        Assert.assertEquals(LocalDate.of(2024, 4, 2), ra.getCheckoutDate());
        Assert.assertEquals(LocalDate.of(2024, 4, 7), ra.getDueDate());
        Assert.assertEquals(299, ra.getDailyCharge());
        Assert.assertEquals(3, ra.getNumChargeDays());
        Assert.assertEquals(897, ra.getPreDiscountCharge());
        Assert.assertEquals(15, ra.getDiscountPercent());
        Assert.assertEquals(135, ra.getDiscountAmount());
        Assert.assertEquals(762, ra.getFinalCharge());
    }
}
