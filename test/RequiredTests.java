import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.*;

public class RequiredTests {
    @Test
    public void RequiredTest_Scenario1() {
        IllegalArgumentException e = assertThrows(
            IllegalArgumentException.class,
            () -> new CheckoutRequest("JAKR", LocalDate.of(2015, 9, 3), 5, 101)
        );
        Assert.assertEquals("discountPercentage must be between 0 and 100", e.getMessage());
    }

    @Test
    public void RequiredTest_Scenario2() {
        CheckoutRequest cr = new CheckoutRequest("LADW", LocalDate.of(2020, 7, 2), 3, 10);
        RentalAgreement ra = new RentalAgreement(cr);

        Assert.assertEquals("LADW", ra.getToolCode());
        Assert.assertEquals("Ladder", ra.getToolType());
        Assert.assertEquals("Werner", ra.getToolBrand());
        Assert.assertEquals(3, ra.getNumRentalDays());
        Assert.assertEquals(LocalDate.of(2020, 7, 2), ra.getCheckoutDate());
        Assert.assertEquals(LocalDate.of(2020, 7, 5), ra.getDueDate());
        Assert.assertEquals(149, ra.getDailyCharge());
        Assert.assertEquals(1, ra.getNumChargeDays());
        Assert.assertEquals(149, ra.getPreDiscountCharge());
        Assert.assertEquals(10, ra.getDiscountPercent());
        Assert.assertEquals(15, ra.getDiscountAmount());
        Assert.assertEquals(134, ra.getFinalCharge());
    }

    @Test
    public void RequiredTest_Scenario3() {
        CheckoutRequest cr = new CheckoutRequest("CHNS", LocalDate.of(2015, 7, 2), 5, 25);
        RentalAgreement ra = new RentalAgreement(cr);

        Assert.assertEquals("CHNS", ra.getToolCode());
        Assert.assertEquals("Chainsaw", ra.getToolType());
        Assert.assertEquals("Stihl", ra.getToolBrand());
        Assert.assertEquals(5, ra.getNumRentalDays());
        Assert.assertEquals(LocalDate.of(2015, 7, 2), ra.getCheckoutDate());
        Assert.assertEquals(LocalDate.of(2015, 7, 7), ra.getDueDate());
        Assert.assertEquals(199, ra.getDailyCharge());
        Assert.assertEquals(4, ra.getNumChargeDays());
        Assert.assertEquals(796, ra.getPreDiscountCharge());
        Assert.assertEquals(25, ra.getDiscountPercent());
        Assert.assertEquals(199, ra.getDiscountAmount());
        Assert.assertEquals(597, ra.getFinalCharge());
    }

    @Test
    public void RequiredTest_Scenario4() {
        CheckoutRequest cr = new CheckoutRequest("JAKD", LocalDate.of(2015, 9, 3), 6, 0);
        RentalAgreement ra = new RentalAgreement(cr);

        Assert.assertEquals("JAKD", ra.getToolCode());
        Assert.assertEquals("Jackhammer", ra.getToolType());
        Assert.assertEquals("DeWalt", ra.getToolBrand());
        Assert.assertEquals(6, ra.getNumRentalDays());
        Assert.assertEquals(LocalDate.of(2015, 9, 3), ra.getCheckoutDate());
        Assert.assertEquals(LocalDate.of(2015, 9, 9), ra.getDueDate());
        Assert.assertEquals(299, ra.getDailyCharge());
        Assert.assertEquals(3, ra.getNumChargeDays());
        Assert.assertEquals(897, ra.getPreDiscountCharge());
        Assert.assertEquals(0, ra.getDiscountPercent());
        Assert.assertEquals(0, ra.getDiscountAmount());
        Assert.assertEquals(897, ra.getFinalCharge());
    }

    @Test
    public void RequiredTest_Scenario5() {
        CheckoutRequest cr = new CheckoutRequest("JAKR", LocalDate.of(2020, 7, 2), 4, 50);
        RentalAgreement ra = new RentalAgreement(cr);

        Assert.assertEquals("JAKR", ra.getToolCode());
        Assert.assertEquals("Jackhammer", ra.getToolType());
        Assert.assertEquals("Ridgid", ra.getToolBrand());
        Assert.assertEquals(4, ra.getNumRentalDays());
        Assert.assertEquals(LocalDate.of(2020, 7, 2), ra.getCheckoutDate());
        Assert.assertEquals(LocalDate.of(2020, 7, 6), ra.getDueDate());
        Assert.assertEquals(299, ra.getDailyCharge());
        Assert.assertEquals(1, ra.getNumChargeDays());
        Assert.assertEquals(299, ra.getPreDiscountCharge());
        Assert.assertEquals(50, ra.getDiscountPercent());
        Assert.assertEquals(150, ra.getDiscountAmount());
        Assert.assertEquals(149, ra.getFinalCharge());
    }
}
