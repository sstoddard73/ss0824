import java.time.LocalDate;
import org.junit.*;

public class DayCounterTest {
    @Test
    public void DayCounter_DefaultConstructor() {
        DayCounter dc = new DayCounter();
        Assert.assertEquals(0, dc.getNumWeekdays());
        Assert.assertEquals(0, dc.getNumWeekendDays());
        Assert.assertEquals(0, dc.getNumHolidays());
    }

    @Test
    public void DayCounter_ConstructorWithInitialValues() {
        DayCounter dc = new DayCounter(5,7,9);
        Assert.assertEquals(5, dc.getNumWeekdays());
        Assert.assertEquals(7, dc.getNumWeekendDays());
        Assert.assertEquals(9, dc.getNumHolidays());
    }

    @Test
    public void DayCounter_ResetCounters() {
        DayCounter dc = new DayCounter(5,7,9);
        dc.resetCounters();
        Assert.assertEquals(0, dc.getNumWeekdays());
        Assert.assertEquals(0, dc.getNumWeekendDays());
        Assert.assertEquals(0, dc.getNumHolidays());
    }

    @Test
    public void DayCounter_GettersAndSetters() {
        DayCounter dc = new DayCounter();

        dc.setNumWeekdays(6);
        Assert.assertEquals(6, dc.getNumWeekdays());

        dc.setNumWeekendDays(8);
        Assert.assertEquals(8, dc.getNumWeekendDays());

        dc.setNumHolidays(10);
        Assert.assertEquals(10, dc.getNumHolidays());
    }

    @Test
    public void DayCounter_CountChargeDays() {
        // Since we'll be adding up the day type counts in different combinations,
        // use test numbers where every distinct combination adds up to a unique total.
        DayCounter dc = new DayCounter(1,2,4);
    
        PriceRules pr = new PriceRules("TEST", 1, false, false, false);
        Assert.assertEquals(0, dc.getChargeDays(pr));

        pr.setWeekdayCharge(true);
        Assert.assertEquals(1, dc.getChargeDays(pr));
    
        pr.setWeekendCharge(true);
        Assert.assertEquals(3, dc.getChargeDays(pr));
    
        pr.setHolidayCharge(true);
        Assert.assertEquals(7, dc.getChargeDays(pr));
    }

    @Test
    public void DayCounter_CountDay() {
        DayCounter dc = new DayCounter();

        // Try some weekdays.
        dc.countDay(LocalDate.of(2024, 3, 14)); // ordinary weekday
        dc.countDay(LocalDate.of(2024, 7, 5));  // July 5th that's not a celebration of July 4th
        dc.countDay(LocalDate.of(2024, 7, 3));  // July 3rd that's not a celebration of July 4th
        dc.countDay(LocalDate.of(2024, 9, 9));  // second Monday in September
        Assert.assertEquals(4, dc.getNumWeekdays());
        Assert.assertEquals(0, dc.getNumWeekendDays());
        Assert.assertEquals(0, dc.getNumHolidays());

        // Try some weekend days.
        dc.countDay(LocalDate.of(2024, 10, 6)); // ordinary weekend day
        dc.countDay(LocalDate.of(2020, 7, 5));  // July 5th on a weekend day, so not a celebration of July 4th
        dc.countDay(LocalDate.of(2022, 7, 3));  // July 3rd on a weekend day, so not a celebration of July 4th
        dc.countDay(LocalDate.of(2024, 9, 1));  // day in September that is sometimes a Monday holiday but not Monday in this year
        Assert.assertEquals(4, dc.getNumWeekdays());
        Assert.assertEquals(4, dc.getNumWeekendDays());
        Assert.assertEquals(0, dc.getNumHolidays());

        // Try some holidays.
        dc.countDay(LocalDate.of(2024, 7, 4)); // weekday July 4th
        dc.countDay(LocalDate.of(2021, 7, 5)); // Monday celebration of July 4th
        dc.countDay(LocalDate.of(2020, 7, 3)); // Friday celebration of July 4th
        dc.countDay(LocalDate.of(2024, 9, 2)); // first Monday in September
        Assert.assertEquals(4, dc.getNumWeekdays());
        Assert.assertEquals(4, dc.getNumWeekendDays());
        Assert.assertEquals(4, dc.getNumHolidays());
    }

    @Test
    public void DayCounter_CountDays() {
        DayCounter dc = new DayCounter();

        // We don't need to be too thorough here, as similar ground is covered
        // in DayCounter_CountDay and RentalAgreementTest.
        dc.countDays(LocalDate.of(2024, 6, 29), 10);
        Assert.assertEquals(6, dc.getNumWeekdays());
        Assert.assertEquals(3, dc.getNumWeekendDays());
        Assert.assertEquals(1, dc.getNumHolidays());

        dc.resetCounters();
        dc.countDays(LocalDate.of(2024, 9, 1), 3);
        Assert.assertEquals(2, dc.getNumWeekdays());
        Assert.assertEquals(0, dc.getNumWeekendDays());
        Assert.assertEquals(1, dc.getNumHolidays());
    }
}
