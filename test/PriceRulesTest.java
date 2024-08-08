import org.junit.*;

public class PriceRulesTest {
    @Test
    public void PriceRules_DefaultConstructor() {
        PriceRules pr = new PriceRules();
        Assert.assertEquals("", pr.getToolType());
        Assert.assertEquals(0, pr.getDailyCharge());
        Assert.assertEquals(false, pr.getWeekdayCharge());
        Assert.assertEquals(false, pr.getWeekendCharge());
        Assert.assertEquals(false, pr.getHolidayCharge());
    }

    @Test
    public void PriceRules_ConstructorWithInitialValues() {
        PriceRules pr = new PriceRules("TOOL TYPE", 950, true, false, true);
        Assert.assertEquals("TOOL TYPE", pr.getToolType());
        Assert.assertEquals(950, pr.getDailyCharge());
        Assert.assertEquals(true, pr.getWeekdayCharge());
        Assert.assertEquals(false, pr.getWeekendCharge());
        Assert.assertEquals(true, pr.getHolidayCharge());

        // Try a couple different combinations of values, to ensure that the
        // constructor isn't mismatching the boolean values.
        pr = new PriceRules("TOOL TYPE", 950, false, true, true);
        Assert.assertEquals(false, pr.getWeekdayCharge());
        Assert.assertEquals(true, pr.getWeekendCharge());
        Assert.assertEquals(true, pr.getHolidayCharge());
    
        pr = new PriceRules("TOOL TYPE", 950, true, true, false);
        Assert.assertEquals(true, pr.getWeekdayCharge());
        Assert.assertEquals(true, pr.getWeekendCharge());
        Assert.assertEquals(false, pr.getHolidayCharge());
    }

    @Test
    public void CheckoutRequest_CopyConstructor() {
        PriceRules pr1 = new PriceRules("TYPE", 888, true, false, true);
        PriceRules pr2 = new PriceRules(pr1);
        Assert.assertFalse(pr1 == pr2);
        Assert.assertEquals(pr1.getToolType(), pr2.getToolType());
        Assert.assertEquals(pr1.getDailyCharge(), pr2.getDailyCharge());
        Assert.assertEquals(pr1.getWeekdayCharge(), pr2.getWeekdayCharge());
        Assert.assertEquals(pr1.getWeekendCharge(), pr2.getWeekendCharge());
        Assert.assertEquals(pr1.getHolidayCharge(), pr2.getHolidayCharge());

        // Try a couple other combinations of booleans, to make sure the copy
        // constructor isn't mismatching them.
        pr1 = new PriceRules("TYPE", 888, false, true, true);
        pr2 = new PriceRules(pr1);
        Assert.assertEquals(pr1.getWeekdayCharge(), pr2.getWeekdayCharge());
        Assert.assertEquals(pr1.getWeekendCharge(), pr2.getWeekendCharge());
        Assert.assertEquals(pr1.getHolidayCharge(), pr2.getHolidayCharge());
        
        pr1 = new PriceRules("TYPE", 888, true, true, false);
        pr2 = new PriceRules(pr1);
        Assert.assertEquals(pr1.getWeekdayCharge(), pr2.getWeekdayCharge());
        Assert.assertEquals(pr1.getWeekendCharge(), pr2.getWeekendCharge());
        Assert.assertEquals(pr1.getHolidayCharge(), pr2.getHolidayCharge());
    }

    @Test
    public void PriceRules_GettersAndSetters() {
        PriceRules pr = new PriceRules();

        // Test tool type.
        pr.setToolType("MY FAVORITE TOOL TYPE");
        Assert.assertEquals("MY FAVORITE TOOL TYPE", pr.getToolType());

        // Test the booleans.
        pr.setWeekdayCharge(true);
        Assert.assertEquals(true, pr.getWeekdayCharge());

        pr.setWeekendCharge(true);
        Assert.assertEquals(true, pr.getWeekendCharge());
    
        pr.setHolidayCharge(true);
        Assert.assertEquals(true, pr.getHolidayCharge());
    }
}