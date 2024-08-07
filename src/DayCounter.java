import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.Month;

/**
 * DayCounter keeps track of types of days and allows the caller to increment these counts without
 * having to understand which days are of which types.
 * Types of days are mutually exclusive: a day can be a weekday, weekend day, or holiday.
 * Holidays take precedence.
 */
class DayCounter {
    private int numWeekdays;
    private int numWeekendDays;
    private int numHolidays;

    /**
     * Construct a new DayCounter object with zero as the starting count for all types of days.
     */
    public DayCounter() {
        resetCounters();
    }

    /**
     * Construct a new DayCounter object with the specified starting counts.
     * @param numWeekdays
     * @param numWeekendDays
     * @param numHolidays
     */
    public DayCounter(int numWeekdays, int numWeekendDays, int numHolidays) {
        this.numWeekdays = numWeekdays;
        this.numWeekendDays = numWeekendDays;
        this.numHolidays = numHolidays;
    }

    /**
     * Reset the counts of all types of days to 0.
     */
    public void resetCounters() {
        numWeekdays = 0;
        numWeekendDays = 0;
        numHolidays = 0;
    }

    /**
     * Take a start date and a number of days and increment the day type counters
     * for all dates within the range.  The start date itself is NOT counted, but
     * the end date is.
     * 
     * @param startDay the LocalDate object representing the start date
     * @param numDays  the number of days to count
     * @return the DayCounts object representing the counts of day types
     */
    public void countDays(LocalDate startDay, int numDays) {
        // Iterate over the date range.
        LocalDate date = startDay;
        for (int i = 0; i < numDays; i++) {
            // Bump up the day.  We do this before counting, not after, because we don't want to count
            // the start date, but we do want to count the end date.
            date = date.plusDays(1);

            // Get our day counter object to count this day in whatever category of day it determines.
            countDay(date);            
        }
    }

    /**
     * Increment the appropriate day type counter based on what kind of a day the
     * given date is.  Only one type of day will be counted.  If it's a holiday, the
     * holiday counter will be incremented; otherwise, either the weekend day or weekday
     * counter will be incremented.
     * @param date the date in question
     */
    public void countDay(LocalDate date) {
        // Is this a holiday?  If so, don't bother to do the weekend vs. weekday check.
        // (NOTE: This logic only works because day types are mutually exclusive.
        //        If one day could be more than one type, we'd have to write this differently.)
        if (isHoliday(date)) {
            numHolidays++;
        } else if (isWeekendDay(date)) {
            numWeekendDays++;
        } else {
            numWeekdays++;
        }
    }

    /**
     * Check to see if the given date is a weekend day or a weekday.
     * @param date
     * @return a boolean representing whether or not the given date is a weekend day
     */
    private boolean isWeekendDay(LocalDate date) {
        DayOfWeek dow = date.getDayOfWeek();
        return dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY;
    }

    /**
     * Check to see if the given date is a holiday.
     * Holidays are:
     *    - July 4 (or nearest weekday if it falls on a weekend day)
     *    - First Monday in September
     * @param date
     * @return a boolean representing whether or not the given date is a holiday
     */
    private boolean isHoliday(LocalDate date) {
        // This is going to be ugly code, because it hard-codes the holidays.
        //
        // Generally I would not want to hard-code the holiday rules here, but it feels like overkill
        // to develop a generic custom holiday class for this project.  In a production project that
        // needs to be more scalable, I would have a Holiday class that allows you to specify the rules
        // (e.g., Nth day of week in the month or Nth day in the month; boolean for nearest weekday, etc)
        // and check a date against it.  Then I'd have a "holiday calculator" class that allows the calling
        // code to set any number of holidays and check dates against them.
        //
        // (Although before I went to the trouble of all that, I'd poke around to see if there are any
        // third-party libraries that do this sort of thing.  I have found a few after a quick Google
        // search, although I did not go so far as to evaluate their suitability with regard to functionality,
        // dependencies, and license terms.)

        // Start checking holidays.
        // We will return true if the date lands on a holiday; otherwise we will keep checking holidays
        // until we run out of them.
        DayOfWeek dow = date.getDayOfWeek();

        // Independence Day (the 4th of July or nearest weekday)
        // The three possibilities:
        //    - 3rd of July on a Friday
        //    - 4th of July on a weekday
        //    - 5th of July on a Monday
        if (date.getMonth() == Month.JULY) {
            if (!isWeekendDay(date) && date.getDayOfMonth() == 4) {
                return true;
            } else if (dow == DayOfWeek.FRIDAY && date.getDayOfMonth() == 3) {
                return true;
            } else if (dow == DayOfWeek.MONDAY && date.getDayOfMonth() == 5) {
                return true;
            }
        }

        // Labor Day (first Monday in September)
        // Any Monday that appears on the 1st through the 7th must be the first Monday; all other Mondays must not be.
        if (date.getMonth() == Month.SEPTEMBER && dow == DayOfWeek.MONDAY && date.getDayOfMonth() <= 7) {
            return true;
        }

        // All done checking for holidays.  If we got this far, the date is not a holiday.
        return false;
    }

    /**
     * Get the current counter value for the number of weekdays.
     * @return the number of weekdays
     */
    public int getNumWeekdays() {
        return numWeekdays;
    }

    /**
     * Set the number of weekdays.
     * @param numWeekdays int (non-negative)
     */
    public void setNumWeekdays(int numWeekdays) {
        if (numWeekdays < 0) {
            throw new IllegalArgumentException("numWeekdays cannot be negative");
        }
        this.numWeekdays = numWeekdays;
    }

    /**
     * Get the current counter value for the number of weekend days.
     * @return the number of weekend days
     */
    public int getNumWeekendDays() {
        return numWeekendDays;
    }

    /**
     * Set the number of weekend days.
     * @param numWeekendDays int (non-negative)
     */
    public void setNumWeekendDays(int numWeekendDays) {
        if (numWeekendDays < 0) {
            throw new IllegalArgumentException("numWeekendDays cannot be negative");
        }
        this.numWeekendDays = numWeekendDays;
    }

    /**
     * Get the current counter value for the number of holidays.
     * @return the number of holidays
     */
    public int getNumHolidays() {
        return numHolidays;
    }

    /**
     * Set the number of holidays.
     * @param numHolidays int (non-negative)
     */
    public void setNumHolidays(int numHolidays) {
        if (numHolidays < 0) {
            throw new IllegalArgumentException("numHolidays cannot be negative");
        }
        this.numHolidays = numHolidays;
    }
};